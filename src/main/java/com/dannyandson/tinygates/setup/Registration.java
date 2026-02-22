package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.*;
import com.dannyandson.tinygates.items.GateBlockItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, TinyGates.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, TinyGates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, TinyGates.MODID);
    private static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinyGates.MODID);

    public static final Supplier<ANDGateBlock> AND_GATE_BLOCK = BLOCKS.register("and_gate_block", ANDGateBlock::new);
    public static final Supplier<Item> AND_GATE_ITEM = ITEMS.register("and_gate_item", () -> new GateBlockItem(AND_GATE_BLOCK.get()));
    public static final Supplier<BlockEntityType<ANDGateBlockEntity>> AND_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("and_gate_block", () -> BlockEntityType.Builder.of(ANDGateBlockEntity::new, AND_GATE_BLOCK.get()).build(null));

    public static final Supplier<ClockBlock> CLOCK_BLOCK = BLOCKS.register("clock_block", ClockBlock::new);
    public static final Supplier<Item> CLOCK_ITEM = ITEMS.register("clock_item", () -> new GateBlockItem(CLOCK_BLOCK.get()));
    public static final Supplier<BlockEntityType<ClockBlockEntity>> CLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("clock_block", () -> BlockEntityType.Builder.of(ClockBlockEntity::new, CLOCK_BLOCK.get()).build(null));

    public static final Supplier<CounterBlock> COUNTER_BLOCK = BLOCKS.register("counter_block", CounterBlock::new);
    public static final Supplier<Item> COUNTER_ITEM = ITEMS.register("counter_item", () -> new GateBlockItem(COUNTER_BLOCK.get()));
    public static final Supplier<BlockEntityType<CounterBlockEntity>> COUNTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("counter_block", () -> BlockEntityType.Builder.of(CounterBlockEntity::new, COUNTER_BLOCK.get()).build(null));

    public static final Supplier<EdgeDetectorBlock> EDGE_DETECTOR_BLOCK = BLOCKS.register("edge_detector_block", EdgeDetectorBlock::new);
    public static final Supplier<Item> EDGE_DETECTOR_ITEM = ITEMS.register("edge_detector_item", () -> new GateBlockItem(EDGE_DETECTOR_BLOCK.get()));
    public static final Supplier<BlockEntityType<EdgeDetectorBlockEntity>> EDGE_DETECTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("edge_detector_block", () -> BlockEntityType.Builder.of(EdgeDetectorBlockEntity::new, EDGE_DETECTOR_BLOCK.get()).build(null));

    public static final Supplier<NOTGateBlock> NOT_GATE_BLOCK = BLOCKS.register("not_gate_block", NOTGateBlock::new);
    public static final Supplier<Item> NOT_GATE_ITEM = ITEMS.register("not_gate_item", () -> new GateBlockItem(NOT_GATE_BLOCK.get()));
    public static final Supplier<BlockEntityType<NOTGateBlockEntity>> NOT_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("not_gate_block", () -> BlockEntityType.Builder.of(NOTGateBlockEntity::new, NOT_GATE_BLOCK.get()).build(null));

    public static final Supplier<ORGateBlock> OR_GATE_BLOCK = BLOCKS.register("or_gate_block", ORGateBlock::new);
    public static final Supplier<Item> OR_GATE_ITEM = ITEMS.register("or_gate_item", () -> new GateBlockItem(OR_GATE_BLOCK.get()));
    public static final Supplier<BlockEntityType<ORGateBlockEntity>> OR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("or_gate_block", () -> BlockEntityType.Builder.of(ORGateBlockEntity::new, OR_GATE_BLOCK.get()).build(null));

    public static final Supplier<RSLatchBlock> RS_LATCH_BLOCK = BLOCKS.register("rs_latch_block", RSLatchBlock::new);
    public static final Supplier<Item> RS_LATCH_ITEM = ITEMS.register("rs_latch_item", () -> new GateBlockItem(RS_LATCH_BLOCK.get()));
    public static final Supplier<BlockEntityType<RSLatchBlockEntity>> RS_LATCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("rs_latch_block", () -> BlockEntityType.Builder.of(RSLatchBlockEntity::new, RS_LATCH_BLOCK.get()).build(null));

    public static final Supplier<TFlipFlopBlock> T_FLIP_FLOP_BLOCK = BLOCKS.register("t_flip_flop_block", TFlipFlopBlock::new);
    public static final Supplier<Item> T_FLIP_FLOP_ITEM = ITEMS.register("t_flip_flop_item", () -> new GateBlockItem(T_FLIP_FLOP_BLOCK.get()));
    public static final Supplier<BlockEntityType<TFlipFlopBlockEntity>> T_FLIP_FLOP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("t_flip_flop_block", () -> BlockEntityType.Builder.of(TFlipFlopBlockEntity::new, T_FLIP_FLOP_BLOCK.get()).build(null));

    public static final Supplier<XORGateBlock> XOR_GATE_BLOCK = BLOCKS.register("xor_gate_block", XORGateBlock::new);
    public static final Supplier<Item> XOR_GATE_ITEM = ITEMS.register("xor_gate_item", () -> new GateBlockItem(XOR_GATE_BLOCK.get()));
    public static final Supplier<BlockEntityType<XORGateBlockEntity>> XOR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("xor_gate_block", () -> BlockEntityType.Builder.of(XORGateBlockEntity::new, XOR_GATE_BLOCK.get()).build(null));

    public static final DirectionProperty GATE_DIRECTION = DirectionProperty.create("gate_direction");

    public static Supplier<CreativeModeTab> CREATIVE_TAB = TAB.register("tinygatestab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("tinygates"))
                    .icon(() -> new ItemStack(Registration.AND_GATE_ITEM.get()))
                    .displayItems((parameters, output) -> ITEMS.getEntries().forEach(o -> output.accept(o.get())))
                    .build());

    // Called from main mod constructor with the mod event bus
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        TAB.register(modEventBus);
    }
}
