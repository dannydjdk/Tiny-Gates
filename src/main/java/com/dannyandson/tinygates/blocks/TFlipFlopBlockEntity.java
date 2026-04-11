package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.ModRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

public class TFlipFlopBlockEntity extends AbstractGateBlockEntity {

    private boolean input=false;

    public TFlipFlopBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistration.T_FLIP_FLOP_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Identifier getTexture() {
        return input?(output>0? RenderHelper.TEXTURE_T_ON_ON : RenderHelper.TEXTURE_T_ON_OFF):(output>0? RenderHelper.TEXTURE_T_OFF_ON : RenderHelper.TEXTURE_T_OFF_OFF);
    }

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
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("input", this.input);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.input = input.getBooleanOr("input", false);
    }
}
