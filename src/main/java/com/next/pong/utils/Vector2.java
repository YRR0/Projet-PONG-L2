package com.next.pong.utils;

public record Vector2(double x, double y) {

    public Vector2 add(Vector2 other) {
        return new Vector2(x + other.x, y + other.y);
    }

    public Vector2 scalar(double scalar) {
        return new Vector2(scalar * x, scalar * y);
    }

}
