package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class EdgeDetector extends AbstractGate {

    public static ResourceLocation TEXTURE_RISING_ON = new ResourceLocation(TinyGates.MODID,"block/edge_detector_rising_on");
    public static ResourceLocation TEXTURE_RISING_OFF = new ResourceLocation(TinyGates.MODID,"block/edge_detector_rising_off");
    public static ResourceLocation TEXTURE_FALLING_ON = new ResourceLocation(TinyGates.MODID,"block/edge_detector_falling_on");
    public static ResourceLocation TEXTURE_FALLING_OFF = new ResourceLocation(TinyGates.MODID,"block/edge_detector_falling_off");

    private boolean rising=true;
    private boolean input=false;
    private int ticks=0;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(output?(rising?TEXTURE_RISING_ON:TEXTURE_FALLING_ON):(rising?TEXTURE_RISING_OFF:TEXTURE_FALLING_OFF));

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK);
        boolean previousinput = this.input;
        this.input=backNeighbor!=null && backNeighbor.getWeakRsOutput()>0;

        if ((rising && !previousinput && this.input) || (!rising && previousinput && !this.input)){
            this.output=true;
            this.ticks=2;
            return true;
        }

        return false;
    }

    @Override
    public boolean tick(PanelCellPos cellPos) {
        if (this.output){
            this.ticks--;
            if (ticks<=0) {
                this.output = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasActivation(){return true;}

    @Override
    public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
        this.rising=!this.rising;
        return false;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = super.writeNBT();
        compoundTag.putBoolean("input",input);
        compoundTag.putBoolean("rising",rising);
        compoundTag.putInt("ticks",ticks);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        super.readNBT(compoundTag);
        input=compoundTag.getBoolean("input");
        rising=compoundTag.getBoolean("rising");
        ticks=compoundTag.getInt("ticks");
    }

}
