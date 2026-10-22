package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.network.ModNetwork;
import com.potato328.portalmodexpanded.network.VintageBlackplateSecondaryPacket;
import com.potato328.portalmodexpanded.util.VintageBlackplateSecondaryData;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.ChunkWatchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = PortalModExpanded.modid)
public class VintageBlackplateSecondarySyncHandler {

    public VintageBlackplateSecondarySyncHandler() {}

    @SubscribeEvent
    public static void onChunkWatch(ChunkWatchEvent.Watch event) {
        ServerPlayerEntity player = event.getPlayer();
        if (player == null || player.level.isClientSide) {
            return;
        }
        VintageBlackplateSecondaryData data = VintageBlackplateSecondaryData.get(player.getLevel());
        for (BlockPos pos : data.secondaryInChunk(event.getPos().x, event.getPos().z)) {
            ModNetwork.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> player),
                    new VintageBlackplateSecondaryPacket(pos, true)
            );
        }
    }
}
