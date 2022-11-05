package com.next.pong.pages.home;

import com.next.pong.Sound;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;

public class HomeActivity extends Activity<HomeLayout> {

    private Sound se = new Sound();

    public HomeActivity() {
        super(new HomeLayout());

        HomeLayout layout = getLayout();

        layout.getMainButton().setOnMouseClicked(e -> {
            se.playSoundEffect(4);
            Window.goTo(new GameActivity(this.getLayout().getWidth(),this.getLayout().getHeight(),1.0));
        });

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
