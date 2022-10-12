package com.next.pong.framework.activity;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Scene;

public abstract class Activity {

    private final ActivityPayload payload;
    private final Layout layout;
    private final Scene scene;

    protected Activity(Layout layout) {
        this(layout, null);
    }

    protected Activity(Layout layout, ActivityPayload payload) {
        this.layout = layout;
        this.payload = payload;
        this.scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }

    public ActivityPayload getPayload() {
        return payload;
    }

    public void onUpdate(float deltaTime) {
        layout.onUpdate(deltaTime);
    }

    public void onStop() {
        layout.onStop();
    }

}
