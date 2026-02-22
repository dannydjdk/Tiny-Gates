package com.dannyandson.tinygates.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CounterBlock extends AbstractGateBlock {

    public static final MapCodec<CounterBlock> CODEC = simpleCodec(p -> new CounterBlock());

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new CounterBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side!=Side.TOP && side!=Side.BOTTOM;
    }
}
