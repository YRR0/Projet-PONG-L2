package com.next.pong.game.player.ai;

import com.next.pong.game.state.Court;
import com.next.pong.game.state.GameParameters;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;


public class AIPlayer extends Player implements IAInterface {

    private GameParameters gp;
    private Level level;

    public AIPlayer(GameParameters gp, Level l) {
        super.setState(State.IDLE);
        this.gp = gp;
        this.level = l;
    }

    public void reset(GameParameters gp, Level l) {
        super.setState(State.IDLE);
        this.gp = gp;
        this.level = l;
    }

    public Level getLevel() {
        return level;
    }

    public void moveUp() {
        super.setState(RacketController.State.GOING_UP);
    }

    public void moveDown() {
        super.setState(RacketController.State.GOING_DOWN);
    }

    public void upOrDown() {

        var width = gp.getWidth();

        var ballX = gp.getBallX();
        var ballY = gp.getBallY();
        var racketB = gp.getRacketB();

        switch (this.level) {
            case EASY -> onLevel(width, ballX, ballY, racketB, 0.7);
            case MEDIUM -> onLevel(width, ballX, ballY, racketB, 0.65);
            case HARD -> onLevel(width, ballX, ballY, racketB, 0.5);
        }
    }

    public void onLevel(double width, double ballX, double ballY, double racket, double f) {
        if(ballX > f * width) {
            if (ballY < racket) {
                this.moveUp();
            } else if (ballY + Court.BALL_RADIUS > racket + Court.RACKET_SIZE) {
                this.moveDown();
            }
        }
    }

}