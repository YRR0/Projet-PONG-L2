package com.next.pong.pages.game.elements;

import javafx.scene.shape.Rectangle;

public class PlayerElement extends Rectangle {

    public void setPlayerProperties(double x, double y, double width, double height) {
        setX(x - 0.5 * width);
        setY(y - 0.5 * height);
        setWidth(width);
        setHeight(height);
    }

}
