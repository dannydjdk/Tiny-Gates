package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinyredstone.blocks.PanelTile;
import com.dannyandson.tinyredstone.network.PanelCellSync;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
                .consumer(ClockTickSync::handle)
                .add();

        INSTANCE.messageBuilder(PanelCellSync.class,nextID())
                .encoder(PanelCellSync::toBytes)
                .decoder(PanelCellSync::new)
                .consumer(PanelCellSync::handle)
                .add();
    }

    public static void sendToClient(Object packet, PanelTile panelTile) {
        BlockPos pos = panelTile.getBlockPos();
        for (PlayerEntity player : panelTile.getLevel().players()) {
            if (player instanceof ServerPlayerEntity && player.distanceToSqr((double) pos.getX(), (double) pos.getY(), (double) pos.getZ()) < 64d) {
                INSTANCE.sendTo(packet, ((ServerPlayerEntity) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
            }
        }
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }

}