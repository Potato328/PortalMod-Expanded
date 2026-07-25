package com.potato328.portalmodexpanded.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.potato328.portalmodexpanded.client.renderer.model.CameraHeadP2Model;
import com.potato328.portalmodexpanded.tileentity.CameraP2TileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class CameraP2TER extends TileEntityRenderer<CameraP2TileEntity> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("portalmodexpanded:entity/camerahead");
    //public static final ResourceLocation TEXTURE = new ResourceLocation("entity/enchanting_table_book");
    public static final RenderMaterial renderMaterial = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("portalmodexpanded", "entity/camerahead"));


    //private final CameraP2Model model = new CameraP2Model();
    private final CameraHeadP2Model model = new CameraHeadP2Model();

    public CameraP2TER(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(CameraP2TileEntity tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        matrixStack.pushPose();
        matrixStack.translate(0.5D, 0D, 0.5D);


        matrixStack.mulPose(Vector3f.YP.rotation(-tileEntity.tXRot));
        matrixStack.mulPose(Vector3f.ZP.rotation(-tileEntity.tYRot));


        IVertexBuilder ivertexbuilder = renderMaterial.buffer(buffer, RenderType::entityCutout);

        this.model.renderToBuffer(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);

        matrixStack.popPose();

    }

}
