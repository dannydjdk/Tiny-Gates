package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.TinyGates;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TinyGates.MODID)
public class ModNetworkHandler {

    @SubscribeEvent
    public static void registerMessages(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(TinyGates.MODID).versioned("1.2");

        registrar.playToServer(
                ClockTickSync.TYPE,
                ClockTickSync.STREAM_CODEC,
                ClockTickSync::handle
        );
    }

    public static void sendToServer(Object packet) {
        if (packet instanceof ClockTickSync clockTickSync) {
            var connection = Minecraft.getInstance().getConnection();
            if (connection != null) {
                connection.send(new ServerboundCustomPayloadPacket(clockTickSync));
            }
        }
    }
}
