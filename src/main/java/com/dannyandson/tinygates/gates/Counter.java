package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;

public class Counter extends AbstractGate {

    private int output = 0;
    private boolean input = false;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(RenderHelper.TEXTURES_COUNTER[output]);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK),
                leftNeighbor = cellPos.getNeighbor(Side.LEFT),
                rightNeighbor = cellPos.getNeighbor(Side.RIGHT);
        int output= this.output;
        boolean previousinput = this.input;
        this.input = backNeighbor!=null && backNeighbor.getWeakRsOutput()>0;

        if (leftNeighbor!=null && leftNeighbor.getWeakRsOutput()>0) {
            //left input locks counter
            return false;
        }
        else if (rightNeighbor!=null && rightNeighbor.getWeakRsOutput()>0){
            //right input resets counter
            output = 0;
        }
        else if (!previousinput && this.input && output<15){
            output++;
        }

        if (this.output != output){
            this.output = output;
            return true;
        }
        return false;
    }

    @Override
    public int getWeakRsOutput(Side side) {
        return (side==Side.FRONT)?this.output:0;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("output",output);
        compoundTag.putBoolean("input",input);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        output=compoundTag.getInt("output");
        input=compoundTag.getBoolean("input");
    }

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Output", this.output + "");
    }
}
