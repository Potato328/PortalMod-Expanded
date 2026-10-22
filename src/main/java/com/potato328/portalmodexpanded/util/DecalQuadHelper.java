package com.potato328.portalmodexpanded.util;

import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class DecalQuadHelper {

    public static BakedQuad retexture(BakedQuad quad, TextureAtlasSprite newSprite, int tintIndex) {
        return retexture(quad, newSprite, tintIndex, false);
    }

    public static BakedQuad retexture(BakedQuad quad, TextureAtlasSprite newSprite, int tintIndex, boolean halfTurn) {
        int[] vertices = quad.getVertices().clone();
        TextureAtlasSprite oldSprite = quad.getSprite();

        for (int i = 0; i < 4; i++) {
            int offset = i * 8;

            float u = Float.intBitsToFloat(vertices[offset + 4]);
            float v = Float.intBitsToFloat(vertices[offset + 5]);

            float uPct = (oldSprite.getU1() - oldSprite.getU0() == 0) ? 0 : (u - oldSprite.getU0()) / (oldSprite.getU1() - oldSprite.getU0());
            float vPct = (oldSprite.getV1() - oldSprite.getV0() == 0) ? 0 : (v - oldSprite.getV0()) / (oldSprite.getV1() - oldSprite.getV0());

            if (halfTurn) {
                uPct = 1.0F - uPct;
                vPct = 1.0F - vPct;
            }

            float newU = newSprite.getU0() + uPct * (newSprite.getU1() - newSprite.getU0());
            float newV = newSprite.getV0() + vPct * (newSprite.getV1() - newSprite.getV0());

            vertices[offset + 4] = Float.floatToRawIntBits(newU);
            vertices[offset + 5] = Float.floatToRawIntBits(newV);
        }

        // A tinted layer carries the painted face's element id so the colour provider can reach it; an
        // untinted layer passes -1.
        return new BakedQuad(vertices, tintIndex, quad.getDirection(), newSprite, quad.isShade()
        );
    }

    private static int closestVertexOffset(int[] target, int targetOffset, int[] source) {
        float targetX = Float.intBitsToFloat(target[targetOffset]);
        float targetY = Float.intBitsToFloat(target[targetOffset + 1]);
        float targetZ = Float.intBitsToFloat(target[targetOffset + 2]);
        int closestOffset = 0;
        float closestDistance = Float.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            int sourceOffset = i * 8;
            float dx = targetX - Float.intBitsToFloat(source[sourceOffset]);
            float dy = targetY - Float.intBitsToFloat(source[sourceOffset + 1]);
            float dz = targetZ - Float.intBitsToFloat(source[sourceOffset + 2]);
            float distance = dx * dx + dy * dy + dz * dz;
            if (distance < closestDistance) {
                closestDistance = distance;
                closestOffset = sourceOffset;
            }
        }
        return closestOffset;
    }
}
