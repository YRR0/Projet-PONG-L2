package com.next.pong.game.state;

import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;

public class Court {

    public static final double RACKET_SPEED = 300.0; // m/s
    public static final double RACKET_SIZE = 100.0; // m
    public static final double BALL_RADIUS = 10.0; // m

    // instance parameters
    private final RacketController playerA, playerB;
    private final double width, height; // m
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

    public GameParameters getGP() {
        return gp;
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
            System.out.println("Le score est " + score1 + ":" + score2);
            this.gp = new GameParameters(height, width);
            if (playerB instanceof AIPlayer) {
                ((AIPlayer) this.playerB).reset(gp);
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
            nextBallY = ballY + deltaT * ballSpeedY;
        }

        if ((nextBallX < 0 && nextBallY > racketA && nextBallY < racketA + RACKET_SIZE + BALL_RADIUS / 2)
                || (nextBallX > width && nextBallY > racketB && nextBallY < racketB + RACKET_SIZE + BALL_RADIUS / 2)) {
            gp.setBallSpeedX(-ballSpeedX);
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0) {
            score1++;
            return true;
        } else if (nextBallX > width) {
            score2++;
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
