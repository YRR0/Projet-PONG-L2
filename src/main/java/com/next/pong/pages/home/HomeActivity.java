package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;

public class HomeActivity extends Activity<HomeLayout> {

    public HomeActivity() {
        super(new HomeLayout());

        HomeLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e -> Window.goTo(new GameActivity()));

    }

    @Override
    public void onUpdate(float deltaTime) {
        super.onUpdate(deltaTime);
    }

    @Override
    public boolean onStop() {
        return super.onStop();
    }

}
