package com.potato328.portalmodexpanded.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

//Server to Client
public class VintageBlackplateSecondaryPacket {
    public final BlockPos pos;
    public final boolean secondary;

    public VintageBlackplateSecondaryPacket(BlockPos pos, boolean secondary) {
        this.pos = pos;
        this.secondary = secondary;
    }

    public static void encode(VintageBlackplateSecondaryPacket m, PacketBuffer b) {
        b.writeBlockPos(m.pos);
        b.writeBoolean(m.secondary);
    }

    public static VintageBlackplateSecondaryPacket decode(PacketBuffer b) {
        return new VintageBlackplateSecondaryPacket(b.readBlockPos(), b.readBoolean());
    }

    public static void handle(VintageBlackplateSecondaryPacket m, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                        () -> () -> com.potato328.portalmodexpanded.client.VintageBlackplateSecondaryClientCache.receive(m.pos, m.secondary)));
        ctx.get().setPacketHandled(true);
    }
}
