package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.portalmod.core.init.BlockInit;


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

    public static final ItemGroup VINTAGE_TAB = new ItemGroup("expanded_vintage") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.VINTAGE_BLACKPLATE_BLUE.get());
        }
    };

    public static final ItemGroup EXPANDED_TAB = new ItemGroup("expanded") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(BlockInit.BLACKPLATE.get());
        }
    };
}
