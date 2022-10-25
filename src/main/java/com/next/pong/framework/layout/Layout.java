package com.next.pong.framework.layout;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.application.Application;
public abstract class Layout extends Pane {

    public Layout() {
        this(853, 480);
    }

    public Layout(int width, int height) {
        setMinWidth(width);
        setMinHeight(height);
        setBackground(Color.BLACK);
    }

    public void onUpdate(float deltaT) {}
    public void onStop() {
        //Platform.exit();
    }

    protected void addElements(Node... elements) {
        getChildren().addAll(elements);
    }

    protected void removeElements(Node... elements) {
        getChildren().removeAll(elements);
    }

    protected void setBackground(Color colour) {
        setBackground(colour, CornerRadii.EMPTY);
    }

    protected void setBackground(Color colour, CornerRadii cornerRadii) {
        setBackground(colour, cornerRadii, Insets.EMPTY);
    }

    protected void setBackground(Color colour, CornerRadii cornerRadii, Insets insets) {
        setBackground(
                new Background(
                        new BackgroundFill(colour, cornerRadii, insets)
                )
        );
    }
}
