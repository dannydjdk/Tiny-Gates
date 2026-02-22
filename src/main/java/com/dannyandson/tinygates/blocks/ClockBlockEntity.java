package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.gui.ClockBlockGUI;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static com.dannyandson.tinygates.RenderHelper.TEXTURES_CLOCK;

public class ClockBlockEntity extends AbstractGateBlockEntity {

    private int ticks = 20;
    private int tick = 0;
    private boolean input = false;

    public ClockBlockEntity(BlockPos pos, BlockState state) {
        super(Registration.CLOCK_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public ResourceLocation getTexture() {
        return (this.output > 0 ? TEXTURES_CLOCK[TEXTURES_CLOCK.length - 1] : TEXTURES_CLOCK[Math.min(Math.floorDiv(tick * (TEXTURES_CLOCK.length - 1), ticks), TEXTURES_CLOCK.length - 1)]);
    }

    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
        Direction backDirection = getDirectionFromSide(Side.BACK);
        int backSignal = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);
        this.input = backSignal>0;
        return false;
    }

    public boolean tick(){
        if (!this.input) {
            this.tick++;
            if (this.tick >= this.ticks) {
                this.output = 15;
                this.tick = 0;
                return true;
            } else if (this.output>0 && this.tick>1) {
                this.output = 0;
                return true;
            }else {
                sync();
            }
        }
        return false;
    }

    public void use()
    {
        if (getLevel().isClientSide)
            ClockBlockGUI.open(this);
    }

    public Integer getTicks() {
        return this.ticks;
    }
    public void setTicks(Integer ticks){
        if (ticks<this.tick)
            this.tick=0;

        if (ticks<2)this.ticks=2;
        else if(ticks>200)this.ticks=200;
        else this.ticks=ticks;
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.loadAdditional(nbt, registries);
        ticks=nbt.getInt("ticks");
        tick=nbt.getInt("tick");
        input=nbt.getBoolean("input");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
        super.saveAdditional(nbt, registries);
        nbt.putInt("ticks",ticks);
        nbt.putInt("tick",tick);
        nbt.putBoolean("input",input);
    }
}
