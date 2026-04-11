package com.dannyandson.tinygates.blocks;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;

public class GateBlockRenderState extends BlockEntityRenderState {
    public Identifier texture;
    public Direction facing;
    public Direction gateDirection;
}
