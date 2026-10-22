package com.potato328.portalmodexpanded.client;

import com.potato328.portalmodexpanded.util.SecondaryPanelTextures;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VintageBlackplateSecondaryModelBakeHandler {

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        for (ResourceLocation blockId : SecondaryPanelTextures.eligibleBlockIds()) {
            replaceAllVariants(event, blockId);
        }
    }

    private static void replaceAllVariants(ModelBakeEvent event, ResourceLocation blockName) {
        List<ModelResourceLocation> keys = new ArrayList<>();
        for (ResourceLocation key : event.getModelRegistry().keySet()) {
            if (key instanceof ModelResourceLocation) {
                ModelResourceLocation variant = (ModelResourceLocation) key;
                if (variant.getNamespace().equals(blockName.getNamespace())
                        && variant.getPath().equals(blockName.getPath())
                        && !variant.getVariant().equals("inventory")) {
                    keys.add(variant);
                }
            }
        }
        for (ModelResourceLocation key : keys) {
            IBakedModel original = event.getModelRegistry().get(key);
            if (original == null) {
                continue;
            }
            event.getModelRegistry().put(key, new VintageBlackplateSecondaryBakedModel(original));
        }
    }
}
