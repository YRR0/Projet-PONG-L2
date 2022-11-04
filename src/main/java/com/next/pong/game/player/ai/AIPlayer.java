package com.next.pong.game.player.ai;

import com.next.pong.game.state.GameParameters;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;


public class AIPlayer extends Player implements IAInterface {

    private GameParameters gp;

    public AIPlayer(GameParameters gp) {
        super.setState(State.IDLE);
        this.gp = gp;
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

        if (ballX > width / 2) {
            if (ballY < racketB) {
                this.moveUp();
            } else if (ballY + 10.0 > racketB + 100.0) {
                this.moveDown();
            }
        }
    }

}