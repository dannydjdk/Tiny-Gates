package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static com.dannyandson.tinygates.RenderHelper.TEXTURES_COUNTER;

public class CounterBlockEntity extends AbstractGateBlockEntity {

    private boolean input = false;

    public CounterBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.COUNTER_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        return TEXTURES_COUNTER[output];
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
        Direction rightDirection = getDirectionFromSide(Side.RIGHT);
        Direction leftDirection = getDirectionFromSide(Side.LEFT);
        int backSignal = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);
        int rightSignal = getLevel().getSignal(getBlockPos().relative(rightDirection), rightDirection);
        int leftSignal = getLevel().getSignal(getBlockPos().relative(leftDirection), leftDirection);


        int output= this.output;
        boolean previousinput = this.input;
        this.input = backSignal>0;

        if (leftSignal>0) {
            //left input locks counter
            return false;
        }
        else if (rightSignal>0){
            //right input resets counter
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
    public void load(CompoundTag nbt) {
        super.load(nbt);
        input=nbt.getBoolean("input");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putBoolean("input",input);
    }


}
