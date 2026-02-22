package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.TinyGates;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TinyGates.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworkHandler {

    @SubscribeEvent
    public static void registerMessages(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(TinyGates.MODID).versioned("1.2");

        registrar.playToServer(
                ClockTickSync.TYPE,
                ClockTickSync.STREAM_CODEC,
                ClockTickSync::handle
        );

        // NOTE: If Tiny Redstone is loaded, its PanelCellSync packet registration
        // needs to be handled by the Tiny Redstone mod itself in NeoForge 1.21.1.
        // The old pattern of registering another mod's packets via SimpleChannel
        // is no longer applicable with the new payload system.
    }

    public static void sendToServer(Object packet) {
        if (packet instanceof ClockTickSync clockTickSync) {
            PacketDistributor.sendToServer(clockTickSync);
        }
    }
}
