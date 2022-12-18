package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.*;
import com.dannyandson.tinygates.items.GateBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TinyGates.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TinyGates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TinyGates.MODID);


    public static final RegistryObject<ANDGateBlock> AND_GATE_BLOCK = BLOCKS.register("and_gate_block", ANDGateBlock::new);
    public static final RegistryObject<Item> AND_GATE_ITEM = ITEMS.register("and_gate_item",()->new GateBlockItem(AND_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<ANDGateBlockEntity>> AND_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("and_gate_block", () -> BlockEntityType.Builder.of(ANDGateBlockEntity::new, AND_GATE_BLOCK.get()).build(null));

    public static final RegistryObject<ClockBlock> CLOCK_BLOCK = BLOCKS.register("clock_block", ClockBlock::new);
    public static final RegistryObject<Item> CLOCK_ITEM = ITEMS.register("clock_item",()-> new GateBlockItem(CLOCK_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<ClockBlockEntity>> CLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("clock_block", () -> BlockEntityType.Builder.of(ClockBlockEntity::new, CLOCK_BLOCK.get()).build(null));

    public static final RegistryObject<CounterBlock> COUNTER_BLOCK = BLOCKS.register("counter_block", CounterBlock::new);
    public static final RegistryObject<Item> COUNTER_ITEM = ITEMS.register("counter_item",()-> new GateBlockItem(COUNTER_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<CounterBlockEntity>> COUNTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("counter_block", () -> BlockEntityType.Builder.of(CounterBlockEntity::new, COUNTER_BLOCK.get()).build(null));

    public static final RegistryObject<EdgeDetectorBlock> EDGE_DETECTOR_BLOCK = BLOCKS.register("edge_detector_block", EdgeDetectorBlock::new);
    public static final RegistryObject<Item> EDGE_DETECTOR_ITEM = ITEMS.register("edge_detector_item",()-> new GateBlockItem(EDGE_DETECTOR_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<EdgeDetectorBlockEntity>> EDGE_DETECTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("edge_detector_block", () -> BlockEntityType.Builder.of(EdgeDetectorBlockEntity::new, EDGE_DETECTOR_BLOCK.get()).build(null));

    public static final RegistryObject<NOTGateBlock> NOT_GATE_BLOCK = BLOCKS.register("not_gate_block", NOTGateBlock::new);
    public static final RegistryObject<Item> NOT_GATE_ITEM = ITEMS.register("not_gate_item",()-> new GateBlockItem(NOT_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<NOTGateBlockEntity>> NOT_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("not_gate_block", () -> BlockEntityType.Builder.of(NOTGateBlockEntity::new, NOT_GATE_BLOCK.get()).build(null));

    public static final RegistryObject<ORGateBlock> OR_GATE_BLOCK = BLOCKS.register("or_gate_block", ORGateBlock::new);
    public static final RegistryObject<Item> OR_GATE_ITEM = ITEMS.register("or_gate_item",()-> new GateBlockItem(OR_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<ORGateBlockEntity>> OR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("or_gate_block", () -> BlockEntityType.Builder.of(ORGateBlockEntity::new, OR_GATE_BLOCK.get()).build(null));

    public static final RegistryObject<RSLatchBlock> RS_LATCH_BLOCK = BLOCKS.register("rs_latch_block", RSLatchBlock::new);
    public static final RegistryObject<Item> RS_LATCH_ITEM = ITEMS.register("rs_latch_item",()-> new GateBlockItem(RS_LATCH_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<RSLatchBlockEntity>> RS_LATCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("rs_latch_block", () -> BlockEntityType.Builder.of(RSLatchBlockEntity::new, RS_LATCH_BLOCK.get()).build(null));

    public static final RegistryObject<TFlipFlopBlock> T_FLIP_FLOP_BLOCK = BLOCKS.register("t_flip_flop_block", TFlipFlopBlock::new);
    public static final RegistryObject<Item> T_FLIP_FLOP_ITEM = ITEMS.register("t_flip_flop_item",()-> new GateBlockItem(T_FLIP_FLOP_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<TFlipFlopBlockEntity>> T_FLIP_FLOP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("t_flip_flop_block", () -> BlockEntityType.Builder.of(TFlipFlopBlockEntity::new, T_FLIP_FLOP_BLOCK.get()).build(null));

    public static final RegistryObject<XORGateBlock> XOR_GATE_BLOCK = BLOCKS.register("xor_gate_block", XORGateBlock::new);
    public static final RegistryObject<Item> XOR_GATE_ITEM = ITEMS.register("xor_gate_item",()-> new GateBlockItem(XOR_GATE_BLOCK.get()));
    public static final RegistryObject<BlockEntityType<XORGateBlockEntity>> XOR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("xor_gate_block", () -> BlockEntityType.Builder.of(XORGateBlockEntity::new, XOR_GATE_BLOCK.get()).build(null));


    public static final DirectionProperty GATE_DIRECTION = DirectionProperty.create("gate_direction");


    //called from main mod constructor
    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}
