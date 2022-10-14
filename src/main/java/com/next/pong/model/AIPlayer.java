package com.next.pong.model;

/*import com.next.pong.model.Player;
import com.next.pong.model.RacketController;
import com.next.pong.model.GameParameters;*/

public class AIPlayer extends Player {

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
        if(gp.ballX > gp.getWidth()/2) {
            if(gp.ballY < gp.racketB) {
                this.moveUp();
            } else if(gp.ballY + 10.0 > gp.racketB + 100.0) {
                this.moveDown();
            } 
        } 
    }
    
}