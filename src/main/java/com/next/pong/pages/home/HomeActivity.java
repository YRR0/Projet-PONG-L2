package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;

import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;
import com.next.pong.pause.*;

public class HomeActivity extends Activity<HomeLayout> {

    public HomeActivity() {
        super(new HomeLayout());

        HomeLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e -> Window.goTo(new PauseActivity()));
        
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
