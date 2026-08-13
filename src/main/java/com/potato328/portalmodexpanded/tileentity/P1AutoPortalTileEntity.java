package com.potato328.portalmodexpanded.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.portalmod.common.sorted.autoportal.AutoPortalTileEntity;

public class P1AutoPortalTileEntity extends AutoPortalTileEntity {

    public P1AutoPortalTileEntity(TileEntityType<?> type) {
        super(type);
    }
    public P1AutoPortalTileEntity() {
        this(ModTileEntities.P1_AUTOPORTAL.get());
    }
}
