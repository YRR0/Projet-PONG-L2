package com.next.pong.pages.game;

import com.next.pong.framework.activity.Activity;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;
import com.next.pong.game.state.Court;
import com.next.pong.game.state.GameParameters;
import javafx.scene.Scene;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity<GameLayout> {

    public GameActivity() {
        super(new GameLayout(generateCourt(), 1.0));

        var court = getLayout().getCourt();
        initScene(court.getPlayerA(), court.getPlayerB(), getScene());
    }

    private static void initScene(RacketController playerA, RacketController playerB, Scene gameScene){

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
    }

    private static Court generateCourt(){
        var gp = new GameParameters(1000, 600);

        Player playerA = new Player();
        Player playerB = new AIPlayer(gp);

        return new Court(playerA, playerB, gp);
    }

}
