package com.next.pong.pages.game.views;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.next.pong.game.state.Court;
import javafx.scene.text.*;

public class GameView {

    // class parameters
    private final Court court;
    private final Pane gameRoot; // main node of the game
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels
    private Text text;
    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;

    /**
     * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout ce qu'il y a dessus)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera affiché
     * @param scale le facteur d'échelle entre les distances du modèle et le nombre de pixels correspondants dans la vue
     */
    public GameView(Court court, Pane root, double scale) {
        this.court = court;
        this.gameRoot = root;
        this.scale = scale;

        root.setMinWidth(court.getWidth() * scale + 2 * xMargin);
        root.setMinHeight(court.getHeight() * scale);

        racketA = new Rectangle();
        racketA.setHeight(Court.RACKET_SIZE * scale);
        racketA.setWidth(racketThickness);
        racketA.setFill(Color.BLACK);

        racketA.setX(xMargin - racketThickness);
        racketA.setY(court.getGP().getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(Court.RACKET_SIZE * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.BLACK);

        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getGP().getRacketB() * scale);

        ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getGP().getBallX() * scale + xMargin);
        ball.setCenterY(court.getGP().getBallY() * scale);

        text = new Text();
        text.setX(court.getWidth() / 2);
        text.setY(70);
        text.setFont(Font.font("Times new Roman", FontWeight.BOLD, 50));

        gameRoot.getChildren().addAll(racketA, racketB, ball, text);


    }

    public void animate() {
        new AnimationTimer() {
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) { // ignore the first tick, just compute the first deltaT
                    last = now;
                    return;
                }
                court.update((now - last) * 1.0e-9); // convert nanoseconds to seconds
                last = now;
                racketA.setY(court.getGP().getRacketA() * scale);
                racketB.setY(court.getGP().getRacketB() * scale);
                ball.setCenterX(court.getGP().getBallX() * scale + xMargin);
                ball.setCenterY(court.getGP().getBallY() * scale);
                text.setText(court.getScoreL() + " | " + court.getScoreR());
            }
        }.start();
    }

}
