package com.potato328.portalmodexpanded.tileentity.sorted.fizzler;

import com.potato328.portalmodexpanded.block.ModBlocks;
import com.potato328.portalmodexpanded.block.sorted.fizzler.VintageFizzlerEmitterBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.portalmod.common.sorted.fizzler.FizzlerEmitterTileEntity;
import net.portalmod.common.sorted.fizzler.FizzlerFieldBlock;

public class VintageFizzlerTileEntity extends FizzlerEmitterTileEntity {



    @Override
    public void setField(boolean active, int distance, Direction facing, Direction upDirection) {

        boolean rotated = this.getBlockState().getValue(VintageFizzlerEmitterBlock.ROTATED);

        for(int i = 1; i < distance; i++) {

            if(active) {

                BlockState fizzlerField = ModBlocks.VINTAGE_FIZZLER_FIELD.get().defaultBlockState()
                        .setValue(FizzlerFieldBlock.ROTATED, rotated)
                        .setValue(FizzlerFieldBlock.AXIS, facing.getAxis());

                this.level.setBlock(this.getBlockPos().relative(facing, i), fizzlerField, 2);
                this.level.setBlock(this.getBlockPos().relative(facing, i).relative(upDirection), fizzlerField.setValue(FizzlerFieldBlock.HALF, DoubleBlockHalf.UPPER), 2);

            } else {


                this.level.setBlock(this.getBlockPos().relative(facing, i), Blocks.AIR.defaultBlockState(), 2);
                this.level.setBlock(this.getBlockPos().relative(facing, i).relative(upDirection), Blocks.AIR.defaultBlockState(), 2);

            }
        }

    }

    public boolean isOtherSide(BlockState state, Direction direction, boolean upper) {
        if (state.getBlock() instanceof VintageFizzlerEmitterBlock) {
            return state.getValue(VintageFizzlerEmitterBlock.FACING) == direction.getOpposite()
                    && upper == (state.getValue(VintageFizzlerEmitterBlock.HALF) == DoubleBlockHalf.UPPER);
        }

        return false;
    }


}
