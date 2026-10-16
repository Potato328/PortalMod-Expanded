package com.potato328.portalmodexpanded.block.sorted.antline;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.antline.*;

public class VintageAntlineBlock extends AntlineBlock {

    public VintageAntlineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEntity entity = ModTileEntities.VINTAGE_ANTLINE.get().create();
        return entity;
    }
}