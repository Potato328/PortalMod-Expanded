package com.potato328.portalmodexpanded.block.sorted.chamberdoor;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.portalmod.common.sorted.antline.indicator.IndicatorInfo;
import net.portalmod.common.sorted.door.ChamberDoorTileEntity;
import net.portalmod.core.math.Vec3;

public class VintageChamberDoorTileEntity extends ChamberDoorTileEntity {


    @Override
    public void tick() {
        BlockState blockState = this.getBlockState();
        if (!(blockState.getBlock() instanceof VintageChamberDoor)) {
            return;
        }
        VintageChamberDoor doorBlock = (VintageChamberDoor) blockState.getBlock();
        BlockPos pos = this.getBlockPos();
        World world = this.level;
        Direction facing = blockState.getValue(VintageChamberDoor.FACING);

        IndicatorInfo indicatorInfo = this.checkIndicators(blockState, world, pos);
        boolean isOpen = blockState.getValue(VintageChamberDoor.OPEN);

        // Powered by indicators
        if (indicatorInfo.hasIndicators) {
            if (isOpen != indicatorInfo.allIndicatorsActivated) {
                doorBlock.setOpen(indicatorInfo.allIndicatorsActivated, blockState, world, pos);
            }
            return;
        }

        Vector3d middlePos = VintageChamberDoor.getExactMiddlePos(blockState, pos);
        int changeProximity = isOpen ? 4 : 3;  // Open in 3 blocks, close in 4

        // Powered by nearby player
        boolean hasNearbyPlayer = false;
        for (PlayerEntity player : world.players()) {
            if (player.isSpectator()) {
                continue;
            }
            boolean inFront = player.position().subtract(middlePos).multiply(1, 0.5, 1).dot(new Vec3(facing.getNormal()).to3d()) > 0;
            double playerDistance = player.position().distanceTo((middlePos));
            if (playerDistance < changeProximity && inFront || isOpen && playerDistance < 1.5) {
                hasNearbyPlayer = true;
                break;
            }
        }

        if (isOpen != hasNearbyPlayer) {
            doorBlock.setOpen(hasNearbyPlayer, blockState, world, pos);
        }
    }
}
