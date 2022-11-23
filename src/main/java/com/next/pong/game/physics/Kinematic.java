package com.next.pong.game.physics;

import com.next.pong.utils.Vector2;

public class Kinematic {

    public static Vector2 position(double deltaTime, Vector2 initialPosition, Vector2 speed) {
        return initialPosition.add(speed.scalar(deltaTime));
    }

}
