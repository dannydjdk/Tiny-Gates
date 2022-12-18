package com.dannyandson.tinygates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ANDGateBlock extends AbstractGateBlock{

    public ANDGateBlock() {
        super();
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new ANDGateBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side == Side.LEFT || side == Side.RIGHT || side == Side.FRONT;
    }
}
