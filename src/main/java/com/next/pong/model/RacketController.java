package com.next.pong.model;

public interface RacketController {
    enum State { GOING_UP, IDLE, GOING_DOWN }
    State getState();
    void setState(State s);
}
