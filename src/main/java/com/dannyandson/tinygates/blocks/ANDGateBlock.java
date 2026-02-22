package com.dannyandson.tinygates.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ANDGateBlock extends AbstractGateBlock{

    public static final MapCodec<ANDGateBlock> CODEC = simpleCodec(p -> new ANDGateBlock());

    public ANDGateBlock() {
        super();
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
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
