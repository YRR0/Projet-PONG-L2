package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.next.pong.game.state.Court;
import javafx.scene.text.*;

public class GameLayout extends Layout {

    private final Court court;
    private final double scale;

    private static final double X_MARGIN = 50.0; // px
    private static final double RACKET_THICKNESS = 10.0; // px

    private final Rectangle racketA, racketB;
    private final Circle ball;
    private final Text text;

    private static final double MS_TO_SEC = 0.001;

    public GameLayout(Court court, double scale) {
        super();

        this.scale = scale;
        this.court = court;

        setMinWidth((int) (court.getWidth() * scale + 2 * X_MARGIN));
        setMinHeight((int) (court.getHeight() * scale));

        racketA = new Rectangle();
        racketA.setHeight(Court.RACKET_SIZE * scale);
        racketA.setWidth(RACKET_THICKNESS);
        racketA.setFill(Color.BLACK);

        racketA.setX(X_MARGIN - RACKET_THICKNESS);
        racketA.setY(court.getGP().getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(Court.RACKET_SIZE * scale);
        racketB.setWidth(RACKET_THICKNESS);
        racketB.setFill(Color.BLACK);

        racketB.setX(court.getWidth() * scale + X_MARGIN);
        racketB.setY(court.getGP().getRacketB() * scale);

        ball = new Circle();
        ball.setRadius(Court.BALL_RADIUS);
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getGP().getBallX() * scale + X_MARGIN);
        ball.setCenterY(court.getGP().getBallY() * scale);

        text = new Text();
        text.setX(court.getWidth() / 2);
        text.setY(70);
        text.setFont(Font.font("Times new Roman", FontWeight.BOLD, 50));

        addElements(racketA, racketB, ball, text);
    }

    @Override
    public void onUpdate(double deltaMs) {
        court.update(deltaMs * MS_TO_SEC);
        racketA.setY(court.getGP().getRacketA() * scale);
        racketB.setY(court.getGP().getRacketB() * scale);
        ball.setCenterX(court.getGP().getBallX() * scale + X_MARGIN);
        ball.setCenterY(court.getGP().getBallY() * scale);
        text.setText(court.getScoreL() + " : " + court.getScoreR());
    }

    public Court getCourt() {
        return court;
    }

}
