package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.*;
import com.dannyandson.tinygates.items.GateBlockItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRegistration {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TinyGates.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TinyGates.MODID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TinyGates.MODID);
    private static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinyGates.MODID);

    private static final BlockBehaviour.Properties GATE_BLOCK_PROPS = BlockBehaviour.Properties.of().sound(SoundType.STONE).strength(0.2f);

    public static final DeferredBlock<ANDGateBlock> AND_GATE_BLOCK = BLOCKS.registerBlock("and_gate_block", ANDGateBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> AND_GATE_ITEM = ITEMS.registerItem("and_gate_item", props -> new GateBlockItem(AND_GATE_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<ANDGateBlockEntity>> AND_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("and_gate_block", () -> new BlockEntityType<>(ANDGateBlockEntity::new, AND_GATE_BLOCK.get()));

    public static final DeferredBlock<ClockBlock> CLOCK_BLOCK = BLOCKS.registerBlock("clock_block", ClockBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> CLOCK_ITEM = ITEMS.registerItem("clock_item", props -> new GateBlockItem(CLOCK_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<ClockBlockEntity>> CLOCK_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("clock_block", () -> new BlockEntityType<>(ClockBlockEntity::new, CLOCK_BLOCK.get()));

    public static final DeferredBlock<CounterBlock> COUNTER_BLOCK = BLOCKS.registerBlock("counter_block", CounterBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> COUNTER_ITEM = ITEMS.registerItem("counter_item", props -> new GateBlockItem(COUNTER_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<CounterBlockEntity>> COUNTER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("counter_block", () -> new BlockEntityType<>(CounterBlockEntity::new, COUNTER_BLOCK.get()));

    public static final DeferredBlock<EdgeDetectorBlock> EDGE_DETECTOR_BLOCK = BLOCKS.registerBlock("edge_detector_block", EdgeDetectorBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> EDGE_DETECTOR_ITEM = ITEMS.registerItem("edge_detector_item", props -> new GateBlockItem(EDGE_DETECTOR_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<EdgeDetectorBlockEntity>> EDGE_DETECTOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("edge_detector_block", () -> new BlockEntityType<>(EdgeDetectorBlockEntity::new, EDGE_DETECTOR_BLOCK.get()));

    public static final DeferredBlock<NOTGateBlock> NOT_GATE_BLOCK = BLOCKS.registerBlock("not_gate_block", NOTGateBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> NOT_GATE_ITEM = ITEMS.registerItem("not_gate_item", props -> new GateBlockItem(NOT_GATE_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<NOTGateBlockEntity>> NOT_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("not_gate_block", () -> new BlockEntityType<>(NOTGateBlockEntity::new, NOT_GATE_BLOCK.get()));

    public static final DeferredBlock<ORGateBlock> OR_GATE_BLOCK = BLOCKS.registerBlock("or_gate_block", ORGateBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> OR_GATE_ITEM = ITEMS.registerItem("or_gate_item", props -> new GateBlockItem(OR_GATE_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<ORGateBlockEntity>> OR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("or_gate_block", () -> new BlockEntityType<>(ORGateBlockEntity::new, OR_GATE_BLOCK.get()));

    public static final DeferredBlock<RSLatchBlock> RS_LATCH_BLOCK = BLOCKS.registerBlock("rs_latch_block", RSLatchBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> RS_LATCH_ITEM = ITEMS.registerItem("rs_latch_item", props -> new GateBlockItem(RS_LATCH_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<RSLatchBlockEntity>> RS_LATCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("rs_latch_block", () -> new BlockEntityType<>(RSLatchBlockEntity::new, RS_LATCH_BLOCK.get()));

    public static final DeferredBlock<TFlipFlopBlock> T_FLIP_FLOP_BLOCK = BLOCKS.registerBlock("t_flip_flop_block", TFlipFlopBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> T_FLIP_FLOP_ITEM = ITEMS.registerItem("t_flip_flop_item", props -> new GateBlockItem(T_FLIP_FLOP_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<TFlipFlopBlockEntity>> T_FLIP_FLOP_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("t_flip_flop_block", () -> new BlockEntityType<>(TFlipFlopBlockEntity::new, T_FLIP_FLOP_BLOCK.get()));

    public static final DeferredBlock<XORGateBlock> XOR_GATE_BLOCK = BLOCKS.registerBlock("xor_gate_block", XORGateBlock::new, GATE_BLOCK_PROPS);
    public static final DeferredItem<Item> XOR_GATE_ITEM = ITEMS.registerItem("xor_gate_item", props -> new GateBlockItem(XOR_GATE_BLOCK.get(), props));
    public static final Supplier<BlockEntityType<XORGateBlockEntity>> XOR_GATE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("xor_gate_block", () -> new BlockEntityType<>(XORGateBlockEntity::new, XOR_GATE_BLOCK.get()));

    public static final EnumProperty<Direction> GATE_DIRECTION = EnumProperty.create("gate_direction", Direction.class);

    public static Supplier<CreativeModeTab> CREATIVE_TAB = TAB.register("tinygatestab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("tinygates"))
                    .icon(() -> new ItemStack(ModRegistration.AND_GATE_ITEM.get()))
                    .displayItems((parameters, output) -> ITEMS.getEntries().forEach(o -> output.accept(o.get())))
                    .build());

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        TAB.register(modEventBus);
    }
}
