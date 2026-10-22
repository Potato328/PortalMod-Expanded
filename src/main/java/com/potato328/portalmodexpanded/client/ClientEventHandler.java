package com.potato328.portalmodexpanded.client;

import com.potato328.portalmodexpanded.block.sorted.antline.VintageAntlineBakedModel;
import com.potato328.portalmodexpanded.block.sorted.antline.VintageAntlineLoader;
import com.potato328.portalmodexpanded.util.SecondaryPanelTextures;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "portalmodexpanded", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        //TER Texture Stitching
        if (event.getMap().location().equals(AtlasTexture.LOCATION_BLOCKS)) {
            event.addSprite(new ResourceLocation("portalmodexpanded", "entity/camerahead"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "entity/p1_autoportal"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "entity/vintage_cube_dropper_door"));

            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/blue/vintage_blackplate_blue_2x2_top_right"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/blue/vintage_blackplate_blue_2x2_top_left"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/blue/vintage_blackplate_blue_2x2_bottom_right"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/blue/vintage_blackplate_blue_2x2_bottom_left"));

            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/plywood/vintage_plywood_light_2x2_top_right"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/plywood/vintage_plywood_light_2x2_top_left"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/plywood/vintage_plywood_light_2x2_bottom_right"));
            event.addSprite(new ResourceLocation("portalmodexpanded", "block/vintage/plywood/vintage_plywood_light_2x2_bottom_left"));

            for(ResourceLocation texture : VintageAntlineBakedModel.getAllTextures())
                event.addSprite(texture);
        }
    }
    @SubscribeEvent
    public static void onLoaderRegister(ModelRegistryEvent event) {
        registerLoader("vintage_antline", new VintageAntlineLoader());
    }

    private static void registerLoader(String name, IModelLoader<?> loader) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation("portalmodexpanded", name), loader);
    }
}
