package com.potato328.portalmodexpanded.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class VintageBlackplateSecondaryClientCache {

    private static final Set<BlockPos> SECONDARY = new HashSet<>();
    private VintageBlackplateSecondaryClientCache() {}

    public static boolean isSecondary(BlockPos pos) {
        return SECONDARY.contains(pos);
    }

    public static void receive(BlockPos pos, boolean secondary) {
        if (secondary) {
            SECONDARY.add(pos.immutable());
        } else {
            SECONDARY.remove(pos);
        }

        World world = Minecraft.getInstance().level;
        if (world != null) world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        if (event.getWorld() instanceof ClientWorld) {
            SECONDARY.clear();
        }
    }
}
