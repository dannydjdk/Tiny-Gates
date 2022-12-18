package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.GateBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TinyGates.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

    public static void init(final FMLClientSetupEvent event) {
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS)) {
            return;
        }

        event.addSprite(RenderHelper.TEXTURE_AND_GATE_ON);
        event.addSprite(RenderHelper.TEXTURE_AND_GATE_OFF);
        event.addSprite(RenderHelper.TEXTURE_OR_GATE_ON);
        event.addSprite(RenderHelper.TEXTURE_OR_GATE_OFF);
        event.addSprite(RenderHelper.TEXTURE_XOR_GATE_ON);
        event.addSprite(RenderHelper.TEXTURE_XOR_GATE_OFF);
        event.addSprite(RenderHelper.TEXTURE_NOT_GATE_ON);
        event.addSprite(RenderHelper.TEXTURE_NOT_GATE_OFF);
        event.addSprite(RenderHelper.TEXTURE_T_ON_ON);
        event.addSprite(RenderHelper.TEXTURE_T_OFF_ON);
        event.addSprite(RenderHelper.TEXTURE_T_ON_OFF);
        event.addSprite(RenderHelper.TEXTURE_T_OFF_OFF);
        event.addSprite(RenderHelper.TEXTURE_RS_ON);
        event.addSprite(RenderHelper.TEXTURE_RS_OFF);
        for(ResourceLocation rs : RenderHelper.TEXTURES_COUNTER){
            event.addSprite(rs);
        }
        for(ResourceLocation rs : RenderHelper.TEXTURES_CLOCK){
            event.addSprite(rs);
        }
        event.addSprite(RenderHelper.TEXTURE_FALLING_OFF);
        event.addSprite(RenderHelper.TEXTURE_FALLING_ON);
        event.addSprite(RenderHelper.TEXTURE_RISING_OFF);
        event.addSprite(RenderHelper.TEXTURE_RISING_ON);

        event.addSprite(GateBlockRenderer.TEXTURE_BLANK_PANEL);

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
