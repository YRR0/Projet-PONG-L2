package com.next.pong.pages.home;

import com.next.pong.framework.layout.Layout;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class HomeLayout extends Layout {

    private final Button mainButton;
    private final Button exit;

    public HomeLayout() {
        setBackground(Color.FIREBRICK);

        mainButton = new Button("Click Me!");
        exit = new Button("Quitter");
        exit.setTranslateY(30);
        addElements(mainButton,exit);
    }

    public Button getMainButton() {
        return mainButton;
    }

    public Button getExitButton(){return exit;}

    @Override
    public void onUpdate(float deltaT) {
        super.onUpdate(deltaT);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
