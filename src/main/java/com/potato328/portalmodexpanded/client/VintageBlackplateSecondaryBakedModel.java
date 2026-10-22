package com.potato328.portalmodexpanded.client;

import com.potato328.portalmodexpanded.util.DecalQuadHelper;
import com.potato328.portalmodexpanded.util.SecondaryPanelTextures;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VintageBlackplateSecondaryBakedModel implements IDynamicBakedModel {
    private static final ModelProperty<Boolean> SECONDARY = new ModelProperty<>();

    private final IBakedModel parent;

    public VintageBlackplateSecondaryBakedModel(IBakedModel parent) {
        this.parent = parent;
    }


    @Override
    public IModelData getModelData(IBlockDisplayReader world, BlockPos pos, BlockState state, IModelData tileData) {
        return new ModelDataMap.Builder()
                .withInitial(SECONDARY, VintageBlackplateSecondaryClientCache.isSecondary(pos))
                .build();
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand, IModelData data) {
        List<BakedQuad> base = parent.getQuads(state, side, rand, data);
        Boolean secondary = data.getData(SECONDARY);

        if(secondary == null || !secondary || !SecondaryPanelTextures.isWallPaint(state)) return base;

        List<BakedQuad> out = new ArrayList<>(base.size());
        for(BakedQuad quad : base) {
            ResourceLocation location = SecondaryPanelTextures.spriteForVintage(state, quad.getSprite().getName());
            if(location == null) {
                out.add(quad);
                continue;
            }
            TextureAtlasSprite sprite = Minecraft.getInstance().getModelManager().getAtlas(AtlasTexture.LOCATION_BLOCKS).getSprite(location);

            out.add(DecalQuadHelper.retexture(quad, sprite, quad.getTintIndex()));
        }
        return out;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
        return parent.getQuads(state, side, rand);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return false;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return parent.getParticleIcon();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return null;
    }
}
