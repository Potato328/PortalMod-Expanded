package com.potato328.portalmodexpanded.block.sorted.cubedropper;

import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

import net.portalmod.common.sorted.antline.indicator.IndicatorInfo;
import net.portalmod.common.sorted.cubedropper.CubeDropperBlock;
import net.portalmod.common.sorted.cubedropper.CubeDropperTileEntity;


public class VintageCubeDropperTileEntity extends CubeDropperTileEntity {

    public VintageCubeDropperTileEntity() {
        super();
    }

    @Override
    public void tick() {
        BlockState blockState = this.getBlockState();
        BlockPos pos = this.getBlockPos();

        CubeDropperBlock dropperBlock = (CubeDropperBlock) blockState.getBlock();
        IndicatorInfo indicatorInfo = this.checkIndicators(blockState, this.level, pos);
        boolean active = !indicatorInfo.hasIndicators || indicatorInfo.allIndicatorsActivated;

        if (this.openTicks > 0) this.openTicks++;

        if (blockState.getBlock() instanceof CubeDropperBlock && this.entityNBT != null) {
            this.updateEntities();

            if (active && this.openTicks == 0 && (this.entityUUIDs.size() == 1 || !this.wasActive)) {
                openDropper(dropperBlock);
            }
        }

        this.wasActive = active;

        if (this.openTicks > 40) {
            closeDropper(dropperBlock);
        }
    }
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.load(this.getBlockState(), pkt.getTag());
    }


    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        nbt = super.save(nbt);
        if (!this.entityUUIDs.isEmpty()) {
            nbt.putUUID("UUID1", this.entityUUIDs.get(0));
        }
        if (this.entityUUIDs.size() >= 2) {
            nbt.putUUID("UUID2", this.entityUUIDs.get(1));
        }
        nbt.putBoolean("Powered", this.wasActive);
        nbt.putInt("OpenTicks", this.openTicks);

        if (this.entityNBT != null) {
            nbt.put("Entity", this.entityNBT);
        }

        return nbt;
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);
        this.entityUUIDs.clear();
        if (nbt.hasUUID("UUID1")) {
            this.entityUUIDs.add(nbt.getUUID("UUID1"));
        }
        if (nbt.hasUUID("UUID2")) {
            this.entityUUIDs.add(nbt.getUUID("UUID2"));
        }
        this.wasActive = nbt.getBoolean("Powered");
        this.openTicks = nbt.getInt("OpenTicks");

        this.entityNBT = null;
        if (nbt.contains("Entity")) {
            this.entityNBT = nbt.getCompound("Entity");
        }
    }
}
