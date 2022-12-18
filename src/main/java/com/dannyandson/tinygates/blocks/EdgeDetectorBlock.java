package com.dannyandson.tinygates.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class EdgeDetectorBlock extends AbstractGateBlock {

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

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        if (level.getBlockEntity(pos) instanceof EdgeDetectorBlockEntity edgeDetectorBlockEntity) {
            edgeDetectorBlockEntity.use();
            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, blockHitResult);
    }
}
