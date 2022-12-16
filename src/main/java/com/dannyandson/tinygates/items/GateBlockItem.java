package com.dannyandson.tinygates.items;

import com.dannyandson.tinygates.setup.ModSetup;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class GateBlockItem extends BlockItem {
    public GateBlockItem(Block block) {
        super(block,new Item.Properties().tab(ModSetup.ITEM_GROUP));
    }


    @Override
    public  void  appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flags)
    {
        if (Screen.hasShiftDown()) {
            list.add(Component.translatable("tinygates.fullsizegatemessage").withStyle(ChatFormatting.GRAY));
            list.add(Component.translatable("message." + this.getDescriptionId()).withStyle(ChatFormatting.DARK_AQUA));
        } else
            list.add(Component.translatable("tinyredstone.tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));    }


}
