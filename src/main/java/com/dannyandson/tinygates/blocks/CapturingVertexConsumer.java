package com.dannyandson.tinygates.blocks;

import com.mojang.blaze3d.vertex.VertexConsumer;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

/**
 * Captures block-local vertices during BER extract, then replays them under the
 * real render matrix via SubmitNodeCollector#submitCustomGeometry. Replaces the
 * removed MultiBufferSource path.
 */
public class CapturingVertexConsumer implements VertexConsumer {

    private final List<CachedVertex> vertices = new ArrayList<>();
    private CachedVertex current;

    public List<CachedVertex> getVertices() {
        return vertices;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        current = new CachedVertex(x, y, z);
        vertices.add(current);
        return this;
    }

    @Override
    public VertexConsumer setColor(int r, int g, int b, int a) {
        current.r = r; current.g = g; current.b = b; current.a = a;
        return this;
    }

    @Override
    public VertexConsumer setColor(int argb) {
        return setColor((argb >> 16) & 0xFF, (argb >> 8) & 0xFF, argb & 0xFF, (argb >> 24) & 0xFF);
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        current.u = u; current.v = v;
        return this;
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        current.overlayU = u; current.overlayV = v;
        return this;
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        current.lightU = u; current.lightV = v;
        return this;
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        current.nx = x; current.ny = y; current.nz = z;
        return this;
    }

    @Override
    public VertexConsumer setLineWidth(float width) {
        return this;
    }

    /** Re-emit captured vertices, applying the supplied matrix once. */
    public static void replay(VertexConsumer consumer, Matrix4f matrix, List<CachedVertex> vertices) {
        for (CachedVertex vertex : vertices) {
            consumer.addVertex(matrix, vertex.x, vertex.y, vertex.z)
                    .setColor(vertex.r, vertex.g, vertex.b, vertex.a)
                    .setUv(vertex.u, vertex.v)
                    .setUv1(vertex.overlayU, vertex.overlayV)
                    .setUv2(vertex.lightU, vertex.lightV)
                    .setNormal(vertex.nx, vertex.ny, vertex.nz);
        }
    }

    static final class CachedVertex {
        final float x, y, z;
        int r, g, b, a;
        float u, v;
        int overlayU, overlayV;
        int lightU, lightV;
        float nx, ny, nz;

        CachedVertex(float x, float y, float z) {
            this.x = x; this.y = y; this.z = z;
        }
    }
}