package com.dannyandson.tinygates.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;

public class RSLatchBlock extends AbstractGateBlock {

    public static final MapCodec<RSLatchBlock> CODEC = simpleCodec(RSLatchBlock::new);

    public RSLatchBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new RSLatchBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side==Side.FRONT || side==Side.LEFT || side==Side.RIGHT;
    }
}
