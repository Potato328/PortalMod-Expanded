package com.potato328.portalmodexpanded.block;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import com.potato328.portalmodexpanded.tileentity.SingleAutoPortalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.portalmod.common.blocks.DoubleBlock;
import net.portalmod.common.items.WrenchItem;
import net.portalmod.common.sorted.portal.PortalColors;
import net.portalmod.common.sorted.portal.PortalEnd;
import net.portalmod.common.sorted.portalgun.PortalGun;
import net.portalmod.core.math.Mat4;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.math.VoxelShapeGroup;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class SingleAutoPortal extends DoubleBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final DirectionProperty DIRECTION = DirectionProperty.create("direction", Direction.Plane.HORIZONTAL);

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public SingleAutoPortal(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(DIRECTION, Direction.NORTH)
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(POWERED, false));

    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.SINGLE_AUTOPORTAL.get().create();
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(!WrenchItem.usedWrench(player, hand))
            return ActionResultType.PASS;

        if(level.isClientSide)
            return ActionResultType.CONSUME;

        Block block = state.getBlock();
        if(!(block instanceof SingleAutoPortal))
            return ActionResultType.PASS;

        BlockPos tePos = getMainPosition(state, pos);

        TileEntity te = level.getBlockEntity(tePos);
        if(!(te instanceof SingleAutoPortalTileEntity))
            return ActionResultType.PASS;

        SingleAutoPortalTileEntity autoPortal = (SingleAutoPortalTileEntity)te;

        if(player.getOffhandItem().getItem() instanceof PortalGun) {
            ItemStack itemStack = player.getOffhandItem();
            Optional<UUID> uuid = PortalGun.getUUID(itemStack);

            if(!uuid.isPresent() || !itemStack.hasTag())
                return ActionResultType.PASS;

            CompoundNBT nbt = itemStack.getTag();

            if(nbt != null) {
                if(!nbt.contains("LeftColor") || !nbt.contains("RightColor"))
                    return ActionResultType.PASS;

                int primaryColor = PortalColors.getIndex(nbt.getString("LeftColor"));
                int secondaryColor = PortalColors.getIndex(nbt.getString("RightColor"));
                PortalEnd end = nbt.contains("Locked") && nbt.getString("Locked").equals("Left")
                        ? PortalEnd.PRIMARY : PortalEnd.SECONDARY;

                autoPortal.link(uuid.get(), end, primaryColor, secondaryColor);
                WrenchItem.playUseSound(level, rayTraceResult.getLocation());
                player.displayClientMessage(new TranslationTextComponent("actionbar.portalmod.autoportal.set"), true);
                return ActionResultType.SUCCESS;
            }
        } else {
            if(autoPortal.end == null) {
                WrenchItem.playFailSound(level, rayTraceResult.getLocation());
                return ActionResultType.SUCCESS;
            }

            if(autoPortal.lastOpenedUUID != null) {
                autoPortal.closePortal();
                WrenchItem.playUseSound(level, rayTraceResult.getLocation());
                return ActionResultType.SUCCESS;
            }

            autoPortal.swapEnd();
            WrenchItem.playUseSound(level, rayTraceResult.getLocation());

            player.displayClientMessage(new TranslationTextComponent("actionbar.portalmod.autoportal."
                    + (autoPortal.end == PortalEnd.PRIMARY ? "primary" : "secondary")), true);

            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }



    private VoxelShapeGroup getShapeGroup(BlockState state) {
        VoxelShapeGroup hitbox = new VoxelShapeGroup.Builder()
                .add(0, 0, 0, 1, 16, 3)
                .add(0, 0, 13, 1, 16, 16)
                .build();
        Direction facing = state.getValue(FACING);
        Direction direction = state.getValue(DIRECTION);

        Mat4 matrix = Mat4.identity();
        matrix.translate(new Vec3(.5));

        if (facing.getAxis() == Direction.Axis.Y) {
            matrix.rotateDeg(Vector3f.ZP, facing == Direction.UP ? 90 : -90);

            if (direction == Direction.NORTH) {
                matrix.rotateDeg(Vector3f.XP, facing == Direction.UP ? -90 : 90);
            }
            if (direction == Direction.EAST) {
                matrix.rotateDeg(Vector3f.XP, facing == Direction.UP ? -0 : 0);
            }
            if (direction == Direction.SOUTH) {
                matrix.rotateDeg(Vector3f.XP, facing == Direction.UP ? -90 : 90);
            }
            if (direction == Direction.WEST) {
                matrix.rotateDeg(Vector3f.XP, facing == Direction.UP ? -0 : 0);
            }

        } else {
            int angle = facing.get2DDataValue() * -90 - 90;
            matrix.rotateDeg(Vector3f.YP, angle);

        }

        matrix.translate(new Vec3(-.5));

        return hitbox.transform(matrix);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return this.getShapeGroup(state).getShape();
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }
    @Override
    public VoxelShape getVisualShape(BlockState state, IBlockReader level, BlockPos pos, ISelectionContext context) {
        return this.getShape(state, level, pos, context);
    }
    public Tuple<Direction, Direction> placementDirectionsFromFacingAndDirection(Direction facing, Direction direction) {
        if(facing.getAxis() == Direction.Axis.X)
            return new Tuple<>(Direction.NORTH, Direction.UP);
        if(facing.getAxis() == Direction.Axis.Z)
            return new Tuple<>(Direction.EAST, Direction.UP);
        return new Tuple<>(direction.getClockWise(), direction);
    }

    public void setAntlinePowered(boolean powered, BlockState blockState, World world, BlockPos pos) {
        this.setBlockStateValue(POWERED, powered, blockState, world, pos);
        this.updateAllNeighbors(world, pos, blockState);
    }

    @Override
    public void neighborChanged(BlockState state, World level, BlockPos pos, Block block, BlockPos neighborPos, boolean b) {
        if(level.isClientSide)
            return;

        Direction facing = state.getValue(FACING);

        boolean isPowered = getAllPositions(state, pos).stream()
                .filter(blockPos -> level.getBlockState(blockPos).getBlock() instanceof SingleAutoPortal)
                .anyMatch(checkingPos -> level.hasSignal(checkingPos.relative(facing.getOpposite()), facing));

        TileEntity te = level.getBlockEntity(getMainPosition(state, pos));

        if(te instanceof SingleAutoPortalTileEntity) {
            ((SingleAutoPortalTileEntity) te).setPowered(isPowered);
        }

        if(!state.canSurvive(level, pos)) {
            level.destroyBlock(pos, true, null, 0);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(DIRECTION);
        builder.add(HALF);
        builder.add(POWERED);
    }


    @Override
    public Direction getUpperDirection(BlockState state) {
        Direction.Axis axis = state.getValue(FACING).getAxis();

        //This is stupid, and there has to be a better way to do this, but this works correctly.
        if (state.getValue(DIRECTION) == Direction.NORTH && axis == Direction.Axis.Y) {
            return Direction.SOUTH;
        }
        if (state.getValue(DIRECTION) == Direction.EAST && axis == Direction.Axis.Y) {
            return Direction.EAST;
        }
        if (state.getValue(DIRECTION) == Direction.SOUTH && axis == Direction.Axis.Y) {
            return Direction.SOUTH;
        }
        if (state.getValue(DIRECTION) == Direction.WEST && axis == Direction.Axis.Y) {
            return Direction.EAST;
        }

            return Direction.UP;



    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getClickedFace();
        Direction horizontalDirection = context.getHorizontalDirection();




        BlockState blockState = this.defaultBlockState().setValue(FACING, direction);

        if (direction.getAxis() == Direction.Axis.Y) {
            Optional<DoubleBlockHalf> half = getPlacementHalf(context, Direction.fromAxisAndDirection(
                    context.getHorizontalDirection().getAxis(), Direction.AxisDirection.POSITIVE));

            if (!half.isPresent()) {
                return null;
            }

            return blockState.setValue(HALF, half.get()).setValue(DIRECTION, horizontalDirection);
        }

        Optional<DoubleBlockHalf> topHalf = getPlacementHalf(context, Direction.UP);

        if(!topHalf.isPresent()) {
            return null;
        }

        return blockState.setValue(HALF, topHalf.get());
    }

    @Override
    public boolean lookDirectionInfluencesLocation() {
        return true;
    }
}
