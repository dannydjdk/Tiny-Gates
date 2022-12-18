package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.gui.ClockBlockGUI;
import com.dannyandson.tinygates.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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

    /**
     * Respond to neighbor change
     *
     * @param neighbor The block position of the neighbor that changed
     * @return true if the output changed
     */
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
            } else if (this.output>0) {
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
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ticks=nbt.getInt("ticks");
        tick=nbt.getInt("tick");
        input=nbt.getBoolean("input");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("ticks",ticks);
        nbt.putInt("tick",tick);
        nbt.putBoolean("input",input);
    }


}
