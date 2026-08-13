package com.potato328.portalmodexpanded.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LunecastChamberLightBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final BooleanProperty CORNER = BooleanProperty.create("corner");
    public static final BooleanProperty CEILING = BooleanProperty.create("ceiling");

    //public static final IntegerProperty CONNECT = IntegerProperty.create("connect", 0, 3);

    public LunecastChamberLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ACTIVE, true)
                .setValue(CORNER, false)
                .setValue(CEILING, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(ACTIVE);
        builder.add(CORNER);
        builder.add(CEILING);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        BlockPos pos = context.getClickedPos();
        double y = context.getClickLocation().y - (double)pos.getY();

        if(y >= .5) {
            return state.setValue(CEILING, true);
        } else {
            return state;
        }
    }

}
