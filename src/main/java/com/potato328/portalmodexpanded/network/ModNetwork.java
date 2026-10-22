package com.potato328.portalmodexpanded.network;

import com.potato328.portalmodexpanded.PortalModExpanded;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "7";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(PortalModExpanded.modid, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    private static int nextId = 0;

    private ModNetwork() {}

    public static void register() {

        CHANNEL.registerMessage(
                nextId++,
                VintageBlackplateSecondaryPacket.class,
                VintageBlackplateSecondaryPacket::encode,
                VintageBlackplateSecondaryPacket::decode,
                VintageBlackplateSecondaryPacket::handle
        );
    }
}
