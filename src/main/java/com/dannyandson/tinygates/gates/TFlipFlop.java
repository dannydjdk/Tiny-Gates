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

public class TFlipFlop extends AbstractGate {

    public static ResourceLocation TEXTURE_ON_ON = new ResourceLocation(TinyGates.MODID,"block/t_flip_flop_on_on");
    public static ResourceLocation TEXTURE_OFF_ON = new ResourceLocation(TinyGates.MODID,"block/t_flip_flop_off_on");
    public static ResourceLocation TEXTURE_OFF_OFF = new ResourceLocation(TinyGates.MODID,"block/t_flip_flop_off_off");
    public static ResourceLocation TEXTURE_ON_OFF = new ResourceLocation(TinyGates.MODID,"block/t_flip_flop_on_off");

    private boolean input=false;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(input?(output?TEXTURE_ON_ON:TEXTURE_ON_OFF):(output?TEXTURE_OFF_ON:TEXTURE_OFF_OFF));

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
        input=compoundTag.getBoolean("input");
    }

}
