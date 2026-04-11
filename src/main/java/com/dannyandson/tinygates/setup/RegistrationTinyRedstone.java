package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.gates.*;
import com.dannyandson.tinygates.items.PanelCellGateItem;
import com.dannyandson.tinyredstone.TinyRedstone;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.dannyandson.tinygates.setup.ModRegistration.ITEMS;

public class RegistrationTinyRedstone {

    public static final DeferredItem<Item> TINY_AND_GATE_ITEM = ITEMS.registerItem("tiny_and_gate", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_OR_GATE_ITEM = ITEMS.registerItem("tiny_or_gate", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_XOR_GATE_ITEM = ITEMS.registerItem("tiny_xor_gate", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_NOT_GATE_ITEM = ITEMS.registerItem("tiny_not_gate", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_CLOCK_ITEM = ITEMS.registerItem("tiny_clock", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_COUNTER_ITEM = ITEMS.registerItem("tiny_counter", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_RS_LATCH_ITEM = ITEMS.registerItem("tiny_rs_latch", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_EDGE_DETECTOR_ITEM = ITEMS.registerItem("tiny_edge_detector", PanelCellGateItem::new);
    public static final DeferredItem<Item> TINY_T_FLIP_FLOP_ITEM = ITEMS.registerItem("tiny_t_flip_flop", PanelCellGateItem::new);

    public static void register() {}

    // Called at FMLCommonSetupEvent
    public static void registerPanelCells(){
        TinyRedstone.registerPanelCell(ANDGate.class, TINY_AND_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(ORGate.class, TINY_OR_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(XORGate.class, TINY_XOR_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(NOTGate.class, TINY_NOT_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(Clock.class, TINY_CLOCK_ITEM.get());
        TinyRedstone.registerPanelCell(Counter.class, TINY_COUNTER_ITEM.get());
        TinyRedstone.registerPanelCell(RSLatch.class, TINY_RS_LATCH_ITEM.get());
        TinyRedstone.registerPanelCell(EdgeDetector.class, TINY_EDGE_DETECTOR_ITEM.get());
        TinyRedstone.registerPanelCell(TFlipFlop.class, TINY_T_FLIP_FLOP_ITEM.get());
    }
}
