package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.ModRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class ORGateBlockEntity extends AbstractGateBlockEntity {

    public ORGateBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistration.OR_GATE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Identifier getTexture() {
        if (this.output>0)
            return RenderHelper.TEXTURE_OR_GATE_ON;
        return RenderHelper.TEXTURE_OR_GATE_OFF;
    }

    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction leftDirection = getDirectionFromSide(Side.LEFT);
        Direction rightDirection = getDirectionFromSide(Side.RIGHT);
        int leftSignal = getLevel().getSignal(getBlockPos().relative(leftDirection),leftDirection);
        int rightSignal =getLevel().getSignal(getBlockPos().relative(rightDirection),rightDirection);

        int output = (leftSignal>0 || rightSignal>0)?15:0;

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }
}
