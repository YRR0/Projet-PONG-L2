package com.next.pong.pages.home;

import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class HomeLayout extends Layout {

    private final Button mainButton;

    public HomeLayout() {
        setBackground(Color.FIREBRICK);

        mainButton = new Button("PLAY");
        addElements(mainButton);
    }

    public Button getMainButton() {
        return mainButton;
    }

}
