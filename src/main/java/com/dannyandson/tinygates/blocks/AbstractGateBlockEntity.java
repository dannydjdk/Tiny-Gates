package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nullable;

public abstract class AbstractGateBlockEntity extends BlockEntity {
    protected int output;

    public abstract ResourceLocation getTexture();

    public AbstractGateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public int getSignal(Side side){
        if (side==Side.FRONT)
            return output;
        return 0;
    }

    public int getDirectSignal(Side side){
        return getSignal(side);
    }

    /**
     * Respond to neighbor change
     *
     * @param neighbor The block position of the neighbor that changed
     * @return true if the output changed
     */
    public abstract boolean onNeighborChange(@Nullable BlockPos neighbor);

    /**
     * To be called when the redstone output changes.
     * Marks dirty (setChanged()), sends client block update and updated neighbors and front neighbor's neighbors.
     */
    protected void outputChange() {
        this.sync();

        this.level.updateNeighborsAt(worldPosition, getBlockState().getBlock());
        Direction dir = getDirectionFromSide(Side.FRONT);
        BlockPos neighborPos = worldPosition.relative(dir);
        BlockState neighborBlockState = level.getBlockState(neighborPos);
        if (!neighborBlockState.isAir())
            this.level.updateNeighborsAtExceptFromFacing(neighborPos, neighborBlockState.getBlock(), dir.getOpposite());
    }


    public Direction getDirectionFromSide(Side side) {
        Direction facing = this.getBlockState().getValue(BlockStateProperties.FACING);
        Direction hfacing = this.getBlockState().getValue(Registration.GATE_DIRECTION);

        if (side == Side.FRONT) return hfacing;
        if (side == Side.BACK) return hfacing.getOpposite();
        if (side == Side.BOTTOM) return facing;
        if (side == Side.TOP) return facing.getOpposite();

        switch (facing) {
            case DOWN:
                if (side == Side.RIGHT) return hfacing.getClockWise();
                return hfacing.getCounterClockWise();
            case UP:
                if (side == Side.LEFT) return hfacing.getClockWise();
                return hfacing.getCounterClockWise();
            case NORTH:
                if (side == Side.LEFT) return hfacing.getCounterClockWise(Direction.Axis.Z);
                return hfacing.getClockWise(Direction.Axis.Z);
            case SOUTH:
                if (side == Side.LEFT) return hfacing.getClockWise(Direction.Axis.Z);
                return hfacing.getCounterClockWise(Direction.Axis.Z);
            case EAST:
                if (side == Side.LEFT) return hfacing.getClockWise(Direction.Axis.X);
                return hfacing.getCounterClockWise(Direction.Axis.X);
            case WEST:
                if (side == Side.LEFT) return hfacing.getCounterClockWise(Direction.Axis.X);
                return hfacing.getClockWise(Direction.Axis.X);
            default:
                return Direction.UP;
        }
    }

    /**
     * Loading and saving block entity data from disk and syncing to client
     */

    protected void sync() {
        if (!level.isClientSide)
            this.level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
        this.setChanged();
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = new CompoundTag();
        this.saveAdditional(nbt);
        return nbt;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.output = nbt.getInt("output");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("output", this.output);
    }
}
