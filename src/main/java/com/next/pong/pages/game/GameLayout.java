package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;
import com.next.pong.pages.game.elements.PlayerElement;

public class GameLayout extends Layout {

    private final PlayerElement playerElementA;
    private final PlayerElement playerElementB;

    private final BallElement ballElement;

    public GameLayout() {
        super();

        playerElementA = new PlayerElement();
        playerElementB = new PlayerElement();
        addElements(playerElementA, playerElementB);

        ballElement = new BallElement();
        addElements(ballElement);
    }

    public void setBallProperties(double x, double y, double radius) {
        ballElement.setBallProperties(x, y, radius);
    }

    public void setPlayerElementA(double x, double y, double width, double height) {
        playerElementA.setPlayerProperties(x, y, width, height);
    }

    public void setPlayerElementB(double x, double y, double width, double height) {
        System.out.println(x);
        playerElementB.setPlayerProperties(x, y, width, height);
    }

}
