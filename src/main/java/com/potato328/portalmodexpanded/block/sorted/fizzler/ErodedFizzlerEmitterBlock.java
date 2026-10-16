package com.potato328.portalmodexpanded.block.sorted.fizzler;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import net.portalmod.common.sorted.fizzler.FizzlerEmitterBlock;
import net.portalmod.common.sorted.fizzler.FizzlerFieldBlock;


public class ErodedFizzlerEmitterBlock extends FizzlerEmitterBlock {

    public ErodedFizzlerEmitterBlock(Properties properties) {

        super(properties);

        this.registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ROTATED, false)
                .setValue(ACTIVE, false)
                .setValue(HALF, DoubleBlockHalf.LOWER));

    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return this.hasTileEntity(state) ? ModTileEntities.ERODED_FIZZLER.get().create() : null;
    }
    @Override
    public boolean isValidConnection(BlockState state, BlockState neighbor) {

        if (neighbor.getBlock() instanceof FizzlerFieldBlock) {

            return neighbor.getValue(FizzlerFieldBlock.AXIS) == state.getValue(FACING).getAxis()
                    && neighbor.getValue(FizzlerFieldBlock.ROTATED) == state.getValue(ROTATED)
                    && neighbor.getValue(FizzlerFieldBlock.HALF) == state.getValue(HALF);

        }

        if (neighbor.getBlock() instanceof ErodedFizzlerEmitterBlock) {

            return neighbor.getValue(FACING) == state.getValue(FACING).getOpposite()
                    && neighbor.getValue(HALF) == state.getValue(HALF)
                    && neighbor.getValue(ROTATED) == state.getValue(ROTATED)
                    && neighbor.getValue(ACTIVE);

        }

        return false;
    }
}
