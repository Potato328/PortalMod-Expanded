package com.potato328.portalmodexpanded.block;

import com.potato328.portalmodexpanded.ModInventoryTab;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.portalmod.common.blocks.FrameBlock;
import net.portalmod.common.sorted.button.StandingButtonBlock;
import net.portalmod.common.sorted.button.SuperButtonBlock;
import net.portalmod.common.sorted.cubedropper.CubeDropperBlock;
import net.portalmod.common.sorted.door.ChamberDoorBlock;
import net.portalmod.common.sorted.fizzler.FizzlerEmitterBlock;
import net.portalmod.common.sorted.fizzler.FizzlerFieldBlock;
import net.portalmod.common.sorted.panel.PanelBlock;
import net.portalmod.common.sorted.platform.PlatformBlock;

import java.util.function.Supplier;

import static com.potato328.portalmodexpanded.PortalModExpanded.modid;
import static net.portalmod.core.init.BlockInit.stoneCopy;

public class ModBlocks {

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, modid);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, modid);

    //Arbored Blocks
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_1 = registerBlock("arbored_blackplate_1", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_1 = registerBlock("arbored_blackplate_slab_1", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_2 = registerBlock("arbored_blackplate_2", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_2 = registerBlock("arbored_blackplate_slab_2", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_3 = registerBlock("arbored_blackplate_3", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_3 = registerBlock("arbored_blackplate_slab_3", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_4 = registerBlock("arbored_blackplate_4", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_4 = registerBlock("arbored_blackplate_slab_4", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_5 = registerBlock("arbored_blackplate_5", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_5 = registerBlock("arbored_blackplate_slab_5", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_6 = registerBlock("arbored_blackplate_6", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_6 = registerBlock("arbored_blackplate_slab_6", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_7 = registerBlock("arbored_blackplate_7", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_7 = registerBlock("arbored_blackplate_slab_7", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_8 = registerBlock("arbored_blackplate_8", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_8 = registerBlock("arbored_blackplate_slab_8", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_9 = registerBlock("arbored_blackplate_9", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_SLAB_9 = registerBlock("arbored_blackplate_slab_9", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");


    //Portal Mod Deco Blocks
    public static final RegistryObject<Block> DIRTY_BLACKPLATE = registerBlock("dirty_blackplate", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");
    public static final RegistryObject<Block> DIRTY_BLACKPLATE_1 = registerBlock("dirty_blackplate_1", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");
    public static final RegistryObject<Block> DIRTY_BLACKPLATE_2 = registerBlock("dirty_blackplate_2", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");
    public static final RegistryObject<Block> DIRTY_BLACKPLATE_3 = registerBlock("dirty_blackplate_3", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");
    public static final RegistryObject<Block> LIGHT_VINTAGE_BLACKPLATE = registerBlock("light_vintage_blackplate", () -> new Block(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");
    public static final RegistryObject<Block> DARK_VINTAGE_BLACKPLATE = registerBlock("dark_vintage_blackplate", () -> new Block(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)), "deco");

    public static final RegistryObject<Block> DIRTY_LUNECAST = registerBlock("dirty_lunecast", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE)), "deco");
    public static final RegistryObject<Block> DIRTY_LUNECAST_2 = registerBlock("dirty_lunecast_2", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE)), "deco");

    public static final RegistryObject<Block> RUSTY_BLOCK = registerBlock("rusty_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), "deco");

    public static final RegistryObject<Block> FRAME_TILES = registerBlock("frame_tiles", () -> new TileFrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");
    public static final RegistryObject<Block> FRAME_TILES_1 = registerBlock("frame_tiles_1", () -> new TileFrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");
    public static final RegistryObject<Block> FRAME_TILES_2 = registerBlock("frame_tiles_2", () -> new TileFrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");
    public static final RegistryObject<Block> FRAME_TILES_3 = registerBlock("frame_tiles_3", () -> new TileFrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");
    public static final RegistryObject<Block> FRAME_TILES_4 = registerBlock("frame_tiles_4", () -> new TileFrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");
    public static final RegistryObject<Block> PANEL_TILE = registerBlock("panel_tile", () -> new FrameBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion(), true), "deco");

    public static final RegistryObject<Block> DEBRIS = registerBlock("debris", () -> new DebrisBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion()), "deco");

    public static final RegistryObject<Block> TILE_DEBRIS = registerBlock("tile_debris", () -> new DebrisTileBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion()), "deco");


    //P1 Blocks
    public static final RegistryObject<Block> ERODED_LUNECAST_P1 = registerBlock("eroded_lunecast_p1", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE)),"eroded");
    public static final RegistryObject<Block> ERODED_LUNECAST_P1_COLD = registerBlock("eroded_lunecast_p1_cold", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE)),"eroded");
    public static final RegistryObject<Block> ERODED_LUNECAST_P1_WARM = registerBlock("eroded_lunecast_p1_warm", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.WHITE_CONCRETE)),"eroded");

    public static final RegistryObject<Block> ERODED_BLACKPLATE_P1 = registerBlock("eroded_blackplate_p1", () -> new PanelBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"eroded");
    public static final RegistryObject<Block> ERODED_BLACKPLATE_P1_SLAB = registerBlock("eroded_blackplate_p1_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"eroded");

    //Arbored Platforms
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_1 = registerBlock("arbored_blackplate_platform_1", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_2 = registerBlock("arbored_blackplate_platform_2", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_3 = registerBlock("arbored_blackplate_platform_3", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_4 = registerBlock("arbored_blackplate_platform_4", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_5 = registerBlock("arbored_blackplate_platform_5", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_6 = registerBlock("arbored_blackplate_platform_6", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_7 = registerBlock("arbored_blackplate_platform_7", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_8 = registerBlock("arbored_blackplate_platform_8", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");
    public static final RegistryObject<Block> ARBORED_BLACKPLATE_PLATFORM_9 = registerBlock("arbored_blackplate_platform_9", () -> new PlatformBlock(AbstractBlock.Properties.copy(Blocks.BLACK_CONCRETE)),"arbored");


    //Arbored Testing Elements
    public static final RegistryObject<Block> ARBORED_CUBE_DROPPER = registerBlock("arbored_cube_dropper", () -> new CubeDropperBlock(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion()),"arbored");
    public static final RegistryObject<Block> ARBORED_CHAMBER_DOOR = registerBlock("arbored_chamber_door", () -> new ChamberDoorBlock(stoneCopy(MaterialColor.COLOR_BLACK).sound(SoundType.STONE).noOcclusion()),"arbored");
    public static final RegistryObject<Block> ARBORED_SUPER_BUTTON = registerBlock("arbored_super_button", () -> new SuperButtonBlock(stoneCopy(MaterialColor.COLOR_RED).sound(SoundType.STONE).noOcclusion()),"arbored");
    public static final RegistryObject<Block> ARBORED_SUPER_BUTTON2 = registerBlock("arbored_super_button2", () -> new SuperButtonBlock(stoneCopy(MaterialColor.COLOR_RED).sound(SoundType.STONE).noOcclusion()),"arbored");
    public static final RegistryObject<Block> ARBORED_STANDING_BUTTON = registerBlock("arbored_standing_button", () -> new StandingButtonBlock(stoneCopy(MaterialColor.COLOR_RED).sound(SoundType.STONE).noOcclusion()),"arbored");

    public static final RegistryObject<Block> ARBORED_CHAMBER_DOOR_P1 = registerBlock("arbored_chamber_door_p1", () -> new ChamberDoorBlock(stoneCopy(MaterialColor.COLOR_BLACK).sound(SoundType.STONE).noOcclusion()),"arbored");


    //P1 Testing Elements
    public static final RegistryObject<Block> ERODED_FIZZLER_EMITTER = registerBlock("eroded_fizzler_emitter", () -> new ErodedFizzlerEmitterBlock(stoneCopy(MaterialColor.COLOR_BLACK).sound(SoundType.STONE).noOcclusion().lightLevel(blockState -> blockState.getValue(FizzlerEmitterBlock.ACTIVE) ? 10 : 0)),"eroded");
    public static final RegistryObject<Block> ERODED_FIZZLER_FIELD = registerBlockNoItem("eroded_fizzler_field", () -> new FizzlerFieldBlock(AbstractBlock.Properties.copy(Blocks.AIR).noOcclusion().strength(-1.0F,3600000.0F).noDrops().lightLevel(blockState -> 10)));
    public static final RegistryObject<Block> ERODED_CHAMBER_DOOR = registerBlock("eroded_chamber_door", () -> new ChamberDoorBlock(stoneCopy(MaterialColor.COLOR_BLACK).sound(SoundType.STONE).noOcclusion()),"eroded");

    //Experimental
    public static final RegistryObject<Block> CAMERA_P2 = registerBlock("camera_p2", () -> new CameraP2Block(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion()), "arbored");

    public static final RegistryObject<Block> SINGLE_AUTO_PORTAL = registerBlock("single_auto_portal", () -> new SingleAutoPortal(AbstractBlock.Properties.copy(Blocks.STONE).noOcclusion()), "arbored");

    //Registers blocks
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, String tab) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registeredBlock, tab);
        return registeredBlock;
    }

    private static <T extends Block>RegistryObject<T> registerBlockNoItem(String name, Supplier<T> block) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        return registeredBlock;
    }

    //Registers block items
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block, String tab) {
        ITEMS.register(name, ()-> new BlockItem(block.get(), properties(tab)));
    }

    public static Item.Properties properties(String tab) {
        switch (tab) {
            case "arbored":
                return new Item.Properties().tab(ModInventoryTab.ARBORED_TAB);
            case "eroded":
                return new Item.Properties().tab(ModInventoryTab.ERODED_TAB);
            case "deco":
                return new Item.Properties().tab(ModInventoryTab.DECO_TAB);
            default:
                return null;
        }
    }
}
