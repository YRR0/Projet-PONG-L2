package com.next.pong.game.player;

import com.next.pong.game.physics.Collision;
import com.next.pong.game.physics.Kinematic;
import com.next.pong.utils.Vector2;

public abstract class Player {

    private static int instances = 0;

    private final int id;
    private Vector2 position;
    private Vector2 speed;
    private Vector2 size;

    public Player(Vector2 position, Vector2 speed, Vector2 size) {
        instances++;
        id = instances;

        this.position = position;
        this.speed = speed;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void integratePosition(double deltaTime) {
        position = Kinematic.position(deltaTime, position, speed);
    }

    public Collision.Boundary getBoundary() {
        return new Collision.Boundary(position, size);
    }

}
