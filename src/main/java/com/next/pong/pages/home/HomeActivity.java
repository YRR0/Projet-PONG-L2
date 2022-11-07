package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;
import com.next.pong.pages.pause.*;
import com.next.pong.framework.window.Window;
import javafx.stage.Stage;
import javafx.scene.Scene;
import com.next.pong.pages.game.GameActivity;

public class HomeActivity extends Activity<HomeLayout> {

    public HomeActivity() {
        super(new HomeLayout());
    Window.setHomeactivity(this);
        HomeLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e ->   Window.goTo(new GameActivity(1000,600,1.0)));
        layout.getExitButton().setOnMouseClicked(e -> Window.goTo(new PauseActivity()));
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
