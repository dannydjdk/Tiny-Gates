package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TinyGates.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static final ItemGroup ITEM_GROUP = new ItemGroup(TinyGates.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.TINY_AND_GATE_ITEM.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
    }
}
