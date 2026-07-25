package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import static com.potato328.portalmodexpanded.PortalModExpanded.modid;

public class ModInventoryTab {
    public static final ItemGroup ARBORED_TAB = new ItemGroup("expanded_arbor") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.ARBORED_BLACKPLATE_1.get());
        }
    };

    public static final ItemGroup ERODED_TAB = new ItemGroup("expanded_eroded") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.ERODED_LUNECAST_P1.get());
        }
    };

    public static final ItemGroup DECO_TAB = new ItemGroup("pm_deco_blocks") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.DIRTY_BLACKPLATE_1.get());
        }
    };
}
