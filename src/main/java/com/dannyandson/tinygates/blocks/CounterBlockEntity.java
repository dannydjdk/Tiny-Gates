package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.setup.ModRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import static com.dannyandson.tinygates.RenderHelper.TEXTURES_COUNTER;

public class CounterBlockEntity extends AbstractGateBlockEntity {

    private boolean input = false;

    public CounterBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistration.COUNTER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Identifier getTexture() {
        return TEXTURES_COUNTER[output];
    }

    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction backDirection = getDirectionFromSide(Side.BACK);
        Direction rightDirection = getDirectionFromSide(Side.RIGHT);
        Direction leftDirection = getDirectionFromSide(Side.LEFT);
        int backSignal = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);
        int rightSignal = getLevel().getSignal(getBlockPos().relative(rightDirection), rightDirection);
        int leftSignal = getLevel().getSignal(getBlockPos().relative(leftDirection), leftDirection);

        int output= this.output;
        boolean previousinput = this.input;
        this.input = backSignal>0;

        if (leftSignal>0) {
            return false;
        }
        else if (rightSignal>0){
            output = 0;
        }
        else if (!previousinput && this.input && output<15){
            output++;
        }

        if (this.output != output){
            this.output = output;
            return true;
        }
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
