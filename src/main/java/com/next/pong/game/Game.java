package com.next.pong.game;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.court.Court;
import com.next.pong.game.player.Player;

public class Game {

    public interface Listener {
        void onPlayerScored();
        void onBallVerticalWallCollision(int id);
        void onBallPlayerCollision(int id);
    }

    private Listener listener;

    private final Court court;

    private int scorePlayerA;
    private int scorePlayerB;

    public Game(int width, int height, Ball ball, Player playerA, Player playerB) {

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
            public void onBallVerticalWallCollision(int id) {
                if (listener != null) {
                    listener.onBallVerticalWallCollision(id);
                }
            }

            @Override
            public void onBallPlayerCollision(int id) {
                if(listener != null) {
                    listener.onBallPlayerCollision(id);
                }
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void update(double deltaTime) {
        if(!court.pause) {
        	court.update(deltaTime);
        }
    }
    
    public Court getCourt() {
    	return court;
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
