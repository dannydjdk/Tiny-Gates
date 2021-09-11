package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.gui.ClockGUI;
import com.dannyandson.tinygates.network.ModNetworkHandler;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.blocks.*;
import com.dannyandson.tinyredstone.network.PanelCellSync;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class Clock extends AbstractGate {

    public static ResourceLocation[] TEXTURES = {
            new ResourceLocation(TinyGates.MODID, "block/clock_1"),
            new ResourceLocation(TinyGates.MODID, "block/clock_2"),
            new ResourceLocation(TinyGates.MODID, "block/clock_3"),
            new ResourceLocation(TinyGates.MODID, "block/clock_4"),
            new ResourceLocation(TinyGates.MODID, "block/clock_5"),
            new ResourceLocation(TinyGates.MODID, "block/clock_6"),
            new ResourceLocation(TinyGates.MODID, "block/clock_7"),
            new ResourceLocation(TinyGates.MODID, "block/clock_8"),
            new ResourceLocation(TinyGates.MODID, "block/clock_9")
    };
    private int ticks = 20;
    private int tick = 0;
    private boolean input = false;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(this.output?TEXTURES[TEXTURES.length-1]:TEXTURES[Math.floorDiv(tick*(TEXTURES.length-1),ticks)]);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK);
        this.input = backNeighbor!=null && backNeighbor.getWeakRsOutput()>0;
        return false;
    }

    @Override
    public boolean tick(PanelCellPos cellPos) {
        if (!this.input) {
            this.tick++;
            if (this.tick >= this.ticks) {
                this.output = true;
                this.tick = 0;
                return true;
            } else if (this.output) {
                this.output = false;
                return true;
            }else {
                ModNetworkHandler.sendToClient(new PanelCellSync(cellPos.getPanelTile().getBlockPos(), cellPos.getIndex(), writeNBT()), cellPos.getPanelTile());
            }
        }
        return false;
    }

    @Override
    public boolean hasActivation(){return true;}

    @Override
    public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
        PanelTile panelTile = cellPos.getPanelTile();
        if (panelTile.getLevel().isClientSide)
            ClockGUI.open(panelTile, cellPos.getIndex(), this);
        return false;
    }


    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = super.writeNBT();
        compoundTag.putInt("ticks",ticks);
        compoundTag.putInt("tick",tick);
        compoundTag.putBoolean("input",input);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        super.readNBT(compoundTag);
        ticks=compoundTag.getInt("ticks");
        tick=compoundTag.getInt("tick");
        input=compoundTag.getBoolean("input");
    }

    public Integer getTicks() {
        return this.ticks;
    }
    public void setTicks(Integer ticks){
        if (ticks<2)this.ticks=2;
        else if(ticks>200)this.ticks=200;
        else this.ticks=ticks;
    }

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Rate", this.ticks/2 + " ticks");
        if(this.input) {
            overlayBlockInfo.addInfo("Locked");
        }
    }
}
