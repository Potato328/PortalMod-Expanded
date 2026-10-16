package com.potato328.portalmodexpanded.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.potato328.portalmodexpanded.block.sorted.cubedropper.VintageCubeDropperBlock;
import com.potato328.portalmodexpanded.block.sorted.cubedropper.VintageCubeDropperTileEntity;
import com.potato328.portalmodexpanded.client.renderer.model.VintageCubeDropperDoorModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.portalmod.common.sorted.cubedropper.CubeDropperBlock;

public class VintageCubeDropperTER extends TileEntityRenderer<VintageCubeDropperTileEntity> {
    private final VintageCubeDropperDoorModel model = new VintageCubeDropperDoorModel();

    public VintageCubeDropperTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(VintageCubeDropperTileEntity be, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer renderBuffer, int light, int overlay) {
        if(be.getBlockState().getBlock() instanceof CubeDropperBlock) {
            if(be.getBlockState().getValue(VintageCubeDropperBlock.OPEN)) {
            ResourceLocation texture = new ResourceLocation("portalmodexpanded", "textures/entity/vintage_cube_dropper_door.png");
            IVertexBuilder iVertexBuilder = renderBuffer.getBuffer(RenderType.entitySolid(texture));
                matrixStack.pushPose();
                matrixStack.translate(0.5, 0.5, 0.5);
                model.renderToBuffer(matrixStack, iVertexBuilder, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
                matrixStack.popPose();
            }
        }
    }
}
