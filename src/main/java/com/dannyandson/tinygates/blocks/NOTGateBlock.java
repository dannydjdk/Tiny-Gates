package com.dannyandson.tinygates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class NOTGateBlock extends AbstractGateBlock {

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new NOTGateBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side == Side.BACK || side == Side.FRONT;
    }
}
