package com.next.pong.framework.activity;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Scene;

public abstract class Activity<T extends Layout> {

    private final ActivityPayload payload;
    private final T layout;
    private final Scene scene;

    protected Activity(T layout) {
        this(layout, null);
    }

    protected Activity(T layout, ActivityPayload payload) {
        this.layout = layout;
        this.payload = payload;
        this.scene = new Scene(layout);
    }

    public Scene getScene() {
        return scene;
    }

    public T getLayout() {
        return layout;
    }

    public ActivityPayload getPayload() {
        return payload;
    }

    public void onUpdate(double deltaTime) {
        layout.onUpdate(deltaTime);
    }

    public void onStop() {
        layout.onStop();
    }

}
