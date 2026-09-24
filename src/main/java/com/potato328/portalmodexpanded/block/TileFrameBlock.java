package com.potato328.portalmodexpanded.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.portalmod.common.blocks.FrameBlock;
import net.portalmod.common.items.WrenchItem;

import javax.annotation.Nullable;
import java.util.List;

import static net.portalmod.core.util.ModUtil.TOOLTIP_STYLE;


public class TileFrameBlock extends FrameBlock {

    public static final IntegerProperty TILE = IntegerProperty.create("tile", 0, 4);
    public static final IntegerProperty ROTATION = IntegerProperty.create("rotation", 0, 3);
    public TileFrameBlock(Properties properties, boolean isFilled) {
        super(properties, isFilled);
        this.registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.UP)
                .setValue(BEAM, false)
                .setValue(WATERLOGGED, false)
                .setValue(TILE, 0)
                .setValue(ROTATION, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BEAM);
        builder.add(WATERLOGGED);
        builder.add(TILE);
        builder.add(ROTATION);
    }

    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {

        if (player.getItemInHand(hand).getItem() instanceof WrenchItem && !player.isShiftKeyDown()) {
            BlockState cycle = blockState.cycle(ROTATION);
            world.setBlockAndUpdate(pos, cycle);
            WrenchItem.playUseSound(world, result.getLocation());
            return ActionResultType.SUCCESS;
        }

        if (player.getItemInHand(hand).getItem() instanceof WrenchItem && player.isShiftKeyDown()) {
            BlockState cycle = blockState.cycle(TILE);
            world.setBlockAndUpdate(pos, cycle);
            WrenchItem.playUseSound(world, result.getLocation());
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }
    private static String getModifierKeyName() {
        return Util.getPlatform() == Util.OS.OSX
                ? "Command"
                : "Ctrl";
    }
    public static IFormattableTextComponent tooltipComponent(String key, Object... args) {
        return new TranslationTextComponent(key, args).setStyle(TOOLTIP_STYLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader blockReader, List<ITextComponent> list, ITooltipFlag flag) {
        if (!Screen.hasControlDown()) {
            list.add(tooltipComponent("tooltip.portalmod.hold_control", getModifierKeyName()));
            return;
        }
        list.add(tooltipComponent("tooltip.portalmodexpanded.tile_frame_1"));
        list.add(tooltipComponent("tooltip.portalmodexpanded.tile_frame_2"));
    }
}

