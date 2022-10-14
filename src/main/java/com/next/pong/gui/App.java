package com.next.pong.gui;

import com.next.pong.model.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.next.pong.model.Court;
import com.next.pong.model.RacketController;
import com.next.pong.model.AIPlayer;
import com.next.pong.model.GameParameters;
import java.util.Timer;
import java.util.TimerTask;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        var root = new Pane();
        var gameScene = new Scene(root);

        var gameParameters = new GameParameters(1000, 600);

        var playerA = new Player();
        var playerB = new AIPlayer(gameParameters);

        gameScene.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case CONTROL -> playerA.setState(RacketController.State.GOING_UP);
                case ALT -> playerA.setState(RacketController.State.GOING_DOWN);
                case UP -> playerB.setState(RacketController.State.GOING_UP);
                case DOWN -> playerB.setState(RacketController.State.GOING_DOWN);
                default -> throw new IllegalArgumentException("Unexpected value: " + ev.getCode());
            }
        });

        final Timer m = new Timer();
        final TimerTask task = new TimerTask() {
            public void run() {
                ((AIPlayer)playerB).upOrDown();
            }
        };
        if(playerB instanceof AIPlayer) {
            m.schedule(task, 0, 110);
        }

        gameScene.setOnKeyReleased(ev -> {
            switch (ev.getCode()) {
                case CONTROL:
                    if (playerA.getState() == RacketController.State.GOING_UP){
                        playerA.setState(RacketController.State.IDLE);
                    }
                    break;
                case ALT:
                    if (playerA.getState() == RacketController.State.GOING_DOWN) {
                        playerA.setState(RacketController.State.IDLE);
                    }
                    break;
                case UP:
                    if (playerB.getState() == RacketController.State.GOING_UP) {
                        playerB.setState(RacketController.State.IDLE);
                    }
                    break;
                case DOWN:
                    if (playerB.getState() == RacketController.State.GOING_DOWN) {
                        playerB.setState(RacketController.State.IDLE);
                    }
                    break;
                default:
                    break;
            }
        });

        var court = new Court(playerA, playerB, 1000, 600, gameParameters);
        var gameView = new GameView(court, root, 1.0);

        primaryStage.setScene(gameScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(ev -> {
            task.cancel();
            m.cancel();
        });
        gameView.animate();
    }

}
