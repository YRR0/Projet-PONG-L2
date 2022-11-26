package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;
import com.next.pong.pages.game.elements.PlayerElement;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameLayout extends Layout {

    private final Text text;

    private final PlayerElement playerElementA;
    private final PlayerElement playerElementB;

    private final BallElement ballElement;

    public GameLayout() {
        super();

        text = new Text("0 : 0");
        text.setY(70);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        addElements(text);

        playerElementA = new PlayerElement();
        playerElementB = new PlayerElement();
        addElements(playerElementA, playerElementB);

        ballElement = new BallElement();
        addElements(ballElement);
    }

    public void setScore(int x, int y) {
        text.setText(x + " : " + y);
        center(text);
    }

    public void setBallProperties(double x, double y, double radius) {
        ballElement.setBallProperties(x, y, radius);
    }

    public void setPlayerElementA(double x, double y, double width, double height) {
        playerElementA.setPlayerProperties(x, y, width, height);
    }

    public void setPlayerElementB(double x, double y, double width, double height) {
        playerElementB.setPlayerProperties(x, y, width, height);
    }

    private void center(Text text) {
        var width = text.getLayoutBounds().getWidth();
        text.setX(0.5 * DEFAULT_WIDTH - 0.5 * width);
    }

}
