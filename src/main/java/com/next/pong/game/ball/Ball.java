package com.next.pong.game.ball;

import com.next.pong.game.physics.Collision;
import com.next.pong.game.physics.Kinematic;
import com.next.pong.utils.Vector2;

public class Ball {

    private final double radius;
    private Vector2 position;
    private Vector2 speed;

    public Ball(Vector2 position, Vector2 speed, double radius) {
        this.radius = radius;
        this.position = position;
        this.speed = speed;
    }

    public double getRadius() {
        return radius;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void flipSpeedX() {
        speed = new Vector2(-speed.x(), speed.y());
    }

    public void flipSpeedY() {
        speed = new Vector2(speed.x(), -speed.y());
    }

    public void integratePosition(double deltaTime) {
        position = Kinematic.position(deltaTime, position, speed);
    }

    public Collision.Boundary getBoundary() {
        return new Collision.Boundary(position, new Vector2(radius, radius));
    }

}
