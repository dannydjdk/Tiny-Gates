package com.dannyandson.tinygates.gui;

import com.dannyandson.tinygates.gates.Clock;
import com.dannyandson.tinygates.network.ClockTickSync;
import com.dannyandson.tinygates.network.ModNetworkHandler;
import com.dannyandson.tinyredstone.blocks.PanelTile;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class TinyClockGUI extends ClockGUI {
    private final PanelTile panelTile;
    private final Integer cellIndex;
    private final Clock clockCell;

    protected TinyClockGUI(PanelTile panelTile, Integer cellIndex, Clock clockCell) {
        super(Component.translatable("tinygates:clockGUI"));
        this.panelTile = panelTile;
        this.cellIndex = cellIndex;
        this.clockCell = clockCell;
    }

    @Override
    protected Integer getTicks() {
        return clockCell.getTicks();
    }

    @Override
    protected void setTicks(int ticks) {
        clockCell.setTicks(ticks);
        ModNetworkHandler.sendToServer(new ClockTickSync(panelTile.getBlockPos(), cellIndex, clockCell.getTicks()));

    }

    public static void open(PanelTile panelTile, Integer cellIndex, Clock clockCell) {
        Minecraft.getInstance().setScreen(new TinyClockGUI(panelTile, cellIndex, clockCell));
    }
}
