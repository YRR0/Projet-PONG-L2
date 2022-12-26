package com.next.pong.pages.home;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Sound;
import com.next.pong.framework.layout.Layout;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;
import com.next.pong.pages.settings.SettingsActivity;
import javafx.scene.Node;
import javafx.scene.control.Button;

import static com.next.pong.utils.StyleUtils.center;

public class HomeActivity extends Activity<HomeLayout> {

    private final Sound music;
    private final Sound soundEffect;

    public HomeActivity() {
        super(new HomeLayout());
        repositionElements();

        music = new Sound();
        soundEffect = new Sound();
        music.playMusic(Resources.Music.GAME);

        Node playButton = findElementById("newGameButton");
        playButton.setOnMouseClicked(event -> {
            soundEffect.playSoundEffect(Resources.Music.COIN);
            Window.goTo(new GameActivity(false));
        });

        Node playButtonAI = findElementById("newGameButtonAI");
        playButtonAI.setOnMouseClicked(event -> {
            soundEffect.playSoundEffect(Resources.Music.COIN);
            Window.goTo(new GameActivity(true));
        });

        Node settingsOfGame = findElementById("settingsOfGame");
        settingsOfGame.setOnMouseClicked(event -> Window.goTo(new SettingsActivity()));

        Node quitTheGame = findElementById("quitTheGame");
        quitTheGame.setOnMouseClicked(event -> System.exit(0));

    }

    private void repositionElements() {
        Node title = findElementById("titleGame");
        Button playButton = (Button) findElementById("newGameButton");
        Button playButtonAI = (Button) findElementById("newGameButtonAI");
        Button settingsOfGame = (Button) findElementById("settingsOfGame");
        Button quitTheGame = (Button) findElementById("quitTheGame");
        int width = Layout.DEFAULT_WIDTH, height = Layout.DEFAULT_HEIGHT;

        center(title);
        title.setLayoutY(0.2 * height);

        playButton.setPrefSize(0.3 * width, 0.1 * height);
        playButton.setLayoutX(0.5 * width - 0.15 * width);
        playButton.setLayoutY(0.3 * height);

        playButtonAI.setPrefSize(0.3 * width, 0.1 * height);
        playButtonAI.setLayoutX(0.5 * width - 0.15 * width);
        playButtonAI.setLayoutY(0.45 * height);

        settingsOfGame.setPrefSize(0.3 * width, 0.1 * height);
        settingsOfGame.setLayoutX(0.5 * width - 0.15 * width);
        settingsOfGame.setLayoutY(0.6 * height);

        quitTheGame.setPrefSize(0.3 * width, 0.1 * height);
        quitTheGame.setLayoutX(0.5 * width - 0.15 * width);
        quitTheGame.setLayoutY(0.75 * height);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();

        if (music != null) {
            music.stopMusic();
        }
    }
}
