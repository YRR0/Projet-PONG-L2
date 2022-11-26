package com.next.pong.game.physics;

import com.next.pong.utils.Vector2;

public class Kinematic {

    public static Vector2 integrate(double deltaTime, Vector2 initialCondition, Vector2 derivative) {
        return initialCondition.add(derivative.scalar(deltaTime));
    }

}
