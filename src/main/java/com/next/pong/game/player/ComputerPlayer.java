package com.next.pong.game.player;

import com.next.pong.game.ball.Ball;
import com.next.pong.utils.Vector2;

public class ComputerPlayer extends Player {

    public ComputerPlayer(Vector2 position, Vector2 speed, Vector2 size) {
        super(position, speed, size);
    }

    public void update(Ball ball, int layoutWidth) {
        Vector2 RacketPosition = getPosition();
        Vector2 BallPosition = ball.getPosition();

        double screenMid = 0.5 * layoutWidth;

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
