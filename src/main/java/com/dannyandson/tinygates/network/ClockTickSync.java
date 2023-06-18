package com.dannyandson.tinygates.network;

import com.dannyandson.tinygates.blocks.ClockBlockEntity;
import com.dannyandson.tinygates.gates.Clock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClockTickSync {
    private final BlockPos pos;
    private final int cellIndex;
    private final int ticks;

    public ClockTickSync(BlockPos pos, int cellIndex, int ticks)
    {
        this.pos=pos;
        this.cellIndex=cellIndex;
        this.ticks = ticks;
    }

    public ClockTickSync(FriendlyByteBuf buffer)
    {
        this.pos= buffer.readBlockPos();
        this.cellIndex=buffer.readInt();
        this.ticks=buffer.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeBlockPos(pos);
        buf.writeInt(cellIndex);
        buf.writeInt(ticks);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(()-> {
            BlockEntity te =  ctx.get().getSender().level().getBlockEntity(this.pos);
            if (cellIndex==-1){
                if (te instanceof ClockBlockEntity clockBlockEntity){
                    clockBlockEntity.setTicks(this.ticks);
                }
            } else {
                Clock.clockTickSync(te,cellIndex,ticks);
            }
             ctx.get().setPacketHandled(true);
        });
        return true;
    }
}
