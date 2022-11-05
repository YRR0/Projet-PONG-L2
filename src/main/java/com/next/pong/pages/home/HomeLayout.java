package com.next.pong.pages.home;

import com.next.pong.Sound;
import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class HomeLayout extends Layout {

    private final Button mainButton;
    private final Button exit;

    private Sound s = new Sound();

    public HomeLayout() {
        setBackground(Color.FIREBRICK);

        mainButton = new Button("Play");
        exit = new Button("Quitter");
        exit.setTranslateY(30);
        addElements(mainButton,exit);
        s.playMusic(2);
    }

    public Button getMainButton() {
        return mainButton;
    }

    public Button getExitButton(){return exit;}

    @Override
    public void onUpdate(double deltaT) {
        super.onUpdate(deltaT);
    }

    @Override
    public boolean onStop() {
        return super.onStop();
    }

}
