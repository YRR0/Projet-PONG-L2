package com.next.pong.framework.layout;

import com.next.pong.content.Resources;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;


public abstract class Layout extends Pane {

    public static final int DEFAULT_WIDTH = 853;
    public static final int DEFAULT_HEIGHT = 480;

    public Layout() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Layout(int width, int height) {
        setMinWidth(width);
        setMinHeight(height);
        setBackground(Color.WHITE);
    }

    public Layout(String layoutName) {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/next/pong/layout/" + layoutName + ".fxml")
        );

        try {
            Parent parent = loader.load();
            addElements(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Layout(Resources.Layout layout) {
        this(layout.toString());
    }

    public void onUpdate(double deltaMs) {
    }

    public void onDestroy() {
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
