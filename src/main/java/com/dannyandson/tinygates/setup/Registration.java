package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.gates.ANDGate;
import com.dannyandson.tinyredstone.TinyRedstone;
import com.dannyandson.tinyredstone.items.PanelCellItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinyRedstone.MODID);

    public static final RegistryObject<Item> TINY_ANDGATE_ITEM = ITEMS.register("tiny_andgate", PanelCellItem::new);

    //called from main mod constructor
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //called at FMLCommonSetupEvent in ModSetup
    public static void registerPanelCells(){
        TinyRedstone.registerPanelCell(ANDGate.class,TINY_ANDGATE_ITEM.get());
    }


}
