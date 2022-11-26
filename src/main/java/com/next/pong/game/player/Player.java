package com.next.pong.game.player;

import com.next.pong.game.physics.Collision;
import com.next.pong.game.physics.Kinematic;
import com.next.pong.utils.MathUtils;
import com.next.pong.utils.Vector2;

public class Player {

    private static final int INPUT_ACCELERATION = 600;
    private static final double FRICTION_COEFFICIENT = 1;
    private static final int VERTICAL_SPEED = 300;

    private static int instances = 0;

    private final int id;
    private Vector2 position;
    private Vector2 speed;
    private Vector2 inputAcceleration;
    private Vector2 size;

    public Player(Vector2 position, Vector2 speed, Vector2 size) {
        instances++;
        id = instances;

        this.inputAcceleration = new Vector2(0, 0);
        this.position = position;
        this.speed = speed;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void integratePosition(double deltaTime) {
        var netAcceleration = inputAcceleration.add(frictionAcceleration());
        speed = Kinematic.integrate(deltaTime, speed, netAcceleration);
        clampSpeed();
        position = Kinematic.integrate(deltaTime, position, speed);
    }

    private void clampSpeed() {
        speed = new Vector2(speed.x(), MathUtils.clamp(-VERTICAL_SPEED, speed.y(), VERTICAL_SPEED));
    }

    private Vector2 frictionAcceleration() {
        return new Vector2(0.0, -speed.y() * FRICTION_COEFFICIENT);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void applyForceUp() {
        inputAcceleration = new Vector2(0, -INPUT_ACCELERATION);
    }

    public void applyNeutralForce() {
        inputAcceleration = new Vector2(0, 0);
    }

    public void applyForceDown() {
        inputAcceleration = new Vector2(0, INPUT_ACCELERATION);
    }

    public void neutraliseSpeed() {
        speed = new Vector2(0, 0);
    }

    public Collision.Boundary getBoundary() {
        return new Collision.Boundary(position, size);
    }

}
