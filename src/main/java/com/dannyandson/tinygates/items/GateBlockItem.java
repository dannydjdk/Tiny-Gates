package com.dannyandson.tinygates.items;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class GateBlockItem extends BlockItem {
    public GateBlockItem(Block block, Item.Properties props) {
        super(block, props.useBlockDescriptionPrefix());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> textConsumer, TooltipFlag flags)
    {
        if (isShiftDown()) {
            textConsumer.accept(Component.translatable("tinygates.fullsizegatemessage").withStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
            textConsumer.accept(Component.translatable("message." + this.getDescriptionId()).withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_AQUA)));
        } else
            textConsumer.accept(Component.translatable("tinyredstone.tooltip.press_shift").withStyle(Style.EMPTY.withColor(ChatFormatting.DARK_GRAY)));
    }

    private static boolean isShiftDown() {
        var window = Minecraft.getInstance().getWindow();
        return InputConstants.isKeyDown(window, GLFW.GLFW_KEY_LEFT_SHIFT)
                || InputConstants.isKeyDown(window, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }
}