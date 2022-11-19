package com.next.pong.game.state;

import java.util.Timer;

import com.next.pong.Sound;
import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;

public class CourtObs {

    public static final double RACKET_SPEED = 600.0; // m/s
    public static final double RACKET_SIZE = 100.0; // m
    public static final double BALL_RADIUS = 10.0; // m

    // instance parameters
    private final RacketController playerA, playerB, obstacle1 , obstacle2;
    private final double width, height; // m
    private int scoreL = 0, scoreR = 0;

    private GameParametersObs gp;
    private Sound se = new Sound();
    private boolean pause = false;

    public CourtObs(RacketController playerA, RacketController playerB, RacketController obs, RacketController obs2, GameParametersObs gp) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.obstacle1 = obs;
        this.obstacle2 = obs2;
        this.width = gp.getWidth();
        this.height = gp.getHeight();
        this.gp = gp;
    }

    public double getWidth() {
        return width;
    }

    public RacketController getPlayerA(){
        return playerA;
    }

    public RacketController getPlayerB(){
        return playerB;
    }
    
    public RacketController getObs(){
        return obstacle1;
    }

    public RacketController getObs2(){
        return obstacle2;
    }

    public double getHeight() {
        return height;
    }

    public GameParametersObs getGP() {
        return gp;
    }

    public int getScoreL() {
        return scoreL;
    }

    public int getScoreR() {
        return scoreR;
    }

    public void setGameParametersObs(GameParametersObs gp) {
        this.gp = gp;
    }

    public void setPause(boolean v) {
        pause = v;
    }

    public boolean getPause() {
        return pause;
    }

    public void update(double deltaT) {
        if(!pause) {
            var racketA = gp.getRacketA();
            var racketB = gp.getRacketB();
            
            var racketObstacle1 = gp.getObstacle1();
            var racketObstacle2 = gp.getObstacle2();
            
            switch (playerA.getState()) {
                case GOING_UP:
                    gp.setRacketA(racketA - RACKET_SPEED * deltaT);
                    if (racketA < 0.0) gp.setRacketA(0.0);
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    gp.setRacketA(racketA + RACKET_SPEED * deltaT);
                    if (racketA + RACKET_SIZE > height) gp.setRacketA(height - RACKET_SIZE);
                    break;
            }
            switch (playerB.getState()) {
                case GOING_UP:
                    gp.setRacketB(racketB - RACKET_SPEED * deltaT);
                    if (racketB < 0.0) gp.setRacketB(0.0);
                    break;
                case IDLE:
                    break;
                case GOING_DOWN:
                    gp.setRacketB(racketB + RACKET_SPEED * deltaT);
                    if (racketB + RACKET_SIZE > height) gp.setRacketB(height - RACKET_SIZE);
                    break;
            }
            
            /*
            switch(obstacle1.getState()) {
            case GOING_UP:
                gp.setObstacle1( racketObstacle1 - RACKET_SPEED * deltaT);
                if (racketObstacle1 < 0.0) gp.setObstacle1(0.0);
                break;
            	case IDLE:
            	case GOING_DOWN:
                gp.setObstacle1( racketObstacle1 + RACKET_SPEED * deltaT);
                if (racketObstacle1 + RACKET_SIZE > height) gp.setObstacle1(height - RACKET_SIZE);
                break;
            }
            
            switch(obstacle2.getState()) {
            case GOING_UP:
                gp.setObstacle2( racketObstacle2 - RACKET_SPEED * deltaT);
                if (racketObstacle2 < 0.0) gp.setObstacle2(0.0);
                break;
            	case IDLE:
            	case GOING_DOWN:
                gp.setObstacle2( racketObstacle2 + RACKET_SPEED * deltaT);
                if (racketObstacle2 + RACKET_SIZE > height) gp.setObstacle2(height - RACKET_SIZE);
                break;
            }
          	*/
        }
    }
    /*
    public void movement() {
    	((Object) new Timer()).shedule(){
    		public void run() {
    			
    		}
    	}
    }
    */

    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {

        var racketA = gp.getRacketA();
        var racketB = gp.getRacketB();
        var obstacle1 = gp.getObstacle1();
        var obstacle2 = gp.getObstacle2();
        
        var ballX = gp.getBallX();
        var ballY = gp.getBallY();

        var ballSpeedX = gp.getBallSpeedX();
        var ballSpeedY = gp.getBallSpeedY();

        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;

        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > height) {
            gp.setBallSpeedY(-ballSpeedY);
            se.playSoundEffect(0);
            nextBallY = ballY + deltaT * gp.getBallSpeedY();
        }

        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + RACKET_SIZE)
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + RACKET_SIZE)) {
            gp.setBallSpeedX(-ballSpeedX);
            nextBallX = ballX + deltaT * gp.getBallSpeedX();
            se.playSoundEffect(1);
        } else if (nextBallX < 0) {
            scoreR++;
            se.playSoundEffect(3);
            return true;
        } else if (nextBallX > width) {
            scoreL++;
            se.playSoundEffect(3);
            return true;
        }

        gp.setBallX(nextBallX);
        gp.setBallY(nextBallY);

        return false;
    }

    public double getBallRadius() {
        return BALL_RADIUS;
    }
}
