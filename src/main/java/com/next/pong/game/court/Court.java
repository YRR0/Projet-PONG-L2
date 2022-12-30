package com.next.pong.game.court;

import com.next.pong.game.ball.Ball;
import com.next.pong.game.physics.Collision;
import com.next.pong.game.player.Player;
import com.next.pong.utils.MathUtils;
import com.next.pong.utils.Vector2;

public class Court {

    public interface Listener {
        void onPlayerScored(int id);
        void onBallVerticalWallCollision(int id);
        void onBallPlayerCollision(int id);
    }

    private Listener listener;
    
    public boolean pause = false;
    
    private final int width;
    private final int height;

    private final Ball ball;
    private boolean isBallInCollisionSpeed;

    private final Player playerA;
    private final Player playerB;

    private static final double MAX_PLAYER_BALL_DEFLECTION = 0.5 * Math.PI;

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

        clampPlayers();
        ballWallCollision();
        ballPlayerCollision();
    }

    private void clampPlayers() {
        clampPlayer(playerA);
        clampPlayer(playerB);
    }

    private void clampPlayer(Player player) {
        var position = player.getPosition();
        var size = player.getSize();

        var offsetX = 0.5 * size.x();
        var offsetY = 0.5 * size.y();

        boolean isHittingWallX = (offsetX > position.x() || position.x() > width - offsetX);
        boolean isHittingWallY = (offsetY > position.y() || position.y() > height - offsetY);

        if(isHittingWallX || isHittingWallY) {
            player.neutraliseSpeed();
        }

        var x = MathUtils.clamp(offsetX, position.x(), width - offsetX);
        var y = MathUtils.clamp(offsetY, position.y(), height - offsetY);

        player.setPosition(new Vector2(x, y));
    }

    private void ballWallCollision() {
        var position = ball.getPosition();
        var radius = ball.getRadius();

        var isTooFarLeft = position.x() - radius < 0;
        var isTooFarRight = position.x() + radius > width;

        if (isTooFarLeft && listener != null) {
            listener.onPlayerScored(playerB.getId());
        }

        if (isTooFarRight && listener != null) {
            listener.onPlayerScored(playerA.getId());
        }

        var isTooFarBottom = position.y() - radius < 0;
        var isTooFarTop = position.y() + radius > height;

        if ((isTooFarBottom || isTooFarTop) && !isBallInCollisionSpeed) {
            ball.flipSpeedY();

            if (listener != null) {
                if(position.y() - radius < 0) {
                    listener.onBallVerticalWallCollision(playerA.getId());
                } else {
                    listener.onBallVerticalWallCollision(playerB.getId());
                }
            }
        }

        isBallInCollisionSpeed = isTooFarBottom || isTooFarTop;
    }

    private void ballPlayerCollision() {
        var ballBoundary = ball.getBoundary();

        boolean isInPlayerA = Collision.areColliding(ballBoundary, playerA.getBoundary());
        boolean isInPlayerB = Collision.areColliding(ballBoundary, playerB.getBoundary());

        if ((isInPlayerA || isInPlayerB) && !isBallInCollisionSpeed) {

            ball.flipSpeedX();
            var offsetParam = playerBallOffsetParam(isInPlayerA ? playerA : playerB);
            ball.setSpeed(ball.getSpeedAngle() + offsetParam * MAX_PLAYER_BALL_DEFLECTION);

            if(listener != null) {
                if(isInPlayerA) {
                    listener.onBallPlayerCollision(playerA.getId());
                } else {
                    listener.onBallPlayerCollision(playerB.getId());
                }
            }
        }

        isBallInCollisionSpeed = isInPlayerA || isInPlayerB;
    }

    private double playerBallOffsetParam(Player player) {
        var offset = ball.getPosition().y() - player.getPosition().y();
        return 2 * offset / player.getSize().y();
    }

}
