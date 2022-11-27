package com.next.pong.pages.home;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Music;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;
import com.next.pong.pages.settings.SettingsActivity;
import javafx.scene.Node;

public class HomeActivity extends Activity<HomeLayout> {

    private final Music audio;

    public HomeActivity() {
        super(new HomeLayout());

        audio = new Music(Resources.Music.IMPERIAL_MARCH);
        audio.play();

        Node playButton = findElementById("newGameButton");
        playButton.setOnMouseClicked(event -> Window.goTo(new GameActivity()));

        Node settingsOfGame = findElementById("settingsOfGame");
        settingsOfGame.setOnMouseClicked(event -> Window.goTo(new SettingsActivity()));

        Node quitTheGame = findElementById("quitTheGame");
        quitTheGame.setOnMouseClicked(event -> System.exit(0));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        audio.dispose();
    }
}
