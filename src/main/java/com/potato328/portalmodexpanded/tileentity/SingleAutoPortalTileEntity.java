package com.potato328.portalmodexpanded.tileentity;

import com.potato328.portalmodexpanded.block.SingleAutoPortal;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.portalmod.common.sorted.antline.indicator.IndicatorInfo;
import net.portalmod.common.sorted.autoportal.AutoPortalTileEntity;
import net.portalmod.common.sorted.portal.PortalColors;
import net.portalmod.common.sorted.portal.PortalEntity;
import net.portalmod.common.sorted.portal.PortalPlacer;
import net.portalmod.core.math.Vec3;
import net.portalmod.core.util.ChangeDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SingleAutoPortalTileEntity extends AutoPortalTileEntity {

    private final ChangeDetector powerChangeDetector = new ChangeDetector();

    public SingleAutoPortalTileEntity() {
        super(ModTileEntities.SINGLE_AUTOPORTAL.get());
    }

    public void setPowered(boolean value) {
        powerChangeDetector.set(value);
    }

    @Override
    public void tick() {
        if(this.level == null || this.level.isClientSide)
            return;

        BlockState blockState = this.getBlockState();
        if(!(blockState.getBlock() instanceof SingleAutoPortal))
            return;

        this.checkLastOpened();

        IndicatorInfo indicatorInfo = this.checkIndicators(blockState, this.level, this.getBlockPos());
        boolean isPowered = blockState.getValue(SingleAutoPortal.POWERED);

        if(indicatorInfo.hasIndicators) {
            if(isPowered != indicatorInfo.allIndicatorsActivated) {
                ((SingleAutoPortal)blockState.getBlock()).setAntlinePowered(indicatorInfo.allIndicatorsActivated, blockState, this.level, this.getBlockPos());

                if(indicatorInfo.allIndicatorsActivated) {
                    openPortal(blockState);
                    return;
                }
            }
        }

        if(powerChangeDetector.isRising()) openPortal(blockState);

        powerChangeDetector.shift();
    }
    private void openPortal(BlockState blockState) {
        Direction facing = this.getBlockState().getValue(SingleAutoPortal.FACING);
        Direction direction = this.getBlockState().getValue(SingleAutoPortal.DIRECTION);
        Tuple<Direction, Direction> directions = ((SingleAutoPortal) blockState.getBlock()).placementDirectionsFromFacingAndDirection(facing, direction);
        Direction left = directions.getA();
        Direction up = directions.getB();

        if(facing.getAxisDirection() == Direction.AxisDirection.POSITIVE)
            left = left.getOpposite();

        Vec3 position = new Vec3(this.getBlockPos());

        //There is certainly a better way to do this, but that requires math that I don't know, and don't feel like doing.
        switch(facing) {
            case NORTH:

                position = new Vec3(this.getBlockPos())
                        .add(new Vec3(left.getOpposite()).mul(-0.5))
                        .add(new Vec3(up).mul(1))
                        .add(new Vec3(facing.getOpposite()).mul(1));
                break;
            case SOUTH:

                position = new Vec3(this.getBlockPos())
                        .add(new Vec3(left.getOpposite()).mul(0.5))
                        .add(new Vec3(up).mul(1))
                        .add(new Vec3(facing.getOpposite()).mul(0));
                break;
            case EAST:
                position = new Vec3(this.getBlockPos())
                        .add(new Vec3(left.getOpposite()).mul(-0.5))
                        .add(new Vec3(up).mul(1))
                        .add(new Vec3(facing.getOpposite()).mul(0));
                break;
            case WEST:
                position = new Vec3(this.getBlockPos())
                        .add(new Vec3(left.getOpposite()).mul(0.5))
                        .add(new Vec3(up).mul(1))
                        .add(new Vec3(facing.getOpposite()).mul(1));
                break;
            case UP:
                if(direction == Direction.NORTH) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(0.5))
                            .add(new Vec3(up).mul(-1))
                            .add(new Vec3(facing.getOpposite()).mul(0));
                }
                if(direction == Direction.EAST) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(0.5))
                            .add(new Vec3(up).mul(1))
                            .add(new Vec3(facing.getOpposite()).mul(0));
                }
                if(direction == Direction.SOUTH) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(-0.5))
                            .add(new Vec3(up).mul(1))
                            .add(new Vec3(facing.getOpposite()).mul(0));
                }
                if(direction == Direction.WEST) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(-0.5))
                            .add(new Vec3(up).mul(-1))
                            .add(new Vec3(facing.getOpposite()).mul(0));
                }
                break;
            case DOWN:
                if(direction == Direction.NORTH) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(-0.5))
                            .add(new Vec3(up).mul(-1))
                            .add(new Vec3(facing.getOpposite()).mul(1));
                }
                if(direction == Direction.EAST) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(-0.5))
                            .add(new Vec3(up).mul(1))
                            .add(new Vec3(facing.getOpposite()).mul(1));
                }
                if(direction == Direction.SOUTH) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(0.5))
                            .add(new Vec3(up).mul(1))
                            .add(new Vec3(facing.getOpposite()).mul(1));
                }
                if(direction == Direction.WEST) {
                    position = new Vec3(this.getBlockPos())
                            .add(new Vec3(left.getOpposite()).mul(0.5))
                            .add(new Vec3(up).mul(-1))
                            .add(new Vec3(facing.getOpposite()).mul(1));
                }
                break;

        }

        if(this.gunUUID != null && this.end != null && this.primaryColor != null && this.secondaryColor != null) {
            Optional<Integer> colorIndex = this.getCurrentColorIndex();
            if(colorIndex.isPresent()) {
                String color = PortalColors.values()[colorIndex.get()].name();
                PortalEntity portal = PortalPlacer.placePortal(this.level, this.end, color, this.gunUUID,
                        position, facing, up, true, null, null);
                System.out.println("[Portal Mod Expanded] Attempting to open portal at: " + position + ", facing: " + facing);

                if(portal != null) {
                    this.lastOpenedUUID = portal.getUUID();
                }

                this.sendUpdate();
            }
        }
    }

    private void checkLastOpened() {
        if(this.level == null || this.lastOpenedUUID == null)
            return;

        PortalEntity portal = (PortalEntity)((ServerWorld)this.level).getEntity(this.lastOpenedUUID);

        if(portal == null || !portal.isAlive()) {
            this.lastOpenedUUID = null;
            this.sendUpdate();
        }
    }

    @Override
    public List<BlockPos> getIndicatorPositions(BlockState state, World world, BlockPos pos) {
        Direction up = ((SingleAutoPortal) state.getBlock()).getUpperDirection(state);
        Direction down = up.getOpposite();
        Direction backwards = state.getValue(SingleAutoPortal.FACING).getOpposite();
        Vector3i perpendicular = up.getNormal().cross(backwards.getNormal());
        Direction side = Direction.fromNormal(perpendicular.getX(), perpendicular.getY(), perpendicular.getZ());

        return new ArrayList<>(Arrays.asList(
                pos.relative(up),
                pos.relative(down),
                pos.relative(side),
                pos.relative(side.getOpposite()),
                pos.relative(side).relative(up),
                pos.relative(side.getOpposite()).relative(up),
                pos.relative(side).relative(down),
                pos.relative(side.getOpposite()).relative(down),
                pos.relative(up).relative(up),
                pos.relative(side).relative(up).relative(up),
                pos.relative(side.getOpposite()).relative(up).relative(up)
        ));
    }



}
