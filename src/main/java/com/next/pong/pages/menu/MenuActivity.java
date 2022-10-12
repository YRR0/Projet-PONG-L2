package com.next.pong.pages.menu;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;

public class MenuActivity extends Activity<MenuLayout> {

    public MenuActivity() {
        super(new MenuLayout());

        MenuLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e -> Window.goTo(new GameActivity()));
    }

    @Override
    public void onUpdate(float deltaTime) {
        super.onUpdate(deltaTime);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
