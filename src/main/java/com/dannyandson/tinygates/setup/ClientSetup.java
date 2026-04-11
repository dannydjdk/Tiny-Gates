package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.GateBlockRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = TinyGates.MODID, value = Dist.CLIENT)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModRegistration.AND_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.CLOCK_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.COUNTER_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.EDGE_DETECTOR_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.NOT_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.OR_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.RS_LATCH_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.T_FLIP_FLOP_BLOCK_ENTITY.get(), GateBlockRenderer::new);
        event.registerBlockEntityRenderer(ModRegistration.XOR_GATE_BLOCK_ENTITY.get(), GateBlockRenderer::new);
    }
}
