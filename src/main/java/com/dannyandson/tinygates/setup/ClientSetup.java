package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.GateBlockRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = TinyGates.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(Registration.AND_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.CLOCK_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.COUNTER_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.EDGE_DETECTOR_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.NOT_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.OR_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.RS_LATCH_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.T_FLIP_FLOP_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(Registration.XOR_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
    }
}
