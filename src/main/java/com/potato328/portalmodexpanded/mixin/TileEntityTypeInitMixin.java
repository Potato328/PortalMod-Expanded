package com.potato328.portalmodexpanded.mixin;

import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.block.Block;
import net.portalmod.core.init.TileEntityTypeInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(TileEntityTypeInit.class)
public class TileEntityTypeInitMixin {
    @Inject(method="getChamberDoorBlocks", at=@At("HEAD"), remap = false)
    private static void addChamberDoorBlocks(Set<Block> blocks, CallbackInfoReturnable<Set<Block>> cir) {
        blocks.add(ModBlocks.ARBORED_CHAMBER_DOOR.get());
        blocks.add(ModBlocks.ERODED_CHAMBER_DOOR.get());
        blocks.add(ModBlocks.ARBORED_CHAMBER_DOOR_P1.get());
    }

    @Inject(method="getCubeDropperBlocks", at=@At("HEAD"), remap = false)
    private static void addCubeDropperBlocks(Set<Block> blocks, CallbackInfoReturnable<Set<Block>> cir) {
        blocks.add(ModBlocks.ARBORED_CUBE_DROPPER.get());
    }

    @Inject(method="getFizzlerEmitterBlocks", at=@At("HEAD"), remap = false)
    private static void addFizzlerBlocks(Set<Block> blocks, CallbackInfoReturnable<Set<Block>> cir) {
        blocks.add(ModBlocks.ERODED_FIZZLER_EMITTER.get());
    }

    @Inject(method="getAutoPortalBlocks", at=@At("HEAD"), remap = false)
    private static void addAutoPortalBlocks(Set<Block> blocks, CallbackInfoReturnable<Set<Block>> cir) {
        blocks.add(ModBlocks.SINGLE_AUTO_PORTAL.get());
    }
}
