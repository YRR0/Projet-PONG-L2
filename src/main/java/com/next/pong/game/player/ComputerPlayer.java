package com.next.pong.game.player;

import com.next.pong.game.ball.Ball;
import com.next.pong.utils.Vector2;

public class ComputerPlayer extends Player {

    public enum Level {
        EASY, MEDIUM, HARD;
    }

    private Level level;

    public ComputerPlayer(Vector2 position, Vector2 speed, Vector2 size, Level level) {
        super(position, speed, size);
        this.level = level;
    }

    public void update(Ball ball, int layoutWidth) {
        Vector2 RacketPosition = getPosition();
        Vector2 BallPosition = ball.getPosition();

        double factor = switch (level) {
            case MEDIUM -> 0.5;
            case EASY -> 0.7;
            case HARD -> 0.25;
        };

        var movePos = ((RacketPosition.x() > 0.5 * layoutWidth) ? factor : (1 - factor)) * layoutWidth;

        if (BallPosition.x() > movePos) {
            if (BallPosition.y() < RacketPosition.y()) {
                applyForceUp();
            } else if (BallPosition.y() > RacketPosition.y()) {
                applyForceDown();
            }
        }
    }

}
