package com.potato328.portalmodexpanded.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ShootableItem;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.portalmod.common.blocks.FrameBlock;
import net.portalmod.common.items.WrenchItem;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;
import net.portalmod.core.util.ModUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.portalmod.core.util.ModUtil.TOOLTIP_STYLE;


public class TileFrameBlock extends FrameBlock {

    public static final IntegerProperty TILE1 = IntegerProperty.create("tile1", 0, 7);
    public static final IntegerProperty TILE2 = IntegerProperty.create("tile2", 0, 7);
    public static final IntegerProperty TILE3 = IntegerProperty.create("tile3", 0, 7);
    public static final IntegerProperty TILE4 = IntegerProperty.create("tile4", 0, 7);
    public TileFrameBlock(Properties properties, boolean isFilled) {
        super(properties, isFilled);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.UP)
                .setValue(BEAM, false)
                .setValue(WATERLOGGED, false)
                .setValue(TILE1, 0)
                .setValue(TILE2, 0)
                .setValue(TILE3, 0)
                .setValue(TILE4, 0));
        //this.initAABBs();
    }

    private static final Map<Direction, VoxelShapeGroup> FILLED_SHAPE = new HashMap<>();
    private static final Map<Direction, VoxelShapeGroup> HOLLOW_SHAPE = new HashMap<>();

    /*private void initAABBs() {
        VoxelShapeGroup filledShape = new VoxelShapeGroup.Builder()
                .add(0, 0, 0, 2, 16, 16)
                .addPart("beam", 0, 5, 5, 16, 11, 11)
                .addPart("beam_collision", 0, 4.5, 4.5, 16, 11.5, 11.5)
                .build();
        VoxelShapeGroup hollowShape = new VoxelShapeGroup.Builder()
                .add(0,0, 0, 2, 1,16)
                .add(0,15,0, 2,16,16)
                .add(0,1, 0, 2,15, 1)
                .add(0,1, 15,2,15,16)
                .addPart("beam", 0, 5, 5, 16, 11, 11)
                .addPart("beam_collision", 0, 4.5, 4.5, 16, 11.5, 11.5)
                .build();



        for(Direction facing : Direction.values()) {
            Mat4 matrix = Mat4.identity();
            matrix.translate(new Vec3(.5));

            if(facing.getAxis() == Direction.Axis.Y) {
                int angle = (facing.getAxisDirection() == Direction.AxisDirection.NEGATIVE) ? 90 : -90;
                matrix.rotateDeg(Vector3f.ZP, angle);
            } else {
                int angle = facing.get2DDataValue() * -90 + 90;
                matrix.rotateDeg(Vector3f.YP, angle);
            }

            matrix.translate(new Vec3(-.5));

            FILLED_SHAPE.put(facing, filledShape.clone().transform(matrix));
            HOLLOW_SHAPE.put(facing, hollowShape.clone().transform(matrix));
        }
    }*/
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {

        VoxelShapeGroup.Builder filledShapeUnbuilt = new VoxelShapeGroup.Builder()
                .add(1, 0, 0, 3, 16, 16)
                .addPart("beam", 0, 5, 5, 16, 11, 11)
                .addPart("beam_collision", 0, 4.5, 4.5, 16, 11.5, 11.5);
        VoxelShapeGroup.Builder hollowShapeUnbuilt = new VoxelShapeGroup.Builder()
                .add(1,0, 0, 3, 1,16)
                .add(1,15,0, 3,16,16)
                .add(1,1, 0, 3,15, 1)
                .add(1,1, 15,3,15,16)
                .addPart("beam", 0, 5, 5, 16, 11, 11)
                .addPart("beam_collision", 0, 4.5, 4.5, 16, 11.5, 11.5);


        if(state.getValue(TILE1) > 0) {
            filledShapeUnbuilt.add(0, 8, 8, 1, 16, 16);
            hollowShapeUnbuilt.add(0, 0, 8, 1, 16, 16);
        }
        if(state.getValue(TILE2) > 0) {
            filledShapeUnbuilt.add(0, 0, 8, 1, 8, 16);
            hollowShapeUnbuilt.add(0, 0, 8, 1, 8, 16);
        }
        if(state.getValue(TILE3) > 0) {
            filledShapeUnbuilt.add(0, 0, 0, 1, 8, 8);
            hollowShapeUnbuilt.add(0, 0, 0, 1, 8, 8);
        }

        if(state.getValue(TILE4) > 0) {
            filledShapeUnbuilt.add(0, 8, 0, 1, 16, 8);
            hollowShapeUnbuilt.add(0, 8, 0, 1, 16, 8);
        }

        VoxelShapeGroup hollowShape = hollowShapeUnbuilt.build();
        VoxelShapeGroup filledShape = filledShapeUnbuilt.build();

        for(Direction facing : Direction.values()) {
            Mat4 matrix = Mat4.identity();
            matrix.translate(new Vec3(.5));

            if (facing.getAxis() == Direction.Axis.Y) {
                int angle = (facing.getAxisDirection() == Direction.AxisDirection.NEGATIVE) ? 90 : -90;
                matrix.rotateDeg(Vector3f.ZP, angle);
            } else {
                int angle = facing.get2DDataValue() * -90 + 90;
                matrix.rotateDeg(Vector3f.YP, angle);
            }

            matrix.translate(new Vec3(-.5));

            FILLED_SHAPE.put(facing, filledShape.clone().transform(matrix));
            HOLLOW_SHAPE.put(facing, hollowShape.clone().transform(matrix));
        }

        if (FRAME_BLOCKS.isEmpty()) {
            DefaultedRegistry.BLOCK.stream().filter(this::holdingCausesFullHitbox).forEach(block -> FRAME_BLOCKS.add(block));
        }

        boolean holdingFrame = FRAME_BLOCKS.stream().anyMatch(block -> context.isHoldingItem(block.asItem()));

        boolean hasBeam = state.getValue(BEAM);
        return (this.isFilled || holdingFrame ? FILLED_SHAPE : HOLLOW_SHAPE).get(state.getValue(FACING)).getVariant(hasBeam ? "beam" : "");
    }


    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BEAM);
        builder.add(WATERLOGGED);
        builder.add(TILE1);
        builder.add(TILE2);
        builder.add(TILE3);
        builder.add(TILE4);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        ItemUseContext context = new ItemUseContext(player, hand, result);
        BlockState cycle;

        if (player.getItemInHand(hand).getItem() instanceof WrenchItem) {

            double x = context.getClickLocation().x - pos.getX();
            double y = context.getClickLocation().y - pos.getY();
            double z = context.getClickLocation().z - pos.getZ();
            switch(blockState.getValue(FACING)) {
                case NORTH:
                    if(x >= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x >= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x <= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x <= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                case SOUTH:
                    if(x >= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x >= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x <= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(x <= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                case EAST:
                    if(z <= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                case WEST:
                    if(z >= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && y <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && y >= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                case UP:
                    if(z <= 0.5 && x >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && x >= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && x <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && x <= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                case DOWN:
                    if(z >= 0.5 && x >= 0.5) {
                        cycle = blockState.cycle(TILE1);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && x >= 0.5) {
                        cycle = blockState.cycle(TILE2);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z <= 0.5 && x <= 0.5) {
                        cycle = blockState.cycle(TILE3);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
                    if(z >= 0.5 && x <= 0.5) {
                        cycle = blockState.cycle(TILE4);
                        WrenchItem.playUseSound(world, result.getLocation());
                        world.setBlockAndUpdate(pos, cycle);
                        return ActionResultType.SUCCESS;
                    }
            }
        }
        return ActionResultType.PASS;
    }
    private static String getModifierKeyName() {
        return Util.getPlatform() == Util.OS.OSX
                ? "Command"
                : "Ctrl";
    }
    public static IFormattableTextComponent tooltipComponent(String key, Object... args) {
        return new TranslationTextComponent(key, args).setStyle(TOOLTIP_STYLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader blockReader, List<ITextComponent> list, ITooltipFlag flag) {
        if (!Screen.hasControlDown()) {
            list.add(tooltipComponent("tooltip.portalmod.hold_control", getModifierKeyName()));
            return;
        }
        list.add(tooltipComponent("tooltip.portalmodexpanded.tile_frame_1"));
        list.add(tooltipComponent("tooltip.portalmodexpanded.tile_frame_2"));
    }
}

