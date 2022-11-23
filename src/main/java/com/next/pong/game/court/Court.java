package com.next.pong.game.court;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.physics.Collision;
import com.next.pong.game.player.Player;

public class Court {

    private final int width;
    private final int height;

    private final Ball ball;
    private final Player playerA;
    private final Player playerB;

    public Court(int width, int height, Ball ball, Player playerA, Player playerB) {
        this.width = width;
        this.height = height;
        this.ball = ball;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public Ball getBall() {
        return ball;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void update(double deltaTime) {
        ball.integratePosition(deltaTime);
        playerA.integratePosition(deltaTime);
        playerB.integratePosition(deltaTime);

        ballWallCollision();
        ballPlayerCollision();
    }

    private void ballWallCollision() {
        var position = ball.getPosition();
        var radius = ball.getRadius();

        if (!(0 <= position.x() - radius && position.x() + radius <= width)) {
            ball.flipSpeedX();
        }

        if (!(0 <= position.y() - radius && position.y() + radius <= height)) {
            ball.flipSpeedY();
        }
    }

    private void ballPlayerCollision() {
        var ballBoundary = ball.getBoundary();
        boolean isInPlayerA = Collision.areColliding(ballBoundary, playerA.getBoundary());
        boolean isInPlayerB = Collision.areColliding(ballBoundary, playerB.getBoundary());

        if (isInPlayerA || isInPlayerB) {
            ball.flipSpeedX();
        }
    }

}
