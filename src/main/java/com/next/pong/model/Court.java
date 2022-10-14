package com.next.pong.model;

/*import javax.crypto.spec.GCMParameterSpec;
import javax.swing.plaf.nimbus.State;
import com.next.pong.model.RacketController;
import com.next.pong.model.GameParameters;
import com.next.pong.model.AIPlayer;*/

public class Court {

    // instance parameters
    private final RacketController playerA, playerB;
    private final double width, height; // m
    private final double racketSpeed = 300.0; // m/s
    private final double racketSize = 100.0; // m
    private final double ballRadius = 10.0; // m
    public int score1 = 0;
    public int score2 = 0;

    private GameParameters gp;

    public Court(RacketController playerA, RacketController playerB, double width, double height, GameParameters gp) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.width = width;
        this.height = height;
        this.gp = gp;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRacketSize() {
        return racketSize;
    }

    public GameParameters getGP() {
        return gp;
    }

    public void setGameParameters(GameParameters gp) {
        this.gp = gp;
    }

    public void update(double deltaT) {

        switch (playerA.getState()) {
            case GOING_UP:
                gp.racketA -= racketSpeed * deltaT;
                if (gp.racketA < 0.0) gp.racketA = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                gp.racketA += racketSpeed * deltaT;
                if (gp.racketA + racketSize > height) gp.racketA = height - racketSize;
                break;
        }
        switch (playerB.getState()) {
            case GOING_UP:
                gp.racketB -= racketSpeed * deltaT;
                if (gp.racketB < 0.0) gp.racketB = 0.0;
                break;
            case IDLE:
                break;
            case GOING_DOWN:
                gp.racketB += racketSpeed * deltaT;
                if (gp.racketB + racketSize > height) gp.racketB = height - racketSize;
                break;
        }
        if (updateBall(deltaT)) {
            System.out.println("Le score est " + score1 + ":" + score2);
            this.gp = new GameParameters(height, width);
            if(playerB instanceof AIPlayer) {
                ((AIPlayer)this.playerB).reset(gp);
            }
            
        }
    }


    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = gp.ballX + deltaT * gp.ballSpeedX;
        double nextBallY = gp.ballY + deltaT * gp.ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > height) {
            gp.ballSpeedY = -gp.ballSpeedY;
            nextBallY = gp.ballY + deltaT * gp.ballSpeedY;
        }
        if ((nextBallX < 0 && nextBallY > gp.racketA && nextBallY < gp.racketA + racketSize + ballRadius/2)
                || (nextBallX > width && nextBallY > gp.racketB && nextBallY < gp.racketB + racketSize + ballRadius/2)) {
            gp.ballSpeedX = -gp.ballSpeedX;
            nextBallX = gp.ballX + deltaT * gp.ballSpeedX;
        } else if (nextBallX < 0) {
            score1++;
            return true;
        } else if (nextBallX > width) {
            score2++;
            return true;
        }
        gp.ballX = nextBallX;
        gp.ballY = nextBallY;
        return false;
    }

    public double getBallRadius() {
        return ballRadius;
    }
}
