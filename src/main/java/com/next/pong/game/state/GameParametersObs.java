package com.next.pong.game.state;

public class GameParametersObs {

    // instance state
    private double racketA; // m
    private double racketB; // m
    private double obstacle1; 
    private double obstacle2;
    private double ballX, ballY; // m
    private double ballSpeedX, ballSpeedY; // m
    private double height, width;

    public GameParametersObs(double height, double width) {
        this.height = height;
        this.width = width;
        this.racketA = height / 2;
        this.racketB = height / 2;
        this.obstacle1 = height-10 / 2; //AJ 
        this.obstacle2 = height-10 / 2; //AJ
        this.ballSpeedX = 400.0;
        this.ballSpeedY = 400.0;
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

    public void setRacketA(double racketA) {
        this.racketA = racketA;
    }

    public double getRacketB() {
        return racketB;
    }

    public void setRacketB(double racketB) {
        this.racketB = racketB;
    }

    public double getBallX() {
        return ballX;
    }

    public void setBallX(double ballX) {
        this.ballX = ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public void setBallY(double ballY) {
        this.ballY = ballY;
    }

    public double getBallSpeedX() {
        return ballSpeedX;
    }

    public void setBallSpeedX(double ballSpeedX) {
        this.ballSpeedX = ballSpeedX;
    }

    public double getBallSpeedY() {
        return ballSpeedY;
    }

    public void setBallSpeedY(double ballSpeedY) {
        this.ballSpeedY = ballSpeedY;
    }
    
    // Ajout
    public double getObstacle1() {
    	return this.obstacle1;
    }
    public void setObstacle1(double obstacle1) {
    	this.obstacle1 = obstacle1;
    }

    public double getObstacle2() {
    	return this.obstacle2;
    }
    public void setObstacle2(double obstacle2) {
    	this.obstacle2 = obstacle2;
    }
    
    
    
}