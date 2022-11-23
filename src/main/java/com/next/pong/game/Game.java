package com.next.pong.game;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.court.Court;
import com.next.pong.game.player.Player;
import com.next.pong.utils.Vector2;

public class Game {

    private final Court court;

    private int scorePlayerOne;
    private int scorePlayerTwo;

    public Game(int width, int height, Player playerOne, Player playerTwo) {
        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2(200, 300),
                10
        );

        court = new Court(width, height, ball, playerOne, playerTwo);
    }

    public void update(double deltaTime) {
        court.update(deltaTime);
    }

    public Ball getBall() {
        return court.getBall();
    }

}
