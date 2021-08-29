package com.dannyandson.tinygates.items;

import com.dannyandson.tinygates.setup.ModSetup;
import com.dannyandson.tinyredstone.blocks.PanelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class PanelCellGateItem extends Item{

    public PanelCellGateItem() {
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP));
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        return player.level.getBlockState(pos).getBlock() instanceof PanelBlock;
    }

    @Override
    public  void  appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flags)
    {
        list.add(new TranslatableComponent("message." + this.getDescriptionId()));
    }
}
