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

public class EdgeDetectorBlockEntity extends AbstractGateBlockEntity {

    private boolean rising=true;
    private boolean input=false;
    private int ticks=0;

    public EdgeDetectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistration.EDGE_DETECTOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Identifier getTexture() {
        return output>0?(rising? RenderHelper.TEXTURE_RISING_ON: RenderHelper.TEXTURE_FALLING_ON):(rising? RenderHelper.TEXTURE_RISING_OFF: RenderHelper.TEXTURE_FALLING_OFF);
    }

    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction backDirection = getDirectionFromSide(Side.BACK);
        int backSignal = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);

        boolean previousinput = this.input;
        this.input=backSignal>0;

        if ((rising && !previousinput && this.input) || (!rising && previousinput && !this.input)){
            this.output=15;
            this.ticks=2;
            return true;
        }

        return false;
    }

    public boolean tick(){
        if (this.output>0){
            if (ticks<=0) {
                this.output = 0;
                return true;
            }
            this.ticks--;
        }
        return false;
    }

    public void use()
    {
        this.rising=!this.rising;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putBoolean("input", this.input);
        output.putBoolean("rising", this.rising);
        output.putInt("ticks", this.ticks);
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.input = input.getBooleanOr("input", false);
        this.rising = input.getBooleanOr("rising", true);
        this.ticks = input.getIntOr("ticks", 0);
    }
}
