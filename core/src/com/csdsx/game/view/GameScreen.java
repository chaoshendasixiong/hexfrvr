package com.csdsx.game.view;

import com.csdsx.game.control.GameControl;
import com.csdsx.game.control.IControl;
import com.csdsx.game.util.BaseScreen;

/**
 * Created by Administrator on 2016-07-09.
 */
public class GameScreen extends BaseScreen{
    @Override
    public void onInit() {
        this.control = new GameControl();
    }

    @Override
    public void onLogic() {
        this.control.control();
    }
}
