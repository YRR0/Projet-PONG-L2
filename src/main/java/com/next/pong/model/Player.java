package com.next.pong.model;

public class Player implements RacketController {
    private State state = State.IDLE;

    @Override
    public State getState() {
        return state;
    }

    public void setState(State value) {
        state = value;
    }
}