package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NOTGateBlockEntity extends AbstractGateBlockEntity{

    public NOTGateBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.NOT_GATE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        return output>0 ? RenderHelper.TEXTURE_NOT_GATE_ON : RenderHelper.TEXTURE_NOT_GATE_OFF;
    }

    /**
     * Respond to neighbor change
     *
     * @param neighbor The block position of the neighbor that changed
     * @return true if the output changed
     */
    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction backDirection = getDirectionFromSide(Side.BACK);
        int backSignal = getLevel().getSignal(getBlockPos().relative(backDirection),backDirection);

        int output = (backSignal==0)?15:0;

        if (output!=this.output){
            this.output=output;
            return true;
        }

        return false;
    }
}
