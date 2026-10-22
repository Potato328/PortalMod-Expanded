package com.potato328.portalmodexpanded;

import com.potato328.portalmodexpanded.network.ModNetwork;
import com.potato328.portalmodexpanded.network.VintageBlackplateSecondaryPacket;
import com.potato328.portalmodexpanded.util.SecondaryPanelTextures;
import com.potato328.portalmodexpanded.util.VintageBlackplateSecondaryData;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.portalmod.common.sorted.panel.PanelBlock;
import net.portalmod.common.sorted.panel.PanelState;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = PortalModExpanded.modid)
public class WrenchHandler {
    private static final ResourceLocation WRENCH_ID = new ResourceLocation("portalmod", "wrench");

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        ItemStack heldItem = event.getItemStack();
        if (heldItem.isEmpty()) return;

        ResourceLocation registryName = heldItem.getItem().getRegistryName();
        if (registryName == null) return;

        World world = event.getWorld();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        PlayerEntity player = event.getPlayer();

        if (!WRENCH_ID.equals(registryName)) return;

        if (SecondaryPanelTextures.isWallPaint(state)) {
            event.setCanceled(true);
            event.setCancellationResult(ActionResultType.SUCCESS);
            event.setUseBlock(Event.Result.DENY);
            event.setUseItem(Event.Result.DENY);
            if (world.isClientSide) return;

            ServerWorld serverWorld = (ServerWorld) world;
            VintageBlackplateSecondaryData data = VintageBlackplateSecondaryData.get(serverWorld);
            boolean target = !data.isSecondary(pos);
            PanelState clickedPanelState = state.getValue(PanelBlock.STATE);

            Set<BlockPos> group = new HashSet<>();
            group.add(pos.immutable());
            group.addAll(((PanelBlock) state.getBlock()).getConnectedPositions(state, pos));

            for (BlockPos groupPos : group) {
                BlockState groupState = world.getBlockState(groupPos);
                if (groupState.getBlock() != state.getBlock() || !SecondaryPanelTextures.isWallPaint(groupState)) {
                    continue;
                }
                data.setSecondary(groupPos, target);
                ModNetwork.CHANNEL.send(
                        PacketDistributor.TRACKING_CHUNK.with(() -> serverWorld.getChunkAt(groupPos)),
                        new VintageBlackplateSecondaryPacket(groupPos, target)
                );
            }

            player.swing(event.getHand(), true);
            playCustomSound(world, pos, "portalmod", "item.wrench.use", SoundEvents.IRON_GOLEM_REPAIR);
            return;
        }
    }

    private static void playCustomSound(World world, BlockPos pos, String namespace, String path, SoundEvent fallback) {
        ResourceLocation loc = new ResourceLocation(namespace, path);
        SoundEvent customSound = ForgeRegistries.SOUND_EVENTS.getValue(loc);
        SoundEvent finalSound = (customSound != null) ? customSound : fallback;
        world.playSound(null, pos, finalSound, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}
