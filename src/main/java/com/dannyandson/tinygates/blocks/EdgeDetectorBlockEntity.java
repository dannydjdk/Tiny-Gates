package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EdgeDetectorBlockEntity extends AbstractGateBlockEntity {

    private boolean rising=true;
    private boolean input=false;
    private int ticks=0;

    public EdgeDetectorBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.EDGE_DETECTOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
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
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        input=nbt.getBoolean("input");
        rising=nbt.getBoolean("rising");
        ticks=nbt.getInt("ticks");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putBoolean("input",input);
        nbt.putBoolean("rising",rising);
        nbt.putInt("ticks",ticks);
    }
}
