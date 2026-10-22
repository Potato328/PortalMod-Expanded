package com.potato328.portalmodexpanded.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.portalmod.common.sorted.panel.PanelBlock;
import net.portalmod.common.sorted.panel.PanelState;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SecondaryPanelTextures {

    private static final String NAMESPACE = "portalmodexpanded";
    private static final String[] ELIGIBLE_SPRITE_PATHS = {"/blue/vintage_blackplate_blue", "/plywood/vintage_blackplate_plywood"};
    private static final String[] ELIGIBLE_PATHS = {"vintage_blackplate_blue", "vintage_blackplate_plywood"};
    private static final String[] CORNER_SUFFIXES = {
            "top_left", "top_right", "bottom_left", "bottom_right"
    };
    private static final String[] CORNER_INFIXES = {
            "_panel_", "_panel_floor_x_", "_panel_floor_z_", "_panel_ceiling_x_", "_panel_ceiling_z_"
    };

    private SecondaryPanelTextures() {}

    public static boolean isEligible(Block block) {
        if(block == null) return false;
        ResourceLocation id = block.getRegistryName();
        if(id == null || !NAMESPACE.equals(id.getNamespace())) return false;
        for(String path : ELIGIBLE_PATHS) {
            if(path.equals(id.getPath())) return true;
        }
        return false;
    }
    public static boolean isWallPaint(BlockState override) {
        return override != null && override.hasProperty(PanelBlock.STATE) && isEligible(override.getBlock())
                && override.getValue(PanelBlock.STATE).isWall();
    }

    public static boolean isCornerPaint(BlockState override) {
        if (override == null || !override.hasProperty(PanelBlock.STATE) || !isEligible(override.getBlock())) return false;
        PanelState state = override.getValue(PanelBlock.STATE);
        return state.isWall() || state.isFloor();
    }

    public static ResourceLocation spriteForVintage(BlockState override, ResourceLocation sprite) {
        if(!isCornerPaint(override) || sprite == null || !NAMESPACE.equals(sprite.getNamespace())) return null;

        String prefix = "block/";
        String path = sprite.getPath();
        if(!path.startsWith(prefix)) return null;

        String name = path.substring(prefix.length());
        for(String corner : CORNER_SUFFIXES) {
            for(String infix : CORNER_INFIXES) {
                String marker = infix + corner;
                if(name.endsWith(marker)) {
                    String material = name.substring(0, name.length() - marker.length());
                    return new ResourceLocation("portalmodexpanded", "block/" + material + "_2x2_" + corner);
                }
            }
        }
        return null;
    }

    public static List<ResourceLocation> allVintageSpriteLocations() {
        List<ResourceLocation> out = new ArrayList<>();
        for (String path : ELIGIBLE_SPRITE_PATHS) {
            for (String corner : CORNER_SUFFIXES) {
                out.add(new ResourceLocation("portalmodexpanded", "block/" + path + "_2x2_" + corner));
            }
        }
        return out;
    }

    public static List<ResourceLocation> eligibleBlockIds() {
        List<ResourceLocation> out = new ArrayList<>();
        for (String path : ELIGIBLE_PATHS) {
            out.add(new ResourceLocation(NAMESPACE, path));
        }
        return out;
    }
}
