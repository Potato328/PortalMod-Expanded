package com.potato328.portalmodexpanded.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import com.potato328.portalmodexpanded.tileentity.SingleAutoPortalTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;

import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.portalmod.PortalMod;
import net.portalmod.client.render.DynamicTextureVertexBuilder;
import net.portalmod.common.sorted.autoportal.AutoPortalModel;

import java.util.Optional;

import static com.potato328.portalmodexpanded.block.SingleAutoPortal.DIRECTION;
import static com.potato328.portalmodexpanded.block.SingleAutoPortal.FACING;


public class SingleAutoPortalTER extends TileEntityRenderer<SingleAutoPortalTileEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(PortalMod.MODID, "entity/autoportal");
    public static RenderMaterial MATERIAL;
    private final AutoPortalModel model;

    public SingleAutoPortalTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.model = new AutoPortalModel();
        MATERIAL = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, TEXTURE);
    }

    @Override
    public void render(SingleAutoPortalTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        BlockState state = tileEntity.getBlockState();

        matrixStack.pushPose();
        matrixStack.translate(0.5D, -0.5D, 0.001D);
        Direction facing = state.getValue(FACING);
        Direction direction = state.getValue(DIRECTION);

        switch(facing) {
            case NORTH:
                matrixStack.mulPose(Vector3f.YP.rotationDegrees(180));
                matrixStack.translate(0D, 0D, -0.99D);
                break;
            case EAST:
                matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
                matrixStack.translate(-0.5D, 0D, -0.499D);
                break;
            case WEST:
                matrixStack.mulPose(Vector3f.YP.rotationDegrees(270));
                matrixStack.translate(0.5D, 0D, -0.499D);
                break;
            case UP:
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));
                    matrixStack.translate(0D, -2D, -2D);
                    //matrixStack.mulPose(Vector3f.ZP.rotationDegrees(0));

                }
                if(direction == Direction.EAST || direction == Direction.WEST) {
                    //matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
                    matrixStack.mulPose(Vector3f.XP.rotationDegrees(180));
                    matrixStack.translate(0D, -2D, -1D);
                }
            case DOWN:
                if(direction == Direction.NORTH || direction == Direction.SOUTH) {
                    matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
                    matrixStack.translate(0D, -0.5D, -1.499D);
                }
                if(direction == Direction.EAST || direction == Direction.WEST) {
                    matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
                    matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
                    matrixStack.translate(-0.5D, -1D, -1.499D);
                }



        }


        IVertexBuilder ivertexBuilder = MATERIAL.buffer(buffer, RenderType::entityTranslucent);
        DynamicTextureVertexBuilder dtvb = new DynamicTextureVertexBuilder(ivertexBuilder);

        model.frame.render(matrixStack, dtvb, combinedLight, combinedOverlay, 1, 1, 1, 1);

        int closedOffset = tileEntity.lastOpenedUUID != null ? 0 : 2;

        Optional<Integer> colorIndex = tileEntity.getCurrentColorIndex();
        if(colorIndex.isPresent()) {
            int color = colorIndex.get();
            float u = ((color % 4) * 11 + closedOffset) / 64f;
            float v = (float) (color / 4) * 16 / 64f;
            dtvb.setOffset(u, v);
            model.wawas.render(matrixStack, dtvb, LightTexture.pack(15, 15), combinedOverlay, 1, 1, 1, 1);
        }

        matrixStack.popPose();
    }
}

