package com.potato328.portalmodexpanded.client.renderer.model;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class VintageCubeDropperDoorModel extends EntityModel<Entity> {
	private final ModelRenderer door;
	private final ModelRenderer cube_r1;

	public VintageCubeDropperDoorModel() {

		texWidth = 64;
		texHeight = 64;

		door = new ModelRenderer(this);
		door.setPos(0.0F, 24.0F, 0.0F);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-3.0F, -36.0F, 8.0F);
		door.addChild(cube_r1);
		setRotationAngle(cube_r1, 3.1416F, 3.1416F, 0.0F);
		cube_r1.texOffs(0, 0).addBox(-1.0F, 12.0F, -12.0F, 2.0F, 24.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		door.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}