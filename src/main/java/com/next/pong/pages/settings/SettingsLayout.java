package com.next.pong.pages.settings;

import com.next.pong.content.Resources;
import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SettingsLayout extends Layout {

    Label text;
    Pane pane;
    public SettingsLayout() {
        super(Resources.Layout.SETTINGS);
    }

    void waitInput() {
        text = new Label("Click the new button");
        pane = new Pane();
        pane.setPrefWidth(Layout.DEFAULT_WIDTH);
        pane.setPrefHeight(Layout.DEFAULT_HEIGHT);
        pane.setBackground(Background.fill(Color.LIGHTGRAY));
        pane.setOpacity(0.6);
        text.setFont(new Font("Arial", 40));
        text.setLayoutX((0.5 * Layout.DEFAULT_WIDTH) - 200);
        text.setLayoutY(0.5 * Layout.DEFAULT_HEIGHT);
        addElements(pane, text);
    }

    void clearInput() {
        removeElements(text, pane);
    }

}
