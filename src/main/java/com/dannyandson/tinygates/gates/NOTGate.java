package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.dannyandson.tinyredstone.api.IRenderTarget;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class NOTGate extends AbstractGate {

    @Override
    public void render(PoseStack poseStack, IRenderTarget target, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = (alpha == 1.0) ? target.solid() : target.translucent();
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_not_gate = output?RenderHelper.getSprite(RenderHelper.TEXTURE_NOT_GATE_ON):RenderHelper.getSprite(RenderHelper.TEXTURE_NOT_GATE_OFF);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_not_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK);

        boolean output =!(backNeighbor!=null && backNeighbor.getWeakRsOutput()>0);

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }
}