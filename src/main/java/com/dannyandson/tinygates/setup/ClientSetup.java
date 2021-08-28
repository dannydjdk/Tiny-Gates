package com.dannyandson.tinygates.setup;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.gates.ANDGate;
import com.dannyandson.tinygates.gates.ORGate;
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
    }

}
