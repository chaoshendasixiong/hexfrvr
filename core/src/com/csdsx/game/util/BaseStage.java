package com.csdsx.game.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Administrator on 2016-07-01.
 */
public class BaseStage extends Stage {

    public BaseStage() {
        super();
    }

    public BaseStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
    }
}
