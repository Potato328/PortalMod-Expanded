package com.potato328.portalmodexpanded.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.potato328.portalmodexpanded.block.TileFrameBlock_;
import com.potato328.portalmodexpanded.tileentity.FrameTileTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FrameTilesTER extends TileEntityRenderer<FrameTileTileEntity> {

    public FrameTilesTER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public static final RenderMaterial renderMaterial = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("portalmodexpanded", "entity/tile"));

    @Override
    public void render(FrameTileTileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        switch (tileEntity.getBlockState().getValue(TileFrameBlock_.TILE1)) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
        }
    }
}
