package com.dannyandson.tinygates.blocks;

import com.dannyandson.tinygates.RenderHelper;
import com.dannyandson.tinygates.TinyGates;
import com.dannyandson.tinygates.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.core.Direction.*;

public class GateBlockRenderer implements BlockEntityRenderer<AbstractGateBlockEntity> {

    public static ResourceLocation TEXTURE_BLANK_PANEL = new ResourceLocation(TinyGates.MODID,"block/panel_blank");

    public GateBlockRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(AbstractGateBlockEntity gateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int p_112312_) {
        Direction facing = gateBlockEntity.getBlockState().getValue(BlockStateProperties.FACING);
        Direction gateDirection = gateBlockEntity.getBlockState().getValue(Registration.GATE_DIRECTION);

        poseStack.pushPose();

        switch (facing)
        {
            case UP:
                poseStack.mulPose(Vector3f.XP.rotationDegrees(180));
                poseStack.translate(0,-1,-1);
                break;
            case NORTH:
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
                poseStack.translate(0,0,-1);
                break;
            case EAST:
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(90));
                poseStack.translate(0,-1,0);
                break;
            case SOUTH:
                poseStack.mulPose(Vector3f.XP.rotationDegrees(-90));
                poseStack.translate(0,-1,0);
                break;
            case WEST:
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(-90));
                poseStack.translate(-1,0,0);
                break;
        }

        if ((gateDirection==UP&&facing==NORTH) || (gateDirection==NORTH&&facing==DOWN)  || (gateDirection==NORTH&&facing==WEST) || (gateDirection==SOUTH&&facing==UP)||(facing==EAST&&gateDirection==NORTH)||(facing==SOUTH&&gateDirection==DOWN)) {
            poseStack.mulPose(Vector3f.YP.rotationDegrees(180));
            poseStack.translate(-1, 0, -1);
        }else if (gateDirection==EAST||(facing==EAST&&gateDirection==UP)||(facing==WEST&&gateDirection==DOWN)){
            poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
            poseStack.translate(-1,0,0);
        }else if (gateDirection==WEST||(facing==EAST&&gateDirection==DOWN||(facing==WEST&&gateDirection==UP))) {
            poseStack.mulPose(Vector3f.YP.rotationDegrees(270));
            poseStack.translate(0, 0, -1);
        }

        TextureAtlasSprite sprite = RenderHelper.getSprite(TEXTURE_BLANK_PANEL);
        TextureAtlasSprite sprite_top = RenderHelper.getSprite(gateBlockEntity.getTexture());
        VertexConsumer builder = buffer.getBuffer(RenderType.solid());
        int color = 0xFFFFFFFF;

        poseStack.pushPose();
        poseStack.mulPose(Vector3f.XP.rotationDegrees(270));
        poseStack.translate(0, -1, 0.125);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, 1, sprite_top.getU1(), sprite_top.getU0(), sprite_top.getV0(), sprite_top.getV1(), combinedLight, color, 1.0f);

        poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        poseStack.translate(0, -0.125, 0);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, combinedLight, 1.0f);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, combinedLight, 1.0f);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, combinedLight, 1.0f);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0, 0, 1);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, .125f, sprite, combinedLight, 1.0f);

        poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        poseStack.translate(0, -1, 0);
        RenderHelper.drawRectangle(builder, poseStack, 0, 1, 0, 1, sprite, combinedLight, 1.0f);

        poseStack.popPose();


        poseStack.popPose();

    }
}
