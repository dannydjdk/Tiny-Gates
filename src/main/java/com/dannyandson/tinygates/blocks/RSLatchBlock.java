package com.dannyandson.tinygates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class RSLatchBlock extends AbstractGateBlock {

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new RSLatchBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side==Side.FRONT || side==Side.LEFT || side==Side.RIGHT;
    }
}
