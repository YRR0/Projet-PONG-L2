package com.next.pong.pages.home;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Music;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;
import javafx.scene.Node;

public class HomeActivity extends Activity<HomeLayout> {

    private final Music audio;

    public HomeActivity() {
        super(new HomeLayout());

        Node playButton = findElementById("playButton");
        playButton.setOnMouseClicked(event -> Window.goTo(new GameActivity()));

        audio = new Music(Resources.Music.IMPERIAL_MARCH);
        audio.play();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        audio.dispose();
    }
}
