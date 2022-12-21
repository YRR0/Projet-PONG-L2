package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;
import com.next.pong.pages.game.elements.Racket;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameLayout extends Layout {

    private Text score, time;
    private Racket playerElementA, playerElementB;
    private BallElement ballElement;
    public final GamePause gamePause;
    private Circle sun, c2;
    Line line, line2;

    public GameLayout() {
        super();
        this.setBackground(Color.BLACK);
        initScore();
        initTimer();
        initElements();
        // Configuration des buttons de pause;
        gamePause = new GamePause();
        gamePause.buttonStyle();
        addStyleSheet(Resources.Style.BUTTON_STYLE);
        // Animations
        line = new Line(50, 0, Layout.DEFAULT_WIDTH - 50, 0);
        line.setStrokeWidth(20);
        line2 = new Line(50, Layout.DEFAULT_HEIGHT, Layout.DEFAULT_WIDTH - 50, Layout.DEFAULT_HEIGHT);
        line2.setStrokeWidth(20);
        animationBlock();
        ballAnimation();
        addElements(sun, c2, line, line2, gamePause);
        line.getStyleClass().add("line");
        line2.getStyleClass().add("line");
        addStyleSheet(Resources.Style.FIELD_STYLE);
    }

    private void initScore() { // Pour le score
        score = new Text("0 : 0");
        score.setY(100);
        score.setFill(Color.WHITE);
        score.setId("score");
        addElements(score);
    }

    private void initTimer() { // Pour le timer
        time = new Text();
        time.setY(Layout.DEFAULT_HEIGHT - 70);
        time.setFill(Color.LIGHTPINK);
        time.setId("time");
        addElements(time);
    }

    private void initElements() {
        playerElementA = new Racket();
        playerElementB = new Racket();
        addElements(playerElementA, playerElementB);
        ballElement = new BallElement();
        playerElementA.setFill(Color.LIGHTCYAN);
        playerElementB.setFill(Color.LIGHTCYAN);
        addElements(ballElement);
    }

    public void setScore(int x, int y) {
        score.setText(x + " : " + y);
        center(score);
    }

    public void setTime(Text time) {
        this.time.setText(time.getText());
        center(this.time);
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

    public static void center(Text text) {
        var width = text.getLayoutBounds().getWidth();
        text.setX(0.5 * DEFAULT_WIDTH - 0.5 * width);
    }

    public void animationBlock() {
        // Create a sun transition
        //Creation of the sun using the circle shape
        sun = new Circle();
        c2 = new Circle();
        sun.setOpacity(0.3);
        c2.setOpacity(0.3);
        c2.setRadius(DEFAULT_HEIGHT * 0.1);
        c2.setFill(Color.LIGHTGREEN);
        //c2.setOpacity(0.5);
        sun.setRadius(DEFAULT_HEIGHT * 0.1);
        sun.setFill(Color.CADETBLUE);

        //Set the sun position
        sun.setCenterX(Layout.DEFAULT_WIDTH / 2 - DEFAULT_WIDTH * 0.2);
        sun.setCenterY(10);
        c2.setCenterX(Layout.DEFAULT_WIDTH / 2 + DEFAULT_WIDTH * 0.2);
        c2.setCenterY(Layout.DEFAULT_HEIGHT - 10);

        // Create a translate transition
        TranslateTransition movement = new TranslateTransition();
        TranslateTransition movement2 = new TranslateTransition();

        //Setting the duration of the transition
        movement.setDuration(Duration.millis(5000));
        movement2.setDuration(Duration.millis(5000));

        //Set node for the transition
        movement.setNode(sun);
        movement2.setNode(c2);

        // Set the value of the transition along the Y axis
        movement.setByY(Layout.DEFAULT_HEIGHT);
        //movement.setByX(width);
        movement2.setByY(-Layout.DEFAULT_HEIGHT);
        //movement2.setByX(-width);


        // Set cycle count for transition
        movement.setCycleCount(100);
        movement2.setCycleCount(100);

        //Set reverse value to true
        movement.setAutoReverse(true);
        movement2.setAutoReverse(true);

        //Play animation
        movement.play();
        movement2.play();
    }



    public void pauseOpacity() {
        playerElementA.setOpacity(0.3);
        playerElementB.setOpacity(0.3);
        score.setOpacity(1);
        ballElement.setOpacity(0.3);
        line.setOpacity(0.3);
        line2.setOpacity(0.3);
        time.setOpacity(0.3);
    }

    public void restoreOpa() {
        playerElementA.setOpacity(0.9);
        playerElementB.setOpacity(0.9);
        score.setOpacity(0.9);
        ballElement.setOpacity(0.9);
        line.setOpacity(0.9);
        line2.setOpacity(0.9);
        time.setOpacity(0.9);
    }

    private void ballAnimation() {
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(1.0f);
        ds1.setOffsetX(1.0f);
        ds1.setColor(Color.WHITESMOKE);
        ballElement.setEffect(ds1);
    }
}
