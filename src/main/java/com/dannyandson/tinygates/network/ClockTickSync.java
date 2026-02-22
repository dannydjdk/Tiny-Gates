package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.blocks.ClockBlockEntity;
import com.dannyandson.tinygates.gates.Clock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ClockTickSync(BlockPos pos, int cellIndex, int ticks) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ClockTickSync> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(TinyGates.MODID, "clock_tick_sync"));

    public static final StreamCodec<FriendlyByteBuf, ClockTickSync> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC, ClockTickSync::pos,
            ByteBufCodecs.INT, ClockTickSync::cellIndex,
            ByteBufCodecs.INT, ClockTickSync::ticks,
            ClockTickSync::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(ClockTickSync packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            BlockEntity te = ctx.player().level().getBlockEntity(packet.pos());
            if (packet.cellIndex() == -1) {
                if (te instanceof ClockBlockEntity clockBlockEntity) {
                    clockBlockEntity.setTicks(packet.ticks());
                }
            } else {
                Clock.clockTickSync(te, packet.cellIndex(), packet.ticks());
            }
        });
    }
}
