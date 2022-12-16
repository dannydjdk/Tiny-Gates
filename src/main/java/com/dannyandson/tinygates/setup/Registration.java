package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.ANDGateBlock;
import com.dannyandson.tinygates.blocks.ANDGateBlockEntity;
import com.dannyandson.tinygates.blocks.ORGateBlock;
import com.dannyandson.tinygates.blocks.ORGateBlockEntity;
import com.dannyandson.tinygates.gates.*;
import com.dannyandson.tinygates.items.GateBlockItem;
import com.dannyandson.tinygates.items.PanelCellGateItem;
import com.dannyandson.tinyredstone.TinyRedstone;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinyGates.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinyGates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TinyGates.MODID);

    public static final RegistryObject<Item> TINY_AND_GATE_ITEM = ITEMS.register("tiny_and_gate", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_OR_GATE_ITEM = ITEMS.register("tiny_or_gate", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_XOR_GATE_ITEM = ITEMS.register("tiny_xor_gate", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_NOT_GATE_ITEM = ITEMS.register("tiny_not_gate", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_CLOCK_ITEM = ITEMS.register("tiny_clock", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_COUNTER_ITEM = ITEMS.register("tiny_counter", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_RS_LATCH_ITEM = ITEMS.register("tiny_rs_latch", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_EDGE_DETECTOR_ITEM = ITEMS.register("tiny_edge_detector", PanelCellGateItem::new);
    public static final RegistryObject<Item> TINY_T_FLIP_FLOP_ITEM = ITEMS.register("tiny_t_flip_flop", PanelCellGateItem::new);


    public static final RegistryObject<ANDGateBlock> AND_GATE_BLOCK = BLOCKS.register("and_gate_block", ANDGateBlock::new);
    public static final RegistryObject<Item> AND_GATE_ITEM = ITEMS.register("and_gate_item",()->new GateBlockItem(AND_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<ANDGateBlockEntity>> AND_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("and_gate_block", () -> BlockEntityType.Builder.of(ANDGateBlockEntity::new, AND_GATE_BLOCK.get()).build(null));

    public static final RegistryObject<ORGateBlock> OR_GATE_BLOCK = BLOCKS.register("or_gate_block", ORGateBlock::new);
    public static final RegistryObject<Item> OR_GATE_ITEM = ITEMS.register("or_gate_item",()-> new GateBlockItem(OR_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<ORGateBlockEntity>> OR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("or_gate_block", () -> BlockEntityType.Builder.of(ORGateBlockEntity::new, OR_GATE_BLOCK.get()).build(null));


    public static final DirectionProperty GATE_DIRECTION = DirectionProperty.create("gate_direction");


    //called from main mod constructor
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    //called at FMLCommonSetupEvent in ModSetup
    public static void registerPanelCells(){
        TinyRedstone.registerPanelCell(ANDGate.class, TINY_AND_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(ORGate.class, TINY_OR_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(XORGate.class, TINY_XOR_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(NOTGate.class, TINY_NOT_GATE_ITEM.get());
        TinyRedstone.registerPanelCell(Clock.class,TINY_CLOCK_ITEM.get());
        TinyRedstone.registerPanelCell(Counter.class, TINY_COUNTER_ITEM.get());
        TinyRedstone.registerPanelCell(RSLatch.class, TINY_RS_LATCH_ITEM.get());
        TinyRedstone.registerPanelCell(EdgeDetector.class,TINY_EDGE_DETECTOR_ITEM.get());
        TinyRedstone.registerPanelCell(TFlipFlop.class, TINY_T_FLIP_FLOP_ITEM.get());
    }


}
