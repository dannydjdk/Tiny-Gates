package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ORGateBlockEntity extends AbstractGateBlockEntity{

    public ORGateBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.OR_GATE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        if (this.output>0)
            return RenderHelper.TEXTURE_OR_GATE_ON;
        return RenderHelper.TEXTURE_OR_GATE_OFF;
    }

    /**
     * Respond to neighbor change
     *
     * @param neighbor The block position of the neighbor that changed
     * @return true if the output changed
     */
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
