package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import javafx.scene.shape.Circle;

public class GameLayout extends Layout {

    private double ballRadius;
    private double ballX;
    private double ballY;
    private final Circle ballElement;

    public GameLayout() {
        super();

        ballElement = new Circle();

        addElements(ballElement);
    }

    public void setBallProperties(double x, double y, double radius) {
        this.ballX = x;
        this.ballY = y;
        this.ballRadius = radius;
    }

    @Override
    public void onUpdate(double deltaMs) {
        ballElement.setRadius(ballRadius);
        ballElement.setCenterX(ballX);
        ballElement.setCenterY(ballY);
    }

}
