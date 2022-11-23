package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Audio;
import com.next.pong.framework.audio.AudioExtension;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;
import javafx.scene.Node;

public class HomeActivity extends Activity<HomeLayout> {

    private final Audio audio;

    public HomeActivity() {
        super(new HomeLayout());

        Node playButton = findElementById("playButton");
        playButton.setOnMouseClicked(event -> Window.goTo(new GameActivity()));

        audio = new Audio("imperial_march", AudioExtension.MP3);
        audio.play();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        audio.dispose();
    }
}
