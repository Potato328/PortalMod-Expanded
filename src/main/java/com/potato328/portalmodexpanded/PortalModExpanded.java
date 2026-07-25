package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.block.ModBlocks;
import com.potato328.portalmodexpanded.client.renderer.CameraP2TER;
import com.potato328.portalmodexpanded.client.renderer.SingleAutoPortalTER;
import com.potato328.portalmodexpanded.tileentity.ModTileEntities;
import com.potato328.portalmodexpanded.tileentity.SingleAutoPortalTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod("portalmodexpanded")
public class PortalModExpanded {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String modid = "portalmodexpanded";

    public PortalModExpanded() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.register(new ColorHandler());
        ModBlocks.register(eventBus);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        ModTileEntities.TILE_ENTITY.register(eventBus);


    }

    private void setup(final FMLCommonSetupEvent event) {

    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_1.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_1.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_3.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_3.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_4.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_4.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_5.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_5.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_6.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_6.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_7.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_7.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_8.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_8.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_9.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_SLAB_9.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.DIRTY_BLACKPLATE_1.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.DIRTY_BLACKPLATE_2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.DIRTY_BLACKPLATE_3.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.FRAME_TILES.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.FRAME_TILES_1.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.FRAME_TILES_2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.FRAME_TILES_3.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.FRAME_TILES_4.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_1.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_3.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_4.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_5.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_6.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_7.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_8.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_BLACKPLATE_PLATFORM_9.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_SUPER_BUTTON.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_SUPER_BUTTON2.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_CUBE_DROPPER.get(), RenderType.translucent());
            RenderTypeLookup.setRenderLayer(ModBlocks.ARBORED_STANDING_BUTTON.get(), RenderType.cutout());

            RenderTypeLookup.setRenderLayer(ModBlocks.ERODED_FIZZLER_EMITTER.get(), RenderType.cutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ERODED_FIZZLER_FIELD.get(), RenderType.cutout());

            ClientRegistry.bindTileEntityRenderer(ModTileEntities.CAMERA_P2.get(), CameraP2TER::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.SINGLE_AUTOPORTAL.get(), SingleAutoPortalTER::new);


        });
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {

    }
    private void processIMC(final InterModProcessEvent event) {

    }
}
