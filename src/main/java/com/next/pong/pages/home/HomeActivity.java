package com.next.pong.pages.home;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.HistoryOfGame;
import com.next.pong.pages.game.GameActivity;
import com.next.pong.pages.settings.SettingsActivity;
import javafx.scene.Node;


public class HomeActivity extends Activity<HomeLayout> {

    public HomeActivity() {
        super(new HomeLayout());
        Node newGameButton = findElementById("newGameButton");
        newGameButton.setOnMouseClicked(event -> {
            Window.goTo(new GameActivity());});

        Node settingsOfGame = findElementById("settingsOfGame");
        settingsOfGame.setOnMouseClicked(event -> {
            Window.goTo(new SettingsActivity());
        });

        Node historyOfGame = findElementById("historyOfGame");
        historyOfGame.setOnMouseClicked(event -> {
            Window.goTo(new HistoryOfGame());
        });
        Node quitTheGame = findElementById("quitTheGame");
        quitTheGame.setOnMouseClicked(event -> {System.exit(0);});


    }

}
