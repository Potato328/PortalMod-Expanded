package com.potato328.portalmodexpanded.client.renderer.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class P1AutoPortalModel extends EntityModel<Entity> {
    public final ModelRenderer frame;

    public P1AutoPortalModel() {
        texWidth = 64;
        texHeight = 64;

        frame = new ModelRenderer(this);
        frame.setPos(-11.0F, 39.0F, 1.05F);
        frame.texOffs(0, 0).addBox(0.0F, -30.0F, -1.05F, 2.0F, 30.0F, 2.0F, 0.0F, false);
        frame.texOffs(8, 0).addBox(20.0F, -30.0F, -1.05F, 2.0F, 30.0F, 2.0F, 0.0F, false);
        frame.texOffs(0, 32).addBox(0.0F, 0.0F, -1.05F, 2.0F, 1.0F, 1.0F, 0.0F, false);
        frame.texOffs(6, 34).addBox(0.0F, -31.0F, -1.05F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        frame.texOffs(0, 34).addBox(20.0F, 0.0F, -1.05F, 2.0F, 1.0F, 1.0F, 0.0F, true);
        frame.texOffs(6, 32).addBox(20.0F, -31.0F, -1.05F, 2.0F, 1.0F, 1.0F, 0.0F, true);
    }
    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        frame.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}