package com.next.pong.game.player;

import com.next.pong.framework.layout.Layout;
import com.next.pong.game.ball.Ball;
import com.next.pong.utils.Vector2;

public class ComputerPlayer extends Player {

    private Ball ball;
    public ComputerPlayer(Vector2 position, Vector2 speed, Vector2 size, Ball ball) {
        super(position, speed, size);
        this.ball = ball;
        keyEventListener = null;
    }

    private void moveUp() {
        applyForceUp();
    }

    private void moveDown() {
        applyForceDown();
    }

    public void move() {
        Vector2 RacketPosition = this.getPosition();
        Vector2 BallPosition   = ball.getPosition();
        double screenMid = Layout.DEFAULT_WIDTH / 2;
        if((RacketPosition.x() > screenMid && BallPosition.x() > screenMid) || (RacketPosition.x() < screenMid && BallPosition.x() < screenMid)) {
            if(BallPosition.y() < RacketPosition.y()) {
                moveUp();
            } else if(BallPosition.y() > RacketPosition.y()) {
                moveDown();
            }
        }

    }

}
