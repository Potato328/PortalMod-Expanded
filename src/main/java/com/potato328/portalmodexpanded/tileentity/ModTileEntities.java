package com.potato328.portalmodexpanded.tileentity;

import com.potato328.portalmodexpanded.PortalModExpanded;
import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.portalmod.core.init.TileEntityTypeInit;

import java.util.Set;

public class ModTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, PortalModExpanded.modid);

    public static final RegistryObject<TileEntityType<ErodedFizzlerTileEntity>> ERODED_FIZZLER = TILE_ENTITY.register("eroded_fizzler", () -> TileEntityType.Builder.of(ErodedFizzlerTileEntity::new, TileEntityTypeInit.getBlocks(ModTileEntities::getErodedFizzlerBlocks)).build(null));

    public static final RegistryObject<TileEntityType<CameraP2TileEntity>> CAMERA_P2 = TILE_ENTITY.register("camera_p2", () -> TileEntityType.Builder.of(CameraP2TileEntity::new, TileEntityTypeInit.getBlocks(ModTileEntities::getCameraP2Blocks)).build(null));

    public static final RegistryObject<TileEntityType<SingleAutoPortalTileEntity>> SINGLE_AUTOPORTAL = TILE_ENTITY.register("single_autoportal", () -> TileEntityType.Builder.of(SingleAutoPortalTileEntity::new, TileEntityTypeInit.getBlocks(ModTileEntities::getSingleAutoPortalBlocks)).build(null));


    public static Set<Block> getErodedFizzlerBlocks(Set<Block> blocks) {
        blocks.add(ModBlocks.ERODED_FIZZLER_EMITTER.get());
        return blocks;
    }
    public static Set<Block> getCameraP2Blocks(Set<Block> blocks) {
        blocks.add(ModBlocks.CAMERA_P2.get());
        return blocks;
    }

    public static Set<Block> getSingleAutoPortalBlocks(Set<Block> blocks) {
        blocks.add(ModBlocks.SINGLE_AUTO_PORTAL.get());
        return blocks;
    }

}
