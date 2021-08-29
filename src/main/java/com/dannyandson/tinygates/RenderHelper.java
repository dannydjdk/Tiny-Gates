package com.dannyandson.tinygates;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class RenderHelper {

    public static void drawQuarterSlab(PoseStack poseStack, VertexConsumer builder, TextureAtlasSprite sprite_top, TextureAtlasSprite sprite_side, int combinedLight, float alpha){
        poseStack.translate(0,0,0.25);

        //draw base top
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        poseStack.translate(-1,-1,0);
        com.dannyandson.tinyredstone.blocks.RenderHelper.drawRectangle(builder,poseStack,0,1,0,1,sprite_top,combinedLight,alpha);
        poseStack.popPose();


        //draw back side
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
        poseStack.translate(0,-0.25,0);
        com.dannyandson.tinyredstone.blocks.RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //right side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        com.dannyandson.tinyredstone.blocks.RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //front side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        com.dannyandson.tinyredstone.blocks.RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);

        //left side
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
        poseStack.translate(0,0,1);
        com.dannyandson.tinyredstone.blocks.RenderHelper.drawRectangle(builder,poseStack,0,1,0,0.25f,sprite_side,combinedLight,alpha);
    }

}
