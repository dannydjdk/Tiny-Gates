package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.dannyandson.tinyredstone.api.IRenderTarget;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;

public class TFlipFlop extends AbstractGate {

    private boolean input=false;

    @Override
    public void render(PoseStack poseStack, IRenderTarget target, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = (alpha == 1.0) ? target.solid() : target.translucent();
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(input?(output? RenderHelper.TEXTURE_T_ON_ON : RenderHelper.TEXTURE_T_ON_OFF):(output? RenderHelper.TEXTURE_T_OFF_ON : RenderHelper.TEXTURE_T_OFF_OFF));

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK);

        if (!input && (backNeighbor!=null && backNeighbor.getWeakRsOutput()>0)){
            this.input=true;
            this.output=!this.output;
            return true;
        }
        else if (backNeighbor==null || backNeighbor.getWeakRsOutput()==0)
            this.input=false;

        return false;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = super.writeNBT();
        compoundTag.putBoolean("input",input);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        super.readNBT(compoundTag);
        input=compoundTag.getBooleanOr("input", false);
    }
}