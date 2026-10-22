package com.potato328.portalmodexpanded.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VintageBlackplateSecondaryData extends WorldSavedData {

    private static final String NAME = "portalmodexpanded_vintage_blackplate";

    private final Set<Long> secondary = new HashSet<>();

    public VintageBlackplateSecondaryData() {
        super(NAME);
    }

    public static VintageBlackplateSecondaryData get(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(VintageBlackplateSecondaryData::new, NAME);
    }

    public boolean isSecondary(BlockPos pos) {
        return secondary.contains(pos.asLong());
    }

    public void setSecondary(BlockPos pos, boolean value) {
        boolean changed = value ? secondary.add(pos.asLong()) : secondary.remove(pos.asLong());
        if(changed) setDirty();
    }

    public List<BlockPos> secondaryInChunk(int chunkX, int chunkZ) {
        List<BlockPos> out = new ArrayList<>();
        for (long encoded : secondary) {
            BlockPos pos = BlockPos.of(encoded);
            if ((pos.getX() >> 4) == chunkX && (pos.getZ() >> 4) == chunkZ) {
                out.add(pos);
            }
        }
        return out;
    }

    @Override
    public void load(CompoundNBT tag) {
        secondary.clear();
        for (long encoded : tag.getLongArray("Positions")) {
            secondary.add(encoded);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        long[] arr = new long[secondary.size()];
        int i = 0;
        for (long encoded : secondary) {
            arr[i++] = encoded;
        }
        tag.putLongArray("Positions", arr);
        return tag;
    }

}
