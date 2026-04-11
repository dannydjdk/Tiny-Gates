package com.dannyandson.tinygates.gui;

import com.dannyandson.tinygates.TinyGates;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class ClockGUI extends Screen {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 70;

    private ModWidget tickCount;

    private final Identifier GUI = Identifier.fromNamespaceAndPath(TinyGates.MODID, "textures/gui/transparent.png");

    protected ClockGUI(Component component) {
        super(component);
    }

    protected abstract Integer getTicks();
    protected abstract void setTicks(int ticks);

    @Override
    protected void init() {
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        Integer redstoneTicks = this.getTicks()/2;

        this.tickCount = new ModWidget(relX,relY+21,WIDTH,20, Component.nullToEmpty(redstoneTicks.toString()))
                .setTextHAlignment(ModWidget.HAlignment.CENTER).setTextVAlignment(ModWidget.VAlignment.MIDDLE);

        addRenderableOnly(new ModWidget(relX-1, relY-1, WIDTH+2, HEIGHT+2, 0xAA000000));
        addRenderableOnly(new ModWidget(relX, relY, WIDTH, HEIGHT, 0x88EEEEEE));
        addRenderableWidget(ModWidget.buildButton(relX + 35, relY + 48, 80, 20, Component.translatable("tinygates.close"), button -> close()));
        addRenderableOnly(this.tickCount);

        addRenderableOnly(new ModWidget(relX,relY+3,WIDTH-2,20,Component.translatable("tinygates.gui.clock.msg")))
                .setTextHAlignment(ModWidget.HAlignment.CENTER);
        addRenderableWidget(ModWidget.buildButton(relX + 10, relY + 15, 20, 20, Component.nullToEmpty("--"), button -> changeTicks(-20)));
        addRenderableWidget(ModWidget.buildButton(relX + 35, relY + 15, 20, 20, Component.nullToEmpty("-"), button -> changeTicks(-2)));

        addRenderableWidget(ModWidget.buildButton(relX + 95, relY + 15, 20, 20, Component.nullToEmpty("+"), button -> changeTicks(2)));
        addRenderableWidget(ModWidget.buildButton(relX + 125, relY + 15, 20, 20, Component.nullToEmpty("++"), button -> changeTicks(20)));
    }

    private void close() {
        minecraft.setScreen(null);
    }

    private void changeTicks(int change) {
        setTicks(getTicks() + change);

        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;

        Integer redstoneTicks = getTicks() / 2;
        this.removeWidget(this.tickCount);
        this.tickCount = new ModWidget(relX, relY + 21, WIDTH, 20, Component.nullToEmpty(redstoneTicks.toString()))
                .setTextHAlignment(ModWidget.HAlignment.CENTER).setTextVAlignment(ModWidget.VAlignment.MIDDLE);
        addRenderableOnly(this.tickCount);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTicks) {
        guiGraphics.fill(0, 0, this.width, this.height, 0xC0101010);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, GUI, relX, relY, 0, 0, WIDTH, HEIGHT, 256, 256);
    }
}
