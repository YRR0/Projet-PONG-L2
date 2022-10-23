package com.next.pong.game.player.ai;

import com.next.pong.game.state.Court;
import com.next.pong.game.state.GameParameters;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;

public class AIPlayer extends Player implements IAInterface {

    private GameParameters gp;
    private int level;

    public AIPlayer(GameParameters gp) {
        super.setState(State.IDLE);
        this.gp = gp;
        this.level = 2;
    }

    public AIPlayer(GameParameters gp, int level) {
        super.setState(State.IDLE);
        this.gp = gp;
        this.level = level;
    }

    public void reset(GameParameters gp) {
        super.setState(State.IDLE);
        this.gp = gp;
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
        switch(this.level) {
            case 1 : easyLevel(width, ballX, ballY, racketB);
            break;
            case 3 : highLevel(width, ballX, ballY, racketB);
            break;
            default: mediumLevel(width, ballX, ballY, racketB);
        }

    }

    private void easyLevel(double width, double ballX, double ballY, double racketB) {
        if (ballX > 0.7 * width) {
            if (ballY < racketB) {
                this.moveUp();
            } else if (ballY + Court.BALL_RADIUS > racketB + Court.RACKET_SIZE) {
                this.moveDown();
            }
        }
    }

    private void mediumLevel(double width, double ballX, double ballY, double racketB) {
        if (ballX > 0.65 * width) {
            System.out.println("ici");
            if (ballY < racketB) {
                this.moveUp();
            } else if (ballY > racketB + (Court.RACKET_SIZE / 2)) {
                this.moveDown();
            }
        } else {
            System.out.println("pas ici");
        }
    }

    private void highLevel(double width, double ballX, double ballY, double racketB) {
        if (ballX > 0.6 * width) {
            if (ballY < racketB) {
                this.moveUp();
            } else if (ballY + Court.BALL_RADIUS > racketB + Court.RACKET_SIZE) {
                this.moveDown();
            }
        }
    }

}