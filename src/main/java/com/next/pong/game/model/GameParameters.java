package com.next.pong.game.model;

public class GameParameters {

    // instance state
    double racketA; // m
    double racketB; // m
    double ballX, ballY; // m
    double ballSpeedX, ballSpeedY; // m
    double height, width;

    public GameParameters(double height, double width) {
        this.height = height;
        this.width = width;
        this.racketA = height / 2;
        this.racketB = height / 2;
        this.ballSpeedX = 200.0;
        this.ballSpeedY = 200.0;
        this.ballX = width / 2;
        this.ballY = height / 2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public double getRacketA() {
        return racketA;
    }

    public double getRacketB() {
        return racketB;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public double getballSpeedX() {
        return ballSpeedX;
    }

    public double getballSpeedY() {
        return ballSpeedY;
    }

}