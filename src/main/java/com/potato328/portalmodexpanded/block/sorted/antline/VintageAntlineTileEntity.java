package com.potato328.portalmodexpanded.block.sorted.antline;

import com.potato328.portalmodexpanded.block.ModBlocks;
import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.portalmod.common.sorted.antline.AntlineBlock;
import net.portalmod.common.sorted.antline.AntlineTileEntity;

public class VintageAntlineTileEntity extends AntlineTileEntity {

    private static final AntlineTileEntity.SideMap NONE = new AntlineTileEntity.SideMap();
    private boolean hideSides;

    public VintageAntlineTileEntity() {
        super(ModTileEntities.VINTAGE_ANTLINE.get());
    }

    public AntlineTileEntity.SideMap getSideMap() {
        return this.hideSides ? NONE : super.getSideMap();
    }

    @Override
    protected void invalidateCaps() { // On removal of entity
        if (level.isClientSide) return;

        SideMap sidemap = this.getSideMap();
        int count = sidemap.getSideCount();

        for (int i = 0; i < count - 1; i++) {
            AntlineBlock.dropResources(getBlock().defaultBlockState(), level, this.getBlockPos());
        }
        this.hideSides = true;
        try {
            super.invalidateCaps();
        } finally {
            this.hideSides = false;
        }
    }

    private static Block getBlock() {
        return ModBlocks.VINTAGE_ANTLINE.get();
    }
}
