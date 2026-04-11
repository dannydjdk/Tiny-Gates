package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.setup.ModRegistration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import static net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS;
import static net.minecraft.core.Direction.*;

public class GateBlockRenderer implements BlockEntityRenderer<AbstractGateBlockEntity, GateBlockRenderState> {

    public static Identifier TEXTURE_BLANK_PANEL = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/panel_blank");

    public GateBlockRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public GateBlockRenderState createRenderState() {
        return new GateBlockRenderState();
    }

    @Override
    public void extractRenderState(AbstractGateBlockEntity gateBlockEntity, GateBlockRenderState state, float partialTick,
                                   Vec3 cameraPos,
                                   ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(gateBlockEntity, state, partialTick, cameraPos, crumblingOverlay);
        state.lightCoords = LevelRenderer.getLightCoords(gateBlockEntity.getLevel(), gateBlockEntity.getBlockPos());
        state.texture = gateBlockEntity.getTexture();
        state.facing = gateBlockEntity.getBlockState().getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING);
        state.gateDirection = gateBlockEntity.getBlockState().getValue(ModRegistration.GATE_DIRECTION);
    }

    @Override
    public void submit(GateBlockRenderState state, PoseStack poseStack,
                       SubmitNodeCollector collector, CameraRenderState camera) {
        Direction facing = state.facing;
        Direction gateDirection = state.gateDirection;

        MultiBufferSource.BufferSource bufferSource =
                Minecraft.getInstance().renderBuffers().bufferSource();

        poseStack.pushPose();

        switch (facing)
        {
            case UP:
                poseStack.mulPose(Axis.XP.rotationDegrees(180));
                poseStack.translate(0,-1,-1);
                break;
            case NORTH:
                poseStack.mulPose(Axis.XP.rotationDegrees(90));
                poseStack.translate(0,0,-1);
                break;
            case EAST:
                poseStack.mulPose(Axis.ZP.rotationDegrees(90));
                poseStack.translate(0,-1,0);
                break;
            case SOUTH:
                poseStack.mulPose(Axis.XP.rotationDegrees(-90));
                poseStack.translate(0,-1,0);
                break;
            case WEST:
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90));
                poseStack.translate(-1,0,0);
                break;
        }

        if ((gateDirection==UP&&facing==NORTH) || (gateDirection==NORTH&&facing==DOWN)  || (gateDirection==NORTH&&facing==WEST) || (gateDirection==SOUTH&&facing==UP)||(facing==EAST&&gateDirection==NORTH)||(facing==SOUTH&&gateDirection==DOWN)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(180));
            poseStack.translate(-1, 0, -1);
        }else if (gateDirection==EAST||(facing==EAST&&gateDirection==UP)||(facing==WEST&&gateDirection==DOWN)){
            poseStack.mulPose(Axis.YP.rotationDegrees(90));
            poseStack.translate(-1,0,0);
        }else if (gateDirection==WEST||(facing==EAST&&gateDirection==DOWN||(facing==WEST&&gateDirection==UP))) {
            poseStack.mulPose(Axis.YP.rotationDegrees(270));
            poseStack.translate(0, 0, -1);
        }

        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasManager().get(new SpriteId(LOCATION_BLOCKS, TEXTURE_BLANK_PANEL));
        TextureAtlasSprite sprite_top = Minecraft.getInstance().getAtlasManager().get(new SpriteId(LOCATION_BLOCKS, state.texture));
        VertexConsumer builder = bufferSource.getBuffer(net.minecraft.client.renderer.Sheets.cutoutBlockSheet());
        int color = 0xFFFFFFFF;

        poseStack.pushPose();
        poseStack.mulPose(Axis.XP.rotationDegrees(270));
        poseStack.translate(0, -1, 0.125);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, 1, sprite_top.getU1(), sprite_top.getU0(), sprite_top.getV0(), sprite_top.getV1(), state.lightCoords, color, 1.0f);

        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0, -0.125, 0);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, state.lightCoords, 1.0f);

        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, state.lightCoords, 1.0f);

        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, state.lightCoords, 1.0f);

        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, state.lightCoords, 1.0f);

        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0, -1, 0);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, 1, sprite, state.lightCoords, 1.0f);

        poseStack.popPose();

        poseStack.popPose();
    }
}