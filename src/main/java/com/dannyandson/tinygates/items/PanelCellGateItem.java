package com.dannyandson.tinygates.items;

import com.dannyandson.tinygates.setup.ModSetup;
import com.dannyandson.tinyredstone.blocks.PanelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PanelCellGateItem extends Item{

    public PanelCellGateItem() {
        super(new Item.Properties().tab(ModSetup.ITEM_GROUP));
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        return player.level.getBlockState(pos).getBlock() instanceof PanelBlock;
    }
}
