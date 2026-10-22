package com.potato328.portalmodexpanded.mixin.client;

import com.potato328.portalmodexpanded.block.sorted.cubedropper.VintageCubeDropperTileEntity;
import com.potato328.portalmodexpanded.client.renderer.VintageCubeDropperTER;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TileEntityRendererDispatcher.class)
public class TileEntityRenderDispatcherMixin {
    private VintageCubeDropperTER vintageCubeDropperTERInstance;

    @Inject(method = "getRenderer", at = @At("HEAD"), cancellable = true)
    private <T extends TileEntity> void portalmodexpanded$getRenderer(T te, CallbackInfoReturnable<TileEntityRenderer<T>> cir) {
        if(te instanceof VintageCubeDropperTileEntity) {
            if(vintageCubeDropperTERInstance == null) {
                vintageCubeDropperTERInstance = new VintageCubeDropperTER((TileEntityRendererDispatcher) (Object) this);
            }
            cir.setReturnValue((TileEntityRenderer<T>) vintageCubeDropperTERInstance);
        }
    }
}
