package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class Counter extends AbstractGate {

    public static ResourceLocation[] TEXTURES = {
            new ResourceLocation(TinyGates.MODID, "block/counter_0"),
            new ResourceLocation(TinyGates.MODID, "block/counter_1"),
            new ResourceLocation(TinyGates.MODID, "block/counter_2"),
            new ResourceLocation(TinyGates.MODID, "block/counter_3"),
            new ResourceLocation(TinyGates.MODID, "block/counter_4"),
            new ResourceLocation(TinyGates.MODID, "block/counter_5"),
            new ResourceLocation(TinyGates.MODID, "block/counter_6"),
            new ResourceLocation(TinyGates.MODID, "block/counter_7"),
            new ResourceLocation(TinyGates.MODID, "block/counter_8"),
            new ResourceLocation(TinyGates.MODID, "block/counter_9"),
             new ResourceLocation(TinyGates.MODID, "block/counter_10"),
             new ResourceLocation(TinyGates.MODID, "block/counter_11"),
             new ResourceLocation(TinyGates.MODID, "block/counter_12"),
             new ResourceLocation(TinyGates.MODID, "block/counter_13"),
             new ResourceLocation(TinyGates.MODID, "block/counter_14"),
             new ResourceLocation(TinyGates.MODID, "block/counter_15")
    };
    private int output = 0;
    private boolean input = false;

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, float alpha){
        IVertexBuilder builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(TEXTURES[output]);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(matrixStack,builder,sprite_gate,sprite,combinedLight,alpha);
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
    public CompoundNBT writeNBT() {
        CompoundNBT compoundTag = new CompoundNBT();
        compoundTag.putInt("output",output);
        compoundTag.putBoolean("input",input);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundNBT compoundTag) {
        output=compoundTag.getInt("output");
        input=compoundTag.getBoolean("input");
    }

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Output", this.output + "");
    }
}
