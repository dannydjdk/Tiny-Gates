package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinyredstone.blocks.Side;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ORGateBlock extends AbstractGateBlock {

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new ORGateBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side != Side.TOP && side != Side.BOTTOM;
    }
}
