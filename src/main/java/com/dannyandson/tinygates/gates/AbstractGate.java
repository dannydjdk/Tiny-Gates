package com.dannyandson.tinygates.gates;

import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.api.IPanelCell;
import com.dannyandson.tinyredstone.api.IPanelCellInfoProvider;
import com.dannyandson.tinyredstone.blocks.PanelCellVoxelShape;
import com.dannyandson.tinyredstone.blocks.PanelTile;
import com.dannyandson.tinyredstone.blocks.PosInPanelCell;
import com.dannyandson.tinyredstone.blocks.Side;
import net.minecraft.nbt.CompoundTag;

public abstract class AbstractGate implements IPanelCell, IPanelCellInfoProvider {

    protected boolean output=false;

    @Override
    public int getWeakRsOutput(Side side) {
        return (side==Side.FRONT && output)?15:0;
    }

    @Override
    public int getStrongRsOutput(Side side) {
        return this.getWeakRsOutput(side);
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

    @Override
    public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
        overlayBlockInfo.addText("Output", this.output ? "On" : "Off");
        overlayBlockInfo.setPowerOutput(0);
    }
}
