package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public void onMissingMappings(RegistryEvent.MissingMappings<Block> event) {
        System.out.println("[PortalModExpanded] Missing Mappings Found!");
        for (RegistryEvent.MissingMappings.Mapping<Block> mapping : event.getAllMappings()) {
            if (mapping != null) {

                System.out.println("[PortalModExpanded] Fixing Missing Mappings");

                //Update Remapping
                if (mapping.key.getPath().equals("arbored_blackplate_1")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 1");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_1.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_2")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 2");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_3")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 3");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_3.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_4")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 4");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_4.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_5")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 5");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_5.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_6")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 6");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_6.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_7")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 7");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_7.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_8")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 8");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_8.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_9")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate 9");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_9.get();
                    mapping.remap(block);
                }

                if (mapping.key.getPath().equals("arbored_blackplate_slab_1")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 1");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_1.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_2")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 2");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_3")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 3");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_3.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_4")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 4");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_4.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_5")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 5");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_5.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_6")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 6");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_6.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_7")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 7");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_7.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_8")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 8");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_8.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_blackplate_slab_9")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Blackplate Slab 9");
                    Block block = ModBlocks.ARBORED_BLACKPLATE_SLAB_9.get();
                    mapping.remap(block);
                }

                if (mapping.key.getPath().equals("arbored_super_button")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Super Button");
                    Block block = ModBlocks.ARBORED_SUPER_BUTTON.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_super_button2")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Super Button (No Case)");
                    Block block = ModBlocks.ARBORED_SUPER_BUTTON2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_standing_button")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Standing Button");
                    Block block = ModBlocks.ARBORED_STANDING_BUTTON.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("arbored_chamber_door")) {
                    System.out.println("[PortalModExpanded] Fixed Arbored Chamber Door");
                    Block block = ModBlocks.ARBORED_CHAMBER_DOOR.get();
                    mapping.remap(block);
                }

                //Deco Block Merge Remapping
                if (mapping.key.getPath().equals("metal_wall")) {
                    Block block = ModBlocks.RUSTY_BLOCK.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("single_glue_tile")) {
                    Block block = ModBlocks.FRAME_TILES.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("double_glue_tile")) {
                    Block block = ModBlocks.FRAME_TILES_1.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("triple_glue_tile")) {
                    Block block = ModBlocks.FRAME_TILES_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("quad_glue_tile")) {
                    Block block = ModBlocks.FRAME_TILES_4.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("diag_glue_tile")) {
                    Block block = ModBlocks.FRAME_TILES_3.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("single_glue_tile_clean")) {
                    Block block = ModBlocks.FRAME_TILES.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("double_glue_tile_clean")) {
                    Block block = ModBlocks.FRAME_TILES_1.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("triple_glue_tile_clean")) {
                    Block block = ModBlocks.FRAME_TILES_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("quad_glue_tile_clean")) {
                    Block block = ModBlocks.FRAME_TILES_4.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("diag_glue_tile_clean")) {
                    Block block = ModBlocks.FRAME_TILES_3.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("panel_tile")) {
                    Block block = ModBlocks.PANEL_TILE.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_black_tiles")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_glue_tiles")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_glue_tiles_2")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_clean_glue_tiles")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_clean_glue_tiles_2")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_arbored_glue_tiles")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_dirty_glue_tiles")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_arbored_glue_tiles_2")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("floor_dirty_glue_tiles_2")) {
                    Block block = ModBlocks.TILE_DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("debris_1")) {
                    Block block = ModBlocks.DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("debris_2")) {
                    Block block = ModBlocks.DEBRIS.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("divine_flowers")) {
                    Block block = Blocks.AIR;
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_blackplate")) {
                    Block block = ModBlocks.DIRTY_BLACKPLATE.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_blackplate_2")) {
                    Block block = ModBlocks.DIRTY_BLACKPLATE_1.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_blackplate_3")) {
                    Block block = ModBlocks.DIRTY_BLACKPLATE_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_blackplate_4")) {
                    Block block = ModBlocks.DIRTY_BLACKPLATE_3.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_lunecast")) {
                    Block block = ModBlocks.DIRTY_LUNECAST.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("extra_arbored_lunecast_2")) {
                    Block block = ModBlocks.DIRTY_LUNECAST_2.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("dark_vintage_blackplate")) {
                    Block block = ModBlocks.DARK_VINTAGE_BLACKPLATE.get();
                    mapping.remap(block);
                }
                if (mapping.key.getPath().equals("light_vintage_blackplate")) {
                    Block block = ModBlocks.LIGHT_VINTAGE_BLACKPLATE.get();
                    mapping.remap(block);
                }


            }

        }
    }


}

