package com.dannyandson.tinygates.gates;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.gui.TinyClockGUI;
import com.dannyandson.tinygates.network.ModNetworkHandler;
import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.api.IPanelCell;
import com.dannyandson.tinyredstone.blocks.*;
import com.dannyandson.tinyredstone.network.PanelCellSync;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkDirection;

public class Clock extends AbstractGate {

    private int ticks = 20;
    private int tick = 0;
    private boolean input = false;

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, float alpha) {
        VertexConsumer builder = buffer.getBuffer((alpha==1.0)? RenderType.solid():RenderType.translucent());
        TextureAtlasSprite sprite = RenderHelper.getSprite(PanelTileRenderer.TEXTURE);
        TextureAtlasSprite sprite_gate = RenderHelper.getSprite(this.output? RenderHelper.TEXTURES_CLOCK[RenderHelper.TEXTURES_CLOCK.length-1]: RenderHelper.TEXTURES_CLOCK[Math.min(Math.floorDiv(tick*(RenderHelper.TEXTURES_CLOCK.length-1),ticks), RenderHelper.TEXTURES_CLOCK.length-1)]);

        com.dannyandson.tinygates.RenderHelper.drawQuarterSlab(poseStack,builder,sprite_gate,sprite,combinedLight,alpha);
    }

    @Override
    public boolean neighborChanged(PanelCellPos cellPos) {
        PanelCellNeighbor
                backNeighbor = cellPos.getNeighbor(Side.BACK);
        this.input = backNeighbor!=null && backNeighbor.getWeakRsOutput()>0;
        return false;
    }

    @Override
    public boolean tick(PanelCellPos cellPos) {
        if (!this.input) {
            this.tick++;
            if (this.tick >= this.ticks) {
                this.output = true;
                this.tick = 0;
                return true;
            } else if (this.output && this.tick>1) {
                this.output = false;
                return true;
            }else {
                sendToClient(cellPos);
            }
        }
        return false;
    }

    @Override
    public boolean hasActivation(){return true;}

    @Override
    public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
        PanelTile panelTile = cellPos.getPanelTile();
        if (panelTile.getLevel().isClientSide)
            TinyClockGUI.open(panelTile, cellPos.getIndex(), this);
        return false;
    }


    @Override
    public CompoundTag writeNBT() {
        CompoundTag compoundTag = super.writeNBT();
        compoundTag.putInt("ticks",ticks);
        compoundTag.putInt("tick",tick);
        compoundTag.putBoolean("input",input);
        return compoundTag;
    }

    @Override
    public void readNBT(CompoundTag compoundTag) {
        super.readNBT(compoundTag);
        ticks=compoundTag.getInt("ticks");
        tick=compoundTag.getInt("tick");
        input=compoundTag.getBoolean("input");
    }

    public Integer getTicks() {
        return this.ticks;
    }
    public void setTicks(Integer ticks){
        if (ticks<this.tick)
            this.tick=0;

        if (ticks<2)this.ticks=2;
        else if(ticks>200)this.ticks=200;
        else this.ticks=ticks;
    }

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Rate", this.ticks/2 + " ticks");
        if(this.input) {
            overlayBlockInfo.addInfo("Locked");
        }
    }

    public void sendToClient(PanelCellPos cellPos) {
        PanelTile panelTile = cellPos.getPanelTile();
        BlockPos pos = panelTile.getBlockPos();
        for (Player player : panelTile.getLevel().players()) {
            if (player instanceof ServerPlayer && player.distanceToSqr(pos.getX(),pos.getY(),pos.getZ()) < 64d) {
                ModNetworkHandler.getINSTANCE().sendTo(new PanelCellSync(pos,cellPos.getIndex(),writeNBT()), ((ServerPlayer) player).connection.connection, NetworkDirection.PLAY_TO_CLIENT);
            }
        }
    }


    public static void clockTickSync(BlockEntity blockEntity,int cellIndex, int ticks){
        if (blockEntity instanceof PanelTile panelTile)
        {
            PanelCellPos cellPos = PanelCellPos.fromIndex(panelTile,cellIndex);
            IPanelCell cell = cellPos.getIPanelCell();
            if (cell instanceof Clock clockCell)
            {
                clockCell.setTicks(ticks);
                panelTile.flagSync();
            }
        }
    }

}
