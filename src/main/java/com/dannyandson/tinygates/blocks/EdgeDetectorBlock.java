package com.dannyandson.tinygates.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class EdgeDetectorBlock extends AbstractGateBlock {

    public static final MapCodec<EdgeDetectorBlock> CODEC = simpleCodec(EdgeDetectorBlock::new);

    public EdgeDetectorBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
        return new EdgeDetectorBlockEntity(pos, state);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
        return side==Side.FRONT || side==Side.BACK;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof EdgeDetectorBlockEntity edgeDetectorBlockEntity)
                if(edgeDetectorBlockEntity.tick())
                    edgeDetectorBlockEntity.outputChange();
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(pos) instanceof EdgeDetectorBlockEntity edgeDetectorBlockEntity) {
            edgeDetectorBlockEntity.use();
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, blockHitResult);
    }
}
