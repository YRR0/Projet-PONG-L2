package com.next.pong.game.court;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.physics.Collision;
import com.next.pong.game.player.Player;
import com.next.pong.utils.Vector2;

public class Court {

    public interface Listener {
        void onPlayerScored(int id);

        void onBallVerticalWallCollision();
    }

    private Listener listener;

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

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void resetBall() {
        ball.setPosition(new Vector2(0.5 * width, 0.5 * height));
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

        var isTooFarLeft = 0 > position.x() - radius;
        var isTooFarRight = position.x() + radius > width;

        if (isTooFarLeft || isTooFarRight) {
            ball.flipSpeedX();
        }

        if (isTooFarLeft && listener != null) {
            listener.onPlayerScored(playerB.getId());
        }

        if (isTooFarRight && listener != null) {
            listener.onPlayerScored(playerA.getId());
        }

        if (!(0 <= position.y() - radius && position.y() + radius <= height)) {
            ball.flipSpeedY();

            if (listener != null) {
                listener.onBallVerticalWallCollision();
            }
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
