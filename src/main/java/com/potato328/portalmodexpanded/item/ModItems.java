package com.potato328.portalmodexpanded.item;

import com.potato328.portalmodexpanded.ModInventoryTab;
import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.portalmod.common.sorted.antline.AntlineBlockItem;

public class ModItems {

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "portalmodexpanded");

    public static final RegistryObject<Item> VINTAGE_ANTLINE = ITEMS.register("vintage_antline", () -> new AntlineBlockItem(ModBlocks.VINTAGE_ANTLINE.get(), properties("vintage")));

    public static Item.Properties properties(String tab) {
        switch (tab) {
            case "arbored":
                return new Item.Properties().tab(ModInventoryTab.ARBORED_TAB);
            case "eroded":
                return new Item.Properties().tab(ModInventoryTab.ERODED_TAB);
            case "vintage":
                return new Item.Properties().tab(ModInventoryTab.VINTAGE_TAB);
            case "expanded":
                return new Item.Properties().tab(ModInventoryTab.EXPANDED_TAB);
            default:
                return null;
        }
    }
}
