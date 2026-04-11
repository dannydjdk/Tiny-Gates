package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.setup.ModRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class NOTGateBlockEntity extends AbstractGateBlockEntity {

    public NOTGateBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistration.NOT_GATE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Identifier getTexture() {
        return output>0 ? RenderHelper.TEXTURE_NOT_GATE_ON : RenderHelper.TEXTURE_NOT_GATE_OFF;
    }

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
