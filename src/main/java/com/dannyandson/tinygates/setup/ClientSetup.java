package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.gates.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
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
        if (!event.getMap().location().equals(InventoryMenu.BLOCK_ATLAS)) {
            return;
        }

        event.addSprite(ANDGate.TEXTURE_AND_GATE_ON);
        event.addSprite(ANDGate.TEXTURE_AND_GATE_OFF);
        event.addSprite(ORGate.TEXTURE_OR_GATE_ON);
        event.addSprite(ORGate.TEXTURE_OR_GATE_OFF);
        event.addSprite(XORGate.TEXTURE_XOR_GATE_ON);
        event.addSprite(XORGate.TEXTURE_XOR_GATE_OFF);
        event.addSprite(NOTGate.TEXTURE_NOT_GATE_ON);
        event.addSprite(NOTGate.TEXTURE_NOT_GATE_OFF);
        event.addSprite(TFlipFlop.TEXTURE_ON_ON);
        event.addSprite(TFlipFlop.TEXTURE_OFF_ON);
        event.addSprite(TFlipFlop.TEXTURE_ON_OFF);
        event.addSprite(TFlipFlop.TEXTURE_OFF_OFF);
        event.addSprite(RSLatch.TEXTURE_ON);
        event.addSprite(RSLatch.TEXTURE_OFF);
        for(ResourceLocation rs : Counter.TEXTURES){
            event.addSprite(rs);
        }
        for(ResourceLocation rs : Clock.TEXTURES){
            event.addSprite(rs);
        }
        event.addSprite(EdgeDetector.TEXTURE_FALLING_OFF);
        event.addSprite(EdgeDetector.TEXTURE_FALLING_ON);
        event.addSprite(EdgeDetector.TEXTURE_RISING_OFF);
        event.addSprite(EdgeDetector.TEXTURE_RISING_ON);
    }

}
