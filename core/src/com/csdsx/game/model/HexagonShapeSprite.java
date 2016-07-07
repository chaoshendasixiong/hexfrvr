package com.csdsx.game.model;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.csdsx.game.util.ShapeSprite;

/**
 * Created by Administrator on 2016-07-02.
 */
public class HexagonShapeSprite extends ShapeSprite {
    int side_len = 100;
    ShaderProgram shaderProgram;
    public HexagonShapeSprite() {
        this.mesh = new Mesh(true, 3, 3, new VertexAttribute(VertexAttributes.Usage.Position, 3,
                "a_position"));
        mesh.setVertices(new float[] { 100f, 100f, 0, 50f, 50f, 0, 150f, 50f, 0 });
        mesh.setIndices(new short[] { 0, 1, 2 });
//        shaderProgram = new ShaderProgram();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

//        mesh.render();
    }
}
