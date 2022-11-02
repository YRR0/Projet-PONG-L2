package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import com.next.pong.game.state.Court;
import javafx.scene.text.*;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;


public class GameLayout extends Layout {

    private final Court court;
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels
    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;
    private final Text text;

    public GameLayout(Court court, double scale) {
        super();

        this.scale = scale;
        this.court = court;

        setMinWidth((int)(court.getWidth() * scale + 2 * xMargin));
        setMinHeight((int)(court.getHeight() * scale));

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

        //add the nodes into the layout
        addElements(racketA, racketB, ball, text);
    }

    @Override
    public void onUpdate(double deltaTime){
        court.update(deltaTime);
        racketA.setY(court.getGP().getRacketA() * scale);
        racketB.setY(court.getGP().getRacketB() * scale);
        ball.setCenterX(court.getGP().getBallX() * scale + xMargin);
        ball.setCenterY(court.getGP().getBallY() * scale);
        text.setText(court.getScoreL() + " : " + court.getScoreR());
    }

    @Override
    public boolean onStop(){
        boolean stop = true;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(" Quitter");
        alert.setHeaderText(" Vous allez quitter ...... ");
        alert.setContentText(" Nous espérons vous revoir bientot");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You quitted !!");
        }
        else {
            stop=false;
        }
        return stop;
    }

    public Court getCourt(){
        return court;
    }

}
