package com.next.pong.pages.menu;

import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class MenuLayout extends Layout {

    private final Button mainButton;

    public MenuLayout() {
        setBackground(Color.FIREBRICK);

        mainButton = new Button("Click Me!");
        addElements(mainButton);
    }

    public Button getMainButton() {
        return mainButton;
    }

    @Override
    public void onUpdate(float deltaT) {
        super.onUpdate(deltaT);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
