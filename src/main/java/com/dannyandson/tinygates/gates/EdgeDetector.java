package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class EdgeDetector extends AbstractGate {

    public static ResourceLocation TEXTURE_RISING_ON = new ResourceLocation(TinyGates.MODID,"block/edge_detector_rising_on");
    public static ResourceLocation TEXTURE_RISING_OFF = new ResourceLocation(TinyGates.MODID,"block/edge_detector_rising_off");
    public static ResourceLocation TEXTURE_FALLING_ON = new ResourceLocation(TinyGates.MODID,"block/edge_detector_falling_on");
    public static ResourceLocation TEXTURE_FALLING_OFF = new ResourceLocation(TinyGates.MODID,"block/edge_detector_falling_off");

    private boolean rising=true;
    private boolean input=false;
    private int ticks=0;

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, float alpha){
        IVertexBuilder builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(output?(rising?TEXTURE_RISING_ON:TEXTURE_FALLING_ON):(rising?TEXTURE_RISING_OFF:TEXTURE_FALLING_OFF));

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(matrixStack,builder,sprite_gate,sprite,combinedLight,alpha);
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
    public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, PlayerEntity player) {
        this.rising=!this.rising;
        return false;
    }

    @Override
    public CompoundNBT writeNBT() {
        CompoundNBT compoundTag = super.writeNBT();
        compoundTag.putBoolean("input",input);
        compoundTag.putBoolean("rising",rising);
        compoundTag.putInt("ticks",ticks);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundNBT compoundTag) {
        super.readNBT(compoundTag);
        input=compoundTag.getBoolean("input");
        rising=compoundTag.getBoolean("rising");
        ticks=compoundTag.getInt("ticks");
    }

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Detecting", (this.rising?"Rising":"Falling") + " edge");
    }

}
