package com.next.pong.utils;

public class MathUtils {

    public static double clamp(double min, double value, double max) {
        return Math.max(min, Math.min(value, max));
    }

}
