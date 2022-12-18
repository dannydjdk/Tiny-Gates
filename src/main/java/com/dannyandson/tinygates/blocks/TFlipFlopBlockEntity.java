package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class TFlipFlopBlockEntity extends AbstractGateBlockEntity{

    private boolean input=false;

    public TFlipFlopBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.T_FLIP_FLOP_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        return input?(output>0? RenderHelper.TEXTURE_T_ON_ON : RenderHelper.TEXTURE_T_ON_OFF):(output>0? RenderHelper.TEXTURE_T_OFF_ON : RenderHelper.TEXTURE_T_OFF_OFF);
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

        if (!input && (backSignal>0)){
            this.input=true;
            this.output=(this.output>0)?0:15;
            return true;
        }
        else if (backSignal==0)
            this.input=false;

        sync();
        return false;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.input = nbt.getBoolean("input");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("input", this.input);
    }

}
