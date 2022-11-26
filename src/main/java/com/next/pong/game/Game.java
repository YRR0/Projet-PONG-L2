package com.next.pong.game;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.court.Court;
import com.next.pong.game.player.Player;
import com.next.pong.utils.Vector2;

public class Game {

    public interface Listener {
        void onPlayerScored();

        void onBallVerticalWallCollision();
    }

    private Listener listener;

    private final Court court;

    private int scorePlayerA;
    private int scorePlayerB;

    public Game(int width, int height, Player playerA, Player playerB) {
        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2(200, 300),
                10
        );

        court = new Court(width, height, ball, playerA, playerB);

        court.setListener(new Court.Listener() {
            @Override
            public void onPlayerScored(int id) {
                if (id == playerA.getId()) {
                    scorePlayerA++;
                } else if (id == playerB.getId()) {
                    scorePlayerB++;
                } else {
                    System.out.println("Warning: Unknown player in Court (" + id + ")");
                }

                court.resetBall();

                if (listener != null) {
                    listener.onPlayerScored();
                }
            }

            @Override
            public void onBallVerticalWallCollision() {
                if (listener != null) {
                    listener.onBallVerticalWallCollision();
                }
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void update(double deltaTime) {
        court.update(deltaTime);
    }

    public Ball getBall() {
        return court.getBall();
    }

    public int getScorePlayerA() {
        return scorePlayerA;
    }

    public int getScorePlayerB() {
        return scorePlayerB;
    }

    public Player getPlayerA() {
        return court.getPlayerA();
    }

    public Player getPlayerB() {
        return court.getPlayerB();
    }

}
