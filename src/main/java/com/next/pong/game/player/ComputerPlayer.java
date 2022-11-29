package com.next.pong.game.player;

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
        if(ball.getPosition().y() < getPosition().y()) {
            moveUp();
        } else if(ball.getPosition().y() > getPosition().y() + getSize().y()) {
            moveDown();
        }
    }

}
