package com.dannyandson.tinygates;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import org.joml.Matrix4f;

public class RenderHelper {

    public static Identifier TEXTURE_AND_GATE_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/and_gate_on");
    public static Identifier TEXTURE_AND_GATE_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/and_gate_off");
    public static Identifier[] TEXTURES_CLOCK = {
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_1"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_2"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_3"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_4"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_5"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_6"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_7"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_8"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/clock_9")
    };
    public static Identifier[] TEXTURES_COUNTER = {
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_0"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_1"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_2"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_3"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_4"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_5"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_6"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_7"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_8"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_9"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_10"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_11"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_12"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_13"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_14"),
            Identifier.fromNamespaceAndPath(TinyGates.MODID, "block/counter_15")
    };
    public static Identifier TEXTURE_RISING_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/edge_detector_rising_on");
    public static Identifier TEXTURE_RISING_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/edge_detector_rising_off");
    public static Identifier TEXTURE_FALLING_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/edge_detector_falling_on");
    public static Identifier TEXTURE_FALLING_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/edge_detector_falling_off");

    public static Identifier TEXTURE_NOT_GATE_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/not_gate_on");
    public static Identifier TEXTURE_NOT_GATE_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/not_gate_off");
    public static Identifier TEXTURE_OR_GATE_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/or_gate_on");
    public static Identifier TEXTURE_OR_GATE_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/or_gate_off");
    public static Identifier TEXTURE_RS_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/rs_latch_on");
    public static Identifier TEXTURE_RS_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/rs_latch_off");
    public static Identifier TEXTURE_T_ON_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/t_flip_flop_on_on");
    public static Identifier TEXTURE_T_OFF_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/t_flip_flop_off_on");
    public static Identifier TEXTURE_T_OFF_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/t_flip_flop_off_off");
    public static Identifier TEXTURE_T_ON_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/t_flip_flop_on_off");
    public static Identifier TEXTURE_XOR_GATE_ON = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/xor_gate_on");
    public static Identifier TEXTURE_XOR_GATE_OFF = Identifier.fromNamespaceAndPath(TinyGates.MODID,"block/xor_gate_off");

    public static void drawQuarterSlab(PoseStack poseStack, VertexConsumer builder, TextureAtlasSprite sprite_top, TextureAtlasSprite sprite_side, int combinedLight, float alpha){
        poseStack.translate(0,0,0.25);

        //draw base top
        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(180));
        poseStack.translate(-1,-1,0);
        drawRectangle(builder,poseStack,0,1,0,1,sprite_top.getU1(), sprite_top.getU0(), sprite_top.getV0(), sprite_top.getV1(),combinedLight,0xFFFFFFFF,alpha);
        poseStack.popPose();


        //draw back side
        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.translate(0,-0.25,0);
        drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //right side
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //front side
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //left side
        poseStack.mulPose(Axis.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);
    }


    public static void drawRectangle(VertexConsumer builder, PoseStack poseStack,float x1, float x2, float y1, float y2, TextureAtlasSprite sprite, int combinedLight, float alpha) {
        drawRectangle(builder, poseStack, x1, x2, y1, y2, sprite.getU1(), sprite.getU0(), sprite.getV0(), sprite.getV1(), combinedLight, 0xFFFFFFFF, alpha);
    }

    public static void drawRectangle(VertexConsumer builder, PoseStack matrixStack, float x1, float x2, float y1, float y2, float u0, float u1, float v0, float v1, int combinedLight, int color, float alpha) {
        Matrix4f matrix4f = matrixStack.last().pose();
        add(builder, matrix4f, x1, y1, 0, u0, v0, combinedLight, color, alpha);
        add(builder, matrix4f, x2, y1, 0, u1, v0, combinedLight, color, alpha);
        add(builder, matrix4f, x2, y2, 0, u1, v1, combinedLight, color, alpha);
        add(builder, matrix4f, x1, y2, 0, u0, v1, combinedLight, color, alpha);
    }

    public static void add(VertexConsumer renderer, Matrix4f matrix4f, float x, float y, float z, int color, float alpha) {
        renderer.addVertex(matrix4f, x, y, z)
                .setColor(color >> 16 & 255, color >> 8 & 255, color & 255, (int) (alpha * 255f));
    }

    public static void add(VertexConsumer renderer, Matrix4f matrix4f, float x, float y, float z, float u, float v, int combinedLightIn, int color, float alpha) {
        renderer.addVertex(matrix4f, x, y, z)
                .setColor(color >> 16 & 255, color >> 8 & 255, color & 255, (int) (alpha * 255f))
                .setUv(u, v)
                .setUv1(0, 10)
                .setUv2(combinedLightIn & 0xFFFF, (combinedLightIn >> 16) & 0xFFFF)
                .setNormal(0, 1, 0);
    }

    public static TextureAtlasSprite getSprite(Identifier identifier) {
        return Minecraft.getInstance().getAtlasManager().get(new SpriteId(TextureAtlas.LOCATION_BLOCKS, identifier));
    }

}
