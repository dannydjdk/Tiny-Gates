package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinyredstone.blocks.PanelCellNeighbor;
import com.dannyandson.tinyredstone.blocks.PanelCellPos;
import com.dannyandson.tinyredstone.blocks.PanelTileRenderer;
import com.dannyandson.tinyredstone.blocks.Side;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.dannyandson.tinyredstone.api.IRenderTarget;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class ORGate extends AbstractGate {

    @Override
    public void render(PoseStack poseStack, IRenderTarget target, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = (alpha == 1.0) ? target.solid() : target.translucent();
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_or_gate = output?RenderHelper.getSprite(RenderHelper.TEXTURE_OR_GATE_ON):RenderHelper.getSprite(RenderHelper.TEXTURE_OR_GATE_OFF);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_or_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                rightNeighbor = cellPos.getNeighbor(Side.RIGHT),
                leftNeighbor = cellPos.getNeighbor(Side.LEFT);

        boolean output =
                (rightNeighbor!=null && rightNeighbor.getWeakRsOutput()>0) ||
                        (leftNeighbor!=null && leftNeighbor.getWeakRsOutput()>0);

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }
}