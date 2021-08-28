package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = TinyGates.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TinyGates.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.TINY_AND_GATE_ITEM.get());
        }
    };

    public static void init(final FMLCommonSetupEvent event) {
    }
}
