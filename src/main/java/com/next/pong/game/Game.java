package com.next.pong.game;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.court.Court;
import com.next.pong.game.player.Player;
import com.next.pong.utils.Vector2;

public class Game {

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
    }

    public void update(double deltaTime) {
        court.update(deltaTime);
    }

    public Ball getBall() {
        return court.getBall();
    }

    public Player getPlayerA() {
        return court.getPlayerA();
    }

    public Player getPlayerB() {
        return court.getPlayerB();
    }

}
