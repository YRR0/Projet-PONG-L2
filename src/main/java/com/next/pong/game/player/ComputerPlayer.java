package com.next.pong.game.player;

import com.next.pong.framework.layout.Layout;
import com.next.pong.game.ball.Ball;
import com.next.pong.utils.Vector2;

public class ComputerPlayer extends Player {

    private Ball ball;

    public ComputerPlayer(Vector2 position, Vector2 speed, Vector2 size, Ball ball) {
        super(position, speed, size);
        this.ball = ball;
    }

    public void move() {
        Vector2 RacketPosition = getPosition();
        Vector2 BallPosition = ball.getPosition();

        double screenMid = 0.5 * Layout.DEFAULT_WIDTH;

        boolean isSameLeftSide = RacketPosition.x() < screenMid && BallPosition.x() < screenMid;
        boolean isSameRightSide = RacketPosition.x() > screenMid && BallPosition.x() > screenMid;

        if (isSameLeftSide || isSameRightSide) {
            if (BallPosition.y() < RacketPosition.y()) {
                applyForceUp();
            } else if (BallPosition.y() > RacketPosition.y()) {
                applyForceDown();
            }
        }
    }

}
