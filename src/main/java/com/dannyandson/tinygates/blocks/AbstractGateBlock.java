package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.core.Direction.Axis;
import static net.minecraft.core.Direction.DOWN;

public abstract class AbstractGateBlock extends BaseEntityBlock {

    protected abstract AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state);

    public abstract boolean canConnectRedstone(Side side);

    protected AbstractGateBlock() {
        super(
                Properties.of(Material.STONE)
                        .sound(SoundType.STONE)
                        .strength(2.0f)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return newAbstractGateBlockEntity(pos, state);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
        builder.add(Registration.GATE_DIRECTION);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getClickedFace().getOpposite();
        Direction hfacing = null;
        if (facing == DOWN || facing == Direction.UP)
            hfacing = context.getHorizontalDirection();
        else
            for (Direction dir : context.getNearestLookingDirections()) {
                if (dir != facing && dir != facing.getOpposite())
                    hfacing = dir.getOpposite();
            }

        if (hfacing == null)
            hfacing = Direction.UP;

        return defaultBlockState()
                .setValue(BlockStateProperties.FACING, facing)
                .setValue(Registration.GATE_DIRECTION, hfacing);
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        if (direction != null)
            return canConnectRedstone(getSideFromDirection(direction.getOpposite(), state));
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos neighbor, boolean p_60514_) {
        if (level.getBlockEntity(pos) instanceof AbstractGateBlockEntity gateEntity) {
            if(gateEntity.onNeighborChange(neighbor))
                gateEntity.outputChange();
        } else
            super.onNeighborChange(state, level, pos, neighbor);
    }

    @Override
    public void onPlace(BlockState p_60566_, Level level, BlockPos pos, BlockState p_60569_, boolean p_60570_) {
        if (level.getBlockEntity(pos) instanceof AbstractGateBlockEntity gateEntity) {
            if(gateEntity.onNeighborChange(null))
                gateEntity.outputChange();
        } else
            super.onPlace(p_60566_, level, pos, p_60569_, p_60570_);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getDirectSignal(@NotNull BlockState blockState, BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull Direction direction) {
        if (blockGetter.getBlockEntity(pos) instanceof AbstractGateBlockEntity gateEntity) {
            return gateEntity.getDirectSignal(getSideFromDirection(direction.getOpposite(), blockState));
        }
        return super.getDirectSignal(blockState, blockGetter, pos, direction);
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getSignal(@NotNull BlockState blockState, BlockGetter blockGetter, @NotNull BlockPos pos, @NotNull Direction direction) {
        if (blockGetter.getBlockEntity(pos) instanceof AbstractGateBlockEntity gateEntity) {
            return gateEntity.getSignal(getSideFromDirection(direction.getOpposite(), blockState));
        }
        return super.getSignal(blockState, blockGetter, pos, direction);
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, LevelReader world, BlockPos pos, Direction directionFromNeighborToThis) {
        //returning false to override default behavior and allow block entity to determine output
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isSignalSource(@NotNull BlockState p_60571_) {
        return true;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        Direction facing = state.getValue(BlockStateProperties.FACING);
        switch (facing) {
            case NORTH -> {
                return Block.box(0, 0, 0, 16, 16, 2);
            }
            case EAST -> {
                return Block.box(14,0,0, 16,16,16);
            }
            case SOUTH -> {
                return Block.box(0, 0, 14, 16, 16, 16);
            }
            case WEST -> {
                return Block.box(0, 0, 0, 2, 16, 16);
            }
            case UP -> {
                return Block.box(0, 14, 0, 16, 16, 16);
            }
            default -> {
                return Block.box(0, 0, 0, 16, 2, 16);
            }
        }
    }

    public Side getSideFromDirection(Direction direction, BlockState state) {
        Direction facing = state.getValue(BlockStateProperties.FACING);
        Direction hfacing = state.getValue(Registration.GATE_DIRECTION);

        if (direction == hfacing) return Side.FRONT;
        if (direction == hfacing.getOpposite()) return Side.BACK;
        if (direction == facing) return Side.BOTTOM;
        if (direction == facing.getOpposite()) return Side.TOP;

        switch (facing) {
            case DOWN:
                if (direction == hfacing.getClockWise()) return Side.RIGHT;
                return Side.LEFT;
            case UP:
                if (direction == hfacing.getClockWise()) return Side.LEFT;
                return Side.RIGHT;
            case NORTH:
                if (direction == hfacing.getClockWise(Axis.Z))
                    return Side.LEFT;
                return Side.RIGHT;
            case SOUTH:
                if (direction == hfacing.getClockWise(Axis.Z))
                    return Side.RIGHT;
                return Side.LEFT;
            case EAST:
                if (direction == hfacing.getCounterClockWise(Axis.X))
                    return Side.LEFT;
                return Side.RIGHT;
            case WEST:
                if (direction == hfacing.getCounterClockWise(Axis.X))
                    return Side.RIGHT;
                return Side.LEFT;
            default:
                return Side.TOP;
        }
    }
}