package com.dannyandson.tinygates.gui;

import com.dannyandson.tinygates.blocks.ClockBlockEntity;
import com.dannyandson.tinygates.network.ClockTickSync;
import com.dannyandson.tinygates.network.ModNetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class ClockBlockGUI extends ClockGUI{

    private final ClockBlockEntity clockBlockEntity;

    protected ClockBlockGUI(ClockBlockEntity clockBlockEntity){
        super(new TranslatableComponent("tinygates:clockGUI"));
        this.clockBlockEntity = clockBlockEntity;
    }

    @Override
    protected Integer getTicks() {
        return clockBlockEntity.getTicks();
    }

    @Override
    protected void setTicks(int ticks) {
        clockBlockEntity.setTicks(ticks);
        ModNetworkHandler.sendToServer(new ClockTickSync(clockBlockEntity.getBlockPos(), -1, clockBlockEntity.getTicks()));
    }

    public static void open(ClockBlockEntity clockBlockEntity) {
        Minecraft.getInstance().setScreen(new ClockBlockGUI(clockBlockEntity));
    }
}
