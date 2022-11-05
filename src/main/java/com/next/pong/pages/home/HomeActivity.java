package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;

public class HomeActivity extends Activity<HomeLayout> {

    public HomeActivity() {
        super(new HomeLayout());

        HomeLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e -> Window.goTo(new GameActivity(1000,600,1.0)));

    }

    @Override
    public void onUpdate(double deltaTime) {
        super.onUpdate(deltaTime);
    }

    @Override
    public boolean onStop() {
        return super.onStop();
    }

}
