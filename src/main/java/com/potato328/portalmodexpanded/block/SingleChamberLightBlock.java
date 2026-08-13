package com.potato328.portalmodexpanded.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.portalmod.common.blocks.ChamberLightsBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Random;

public class SingleChamberLightBlock extends ChamberLightsBlock {

    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");
    public static final BooleanProperty ACTIVE =  BooleanProperty.create("active");
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty MIRROR = BooleanProperty.create("mirror");
    public static final BooleanProperty TILE = BooleanProperty.create("tile");

    public SingleChamberLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(stateDefinition.any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(ACTIVE, true)
                .setValue(POWERED, false)
                .setValue(ROTATED, false)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(MIRROR, false)
                .setValue(TILE, true));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AXIS, ACTIVE, POWERED, HALF, ROTATED, MIRROR, TILE);
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (context.getPlayer() == null) return null;
        Direction.Axis axis = context.getPlayer().getDirection().getAxis() == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
        BlockPos pos = context.getClickedPos();
        Direction clickedFace = context.getClickedFace();
        double x = context.getClickLocation().x - pos.getX();
        double y = context.getClickLocation().y - pos.getY();
        double z = context.getClickLocation().z - pos.getZ();

        boolean prefersHorizontal = context.getPlayer() != null && context.getPlayer().isShiftKeyDown();

        Optional<DoubleBlockHalf> verticalTopHalf = getPlacementHalf(context, Direction.UP);
        Optional<DoubleBlockHalf> horizontalTopHalf = getPlacementHalf(context, Direction.fromAxisAndDirection(axis, Direction.AxisDirection.POSITIVE));

        if (!verticalTopHalf.isPresent() && !horizontalTopHalf.isPresent()) {
            return null;
        }

        boolean willBeHorizontal = prefersHorizontal && horizontalTopHalf.isPresent() || !verticalTopHalf.isPresent();

        BlockState blockstate = this.defaultBlockState().setValue(HALF, willBeHorizontal ? horizontalTopHalf.get() : verticalTopHalf.get()).setValue(ACTIVE, new Random().nextBoolean());

        if (willBeHorizontal) {
            if(clickedFace == Direction.UP && context.getNearestLookingDirection().getAxis() == Direction.Axis.Y) {
                if(context.getHorizontalDirection().getAxis() == Direction.Axis.X) {
                    if (x > 0.5) {
                        return blockstate.setValue(AXIS, axis).setValue(ROTATED, true);
                    }
                    if (x < 0.5) {
                        return blockstate.setValue(AXIS, axis).setValue(ROTATED, true).setValue(MIRROR, true);
                    }
                }
                if(context.getHorizontalDirection().getAxis() == Direction.Axis.Z) {
                    if (z > 0.5) {
                        return blockstate.setValue(AXIS, axis).setValue(ROTATED, true);
                    }
                    if (z < 0.5) {
                        return blockstate.setValue(AXIS, axis).setValue(ROTATED, true).setValue(MIRROR, true);
                    }
                }
            } else {
                if (y > 0.5) {
                    return blockstate.setValue(AXIS, axis).setValue(ROTATED, false);
                }
                if (y < 0.5) {
                    return blockstate.setValue(AXIS, axis).setValue(ROTATED, false).setValue(MIRROR, true);
                }
            }

        }
        if(context.getHorizontalDirection().getAxis() == Direction.Axis.X) {
            if(z < 0.5) {
                return blockstate.setValue(ROTATED, true);
            }
            if(z > 0.5) {
                return blockstate.setValue(ROTATED, true).setValue(MIRROR, true);
            }

        }
        if(context.getHorizontalDirection().getAxis() == Direction.Axis.Z) {
            if(x < 0.5) {
                return blockstate.setValue(ROTATED, false);
            }
            if(x > 0.5) {
                return blockstate.setValue(ROTATED, false).setValue(MIRROR, true);
            }

        }
        return blockstate;
    }

}
