package com.next.pong.game.state;

import com.next.pong.Sound;
import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;

public class Court {

    public static final double RACKET_SPEED = 600.0; // m/s
    public static final double RACKET_SIZE = 100.0; // m
    public static final double BALL_RADIUS = 10.0; // m

    // instance parameters
    private final RacketController playerA, playerB;
    private final double width, height; // m
    private int scoreL = 0, scoreR = 0;

    private GameParameters gp;
    private Sound se = new Sound();

    public Court(RacketController playerA, RacketController playerB, GameParameters gp) {
        this.playerA = playerA;
        this.playerB = playerB;
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

    public double getHeight() {
        return height;
    }

    public GameParameters getGP() {
        return gp;
    }

    public int getScoreL() {
        return scoreL;
    }

    public int getScoreR() {
        return scoreR;
    }

    public void setGameParameters(GameParameters gp) {
        this.gp = gp;
    }

    public void update(double deltaT) {

        var racketA = gp.getRacketA();
        var racketB = gp.getRacketB();

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

        if (updateBall(deltaT)) {
            this.gp = new GameParameters(height, width);
            if (playerB instanceof AIPlayer) {
                ((AIPlayer) this.playerB).reset(gp, ((AIPlayer) playerB).getLevel());
            }

        }
    }

    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {

        var racketA = gp.getRacketA();
        var racketB = gp.getRacketB();

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
