package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.gates.ANDGate;
import com.dannyandson.tinygates.gates.ORGate;
import com.dannyandson.tinygates.items.PanelCellGateItem;
import com.dannyandson.tinyredstone.TinyRedstone;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinyGates.MODID);

    public static final RegistryObject<Item> TINY_AND_GATE_ITEM = ITEMS.register("tiny_and_gate", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_OR_GATE_ITEM = ITEMS.register("tiny_or_gate", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_XOR_GATE_ITEM = ITEMS.register("tiny_xor_gate", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_NOT_GATE_ITEM = ITEMS.register("tiny_not_gate", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_CLOCK_ITEM = ITEMS.register("tiny_clock", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_COUNTER_ITEM = ITEMS.register("tiny_counter", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_RS_LATCH_ITEM = ITEMS.register("tiny_rs_latch", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_EDGE_DETECTOR_ITEM = ITEMS.register("tiny_edge_detector", PanelCellGateItem::new);
//    public static final RegistryObject<Item> TINY_T_FLIP_FLOP_ITEM = ITEMS.register("tiny_t_flip_flop", PanelCellGateItem::new);

    //called from main mod constructor
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //called at FMLCommonSetupEvent in ModSetup
    public static void registerPanelCells(){
        TinyRedstone.registerPanelCell(ANDGate.class, TINY_AND_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(ORGate.class, TINY_OR_GATE_ITEM.get());
    }


}
