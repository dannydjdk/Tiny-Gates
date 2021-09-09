package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinyredstone.blocks.*;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

public class RSLatch extends AbstractGate {

    public static ResourceLocation TEXTURE_ON = new ResourceLocation(TinyGates.MODID,"block/rs_latch_on");
    public static ResourceLocation TEXTURE_OFF = new ResourceLocation(TinyGates.MODID,"block/rs_latch_off");

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay, float alpha){
        IVertexBuilder builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_xor_gate = output?RenderHelper.getSprite(TEXTURE_ON):RenderHelper.getSprite(TEXTURE_OFF);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(matrixStack,builder,sprite_xor_gate,sprite,combinedLight,alpha);

    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                rightNeighbor = cellPos.getNeighbor(Side.RIGHT),
                leftNeighbor = cellPos.getNeighbor(Side.LEFT);

        boolean output =
                (leftNeighbor!=null && leftNeighbor.getWeakRsOutput()>0) ||
                        (this.output && (rightNeighbor==null || rightNeighbor.getWeakRsOutput()==0));

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }

}
