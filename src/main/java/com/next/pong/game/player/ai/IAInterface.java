package com.next.pong.game.player.ai;

public interface IAInterface {

    // Abstract methods for the AI controller

    // Move the AI controller up
    void moveUp();

    // Move the AI controller down
    void moveDown();

    // Make the decision either to move up or to move down
    void upOrDown();

}