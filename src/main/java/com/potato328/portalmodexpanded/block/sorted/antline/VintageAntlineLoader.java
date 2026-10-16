package com.potato328.portalmodexpanded.block.sorted.antline;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

public class VintageAntlineLoader implements IModelLoader<VintageAntlineGeometry> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {}

    @Override
    public VintageAntlineGeometry read(JsonDeserializationContext deserializationContext, JsonObject modelContents) {
        return new VintageAntlineGeometry();
    }
}
