Interface AIInterface {

    // Abstract methods for the AI controller

    // Move the AI controller up 
    void moveUp(double dt);

    // Move the AI controller down
    void moveDown(double dt);

    // Make the decision either to move up or to move down
    void UpOrDown();
}