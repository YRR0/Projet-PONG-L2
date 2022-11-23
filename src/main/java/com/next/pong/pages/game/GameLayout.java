package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;

public class GameLayout extends Layout {

    private final BallElement ballElement;

    public GameLayout() {
        super();

        ballElement = new BallElement();

        addElements(ballElement);
    }

    public void setBallProperties(double x, double y, double radius) {
        ballElement.setBallProperties(x, y, radius);
    }

}
