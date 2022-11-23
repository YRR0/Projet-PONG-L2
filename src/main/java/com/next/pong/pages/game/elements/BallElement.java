package com.next.pong.pages.game.elements;

import javafx.scene.shape.Circle;

public class BallElement extends Circle {

    public void setBallProperties(double x, double y, double radius) {
        setCenterX(x);
        setCenterY(y);
        setRadius(radius);
    }

}
