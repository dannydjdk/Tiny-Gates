package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.setup.RegistrationTinyRedstone;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetworkHandler {
    private static SimpleChannel INSTANCE;
    private static int ID = 0;
    private static final String PROTOCOL_VERSION = "1.2";

    private static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(TinyGates.MODID, "tinyredstone"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals);

        INSTANCE.messageBuilder(ClockTickSync.class,nextID())
                .encoder(ClockTickSync::toBytes)
                .decoder(ClockTickSync::new)
                .consumerNetworkThread(ClockTickSync::handle)
                .add();

        if (ModList.get().isLoaded("tinyredstone"))
            RegistrationTinyRedstone.registerTinyRedstoneNetworkHandlers(INSTANCE,nextID());

    }

    public static SimpleChannel getINSTANCE(){
        return INSTANCE;
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }

}