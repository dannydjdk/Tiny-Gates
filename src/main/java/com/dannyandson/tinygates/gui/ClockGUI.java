package com.dannyandson.tinygates.gui;

import com.dannyandson.tinygates.TinyGates;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public abstract class ClockGUI extends Screen {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 70;

    private ModWidget tickCount;

    private final ResourceLocation GUI = new ResourceLocation(TinyGates.MODID, "textures/gui/transparent.png");

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

        addRenderableWidget(new ModWidget(relX-1, relY-1, WIDTH+2, HEIGHT+2, 0xAA000000));
        addRenderableWidget(new ModWidget(relX, relY, WIDTH, HEIGHT, 0x88EEEEEE));
        addRenderableWidget(ModWidget.buildButton(relX + 35, relY + 48, 80, 20, Component.translatable("tinygates.close"), button -> close()));
        addRenderableWidget(this.tickCount);

        addRenderableWidget(new ModWidget(relX,relY+3,WIDTH-2,20,Component.translatable("tinygates.gui.clock.msg")))
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
        addRenderableWidget(this.tickCount);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShaderTexture(0, GUI);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindForSetup(GUI);
        int relX = (this.width - WIDTH) / 2;
        int relY = (this.height - HEIGHT) / 2;
        guiGraphics.blit(GUI, relX, relY, 0, 0, WIDTH, HEIGHT);

        super.render(guiGraphics,mouseX, mouseY, partialTicks);
    }


}
