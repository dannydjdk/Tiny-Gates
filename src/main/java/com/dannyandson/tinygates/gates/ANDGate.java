package com.dannyandson.tinygates.gates;

import com.dannyandson.tinyredstone.api.IPanelCell;
import com.dannyandson.tinyredstone.blocks.*;
import com.dannyandson.tinyredstone.blocks.panelcells.RedstoneDust;
import com.dannyandson.tinyredstone.blocks.panelcells.SuperRepeater;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class ANDGate implements IPanelCell {
    private boolean output=false;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_repeater = RenderHelper.getSprite(SuperRepeater.TEXTURE_REPEATER_ON);
        TextureAtlasSprite sprite_torch_head = RenderHelper.getSprite(RedstoneDust.TEXTURE_REDSTONE_DUST_SEGMENT_ON);


        if (!this.output){
            sprite_torch_head = RenderHelper.getSprite(RedstoneDust.TEXTURE_REDSTONE_DUST_SEGMENT_OFF);
        }

        poseStack.translate(0,0,0.25);

        //draw base top
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        poseStack.translate(-1,-1,0);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,1,sprite_repeater,combinedLight,alpha);
        poseStack.popPose();


        //draw back side
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        poseStack.translate(0,-0.25,0);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite,combinedLight,alpha);

        //right side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite,combinedLight,alpha);

        //front side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite,combinedLight,alpha);

        //left side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite,combinedLight,alpha);

    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor rightNeighbor = cellPos.getNeighbor(Side.RIGHT),
                leftNeighbor = cellPos.getNeighbor(Side.LEFT),
                backNeighbor = cellPos.getNeighbor(Side.BACK);

        boolean output = (rightNeighbor==null || rightNeighbor.getWeakRsOutput()>0) &&
                (leftNeighbor==null || leftNeighbor.getWeakRsOutput()>0) &&
                (backNeighbor==null || backNeighbor.getWeakRsOutput()>0);

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }

    @Override
    public int getWeakRsOutput(Side side) {
        return (side==Side.FRONT && output)?15:0;
    }

    @Override
    public int getStrongRsOutput(Side side) {
        return (side==Side.FRONT && output)?15:0;
    }

    @Override
    public boolean needsSolidBase() {
        return true;
    }

    @Override
    public boolean canAttachToBaseOnSide(Side side) {
        return side==Side.BOTTOM;
    }

    @Override
    public Side getBaseSide() {
        return Side.BOTTOM;
    }

    @Override
    public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
        return IPanelCell.super.onBlockActivated(cellPos, segmentClicked, player);
    }

    @Override
    public boolean hasActivation() {
        return IPanelCell.super.hasActivation();
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putBoolean("output",output);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        output=compoundTag.getBoolean("output");
    }

    @Override
    public PanelCellVoxelShape getShape() {
        return PanelCellVoxelShape.QUARTERCELLSLAB;
    }
}
