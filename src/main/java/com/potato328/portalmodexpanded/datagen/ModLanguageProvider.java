package com.potato328.portalmodexpanded.datagen;

import com.potato328.portalmodexpanded.ModInventoryTab;
import com.potato328.portalmodexpanded.PortalModExpanded;
import com.potato328.portalmodexpanded.block.ModBlocks;
import com.potato328.portalmodexpanded.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen) {
        super(gen, PortalModExpanded.modid, "en_us");
    }

    @Override
    protected void addTranslations() {

        //Arbored Blocks and Items
        add(ModBlocks.ARBORED_BLACKPLATE_1.get(), "Arbored Blackplate Alt 1");
        add(ModBlocks.ARBORED_BLACKPLATE_2.get(), "Arbored Blackplate Alt 2");
        add(ModBlocks.ARBORED_BLACKPLATE_3.get(), "Arbored Blackplate Alt 3");
        add(ModBlocks.ARBORED_BLACKPLATE_4.get(), "Arbored Blackplate Alt 4");
        add(ModBlocks.ARBORED_BLACKPLATE_5.get(), "Arbored Blackplate Alt 5");
        add(ModBlocks.ARBORED_BLACKPLATE_6.get(), "Arbored Blackplate Alt 6");
        add(ModBlocks.ARBORED_BLACKPLATE_7.get(), "Arbored Blackplate Alt 7");
        add(ModBlocks.ARBORED_BLACKPLATE_8.get(), "Arbored Blackplate Alt 8");
        add(ModBlocks.ARBORED_BLACKPLATE_9.get(), "Arbored Blackplate Alt 9");

        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_1.get(), "Arbored Blackplate Slab Alt 1");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_2.get(), "Arbored Blackplate Slab Alt 2");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_3.get(), "Arbored Blackplate Slab Alt 3");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_4.get(), "Arbored Blackplate Slab Alt 4");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_5.get(), "Arbored Blackplate Slab Alt 5");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_6.get(), "Arbored Blackplate Slab Alt 6");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_7.get(), "Arbored Blackplate Slab Alt 7");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_8.get(), "Arbored Blackplate Slab Alt 8");
        add(ModBlocks.ARBORED_BLACKPLATE_SLAB_9.get(), "Arbored Blackplate Slab Alt 9");

        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_1.get(), "Arbored Blackplate Platform Alt 1");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_2.get(), "Arbored Blackplate Platform Alt 2");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_3.get(), "Arbored Blackplate Platform Alt 3");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_4.get(), "Arbored Blackplate Platform Alt 4");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_5.get(), "Arbored Blackplate Platform Alt 5");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_6.get(), "Arbored Blackplate Platform Alt 6");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_7.get(), "Arbored Blackplate Platform Alt 7");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_8.get(), "Arbored Blackplate Platform Alt 8");
        add(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_9.get(), "Arbored Blackplate Platform Alt 9");

        add(ModBlocks.DIRTY_BLACKPLATE.get(), "Dirty Blackplate");
        add(ModBlocks.DIRTY_BLACKPLATE_1.get(), "Ivy Blackplate");
        add(ModBlocks.DIRTY_BLACKPLATE_2.get(), "Mossy Blackplate");
        add(ModBlocks.DIRTY_BLACKPLATE_3.get(), "Jungle Blackplate");
        add(ModBlocks.DIRTY_LUNECAST.get(), "Stained Lunecast");
        add(ModBlocks.DIRTY_LUNECAST_2.get(), "Dirty Lunecast");

        add(ModBlocks.RUSTY_BLOCK.get(), "Rusty Metal");
        add(ModBlocks.PANEL_TILE.get(), "Panel Frame");

        add(ModBlocks.DEBRIS.get(), "Debris");
        add(ModBlocks.TILE_DEBRIS.get(), "Loose Tiles");

        add(ModBlocks.ARBORED_SUPER_BUTTON.get(), "Arbored Super Button");
        add(ModBlocks.ARBORED_SUPER_BUTTON2.get(), "Arbored Super Button (No Case)");
        add(ModBlocks.ARBORED_STANDING_BUTTON.get(), "Arbored Standing Button");
        add(ModBlocks.ARBORED_CHAMBER_DOOR.get(), "Arbored Chamber Door");
        add(ModBlocks.ARBORED_CHAMBER_DOOR_P1.get(), "Arbored Portal 1 Chamber Door");
        add(ModBlocks.ARBORED_CUBE_DROPPER.get(), "Arbored Cube Dropper");

        //Eroded Blocks and Items
        add(ModBlocks.ERODED_LUNECAST_P1.get(), "Portal 1 Lunecast");
        add(ModBlocks.ERODED_LUNECAST_P1_COLD.get(), "Portal 1 Lunecast Cold");
        add(ModBlocks.ERODED_LUNECAST_P1_WARM.get(), "Portal 1 Lunecast Warm");
        add(ModBlocks.ERODED_BLACKPLATE_P1.get(), "Portal 1 Blackplate");
        add(ModBlocks.ERODED_BLACKPLATE_P1_SLAB.get(), "Portal 1 Blackplate Slab");
        add(ModBlocks.ERODED_SUPER_BUTTON.get(), "Portal 1 Super Button");
        add(ModBlocks.ERODED_CHAMBER_DOOR.get(), "Portal 1 Chamber Door");
        add(ModBlocks.P1_AUTO_PORTAL.get(), "Portal 1 Autoportal");

        //Vintage Blocks and Items
        add(ModBlocks.VINTAGE_BLACKPLATE_BLUE.get(), "Vintage Blackplate Blue");
        add(ModBlocks.VINTAGE_BLACKPLATE_PLYWOOD.get(), "Vintage Plywood");
        add(ModBlocks.VINTAGE_BLACKPLATE_PLYWOOD_DARK.get(), "Vintage Plywood Dark");
        add(ModBlocks.DARK_VINTAGE_BLACKPLATE.get(), "Vintage Blackplate Dark");
        add(ModBlocks.LIGHT_VINTAGE_BLACKPLATE.get(), "Vintage Blackplate Light");

        add(ModBlocks.VINTAGE_FIZZLER_EMITTER.get(), "Vintage Fizzler Emitter");
        add(ModBlocks.VINTAGE_ANTLINE_INDICATOR.get(), "Vintage Antline Indicator");
        add(ModBlocks.VINTAGE_CHAMBER_DOOR.get(), "Vintage Chamber Door");
        add(ModBlocks.VINTAGE_CUBE_DROPPER.get(), "Vintage Cube Dropper");
        add(ModBlocks.VINTAGE_SUPER_BUTTON.get(), "Vintage Super Button");
        add(ModBlocks.VINTAGE_STANDING_BUTTON.get(), "Vintage Standing Button");
        add(ModItems.VINTAGE_ANTLINE.get(), "Vintage Antline");

        //Expanded Blocks and Items
        add(ModBlocks.SINGLE_AUTO_PORTAL.get(), "Single Auto Portal");
        add(ModBlocks.SINGLE_CHAMBER_LIGHT_BLACKPLATE.get(), "Single Chamber Light");
        add(ModBlocks.FRAME_TILES.get(), "Single Tile Frame");
        add(ModBlocks.FRAME_TILES_1.get(), "Double Tile Frame");
        add(ModBlocks.FRAME_TILES_2.get(), "Triple Tile Frame");
        add(ModBlocks.FRAME_TILES_3.get(), "Diagonal Tile Frame");
        add(ModBlocks.FRAME_TILES_4.get(), "Full Tile Frame");

        //Inventory Tabs
        add("itemGroup.expanded_arbor", "Expanded Arbor");
        add("itemGroup.expanded_eroded", "Expanded Erosion");
        add("itemGroup.expanded_vintage", "Expanded Vintage");
        add("itemGroup.expanded", "PortalMod: Expanded");

        //Tooltips
        add("tooltip.portalmodexpanded.debris", "- §6Wrench§7: Swap between small and large");
        add("tooltip.portalmodexpanded.tile_debris_1", "- §6Wrench§7: Cycle through tile textures");
        add("tooltip.portalmodexpanded.tile_debris_2", "- §6Wrench + Crouch§7: Swap between small and large");
        add("tooltip.portalmodexpanded.tile_frame_1", "- §6Wrench§7: Rotate the tiles");
        add("tooltip.portalmodexpanded.tile_frame_2", "- §6Wrench + Crouch§7: Cycle through tile textures");

    }
}
