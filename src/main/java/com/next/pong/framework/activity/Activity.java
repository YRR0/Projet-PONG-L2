package com.next.pong.framework.activity;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Node;
import javafx.scene.Scene;

public abstract class Activity<T extends Layout> {

    protected final T layout;

    private final Scene scene;
    private final ActivityPayload payload;

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

    public Node findElementById(String id) {
        return scene.lookup('#' + id);
    }

    public ActivityPayload getPayload() {
        return payload;
    }

    public final void update(double deltaTime) {
        layout.onUpdate(deltaTime);
        onUpdate(deltaTime);
    }

    public void onUpdate(double deltaTime) {
    }

    public final void destroy() {
        layout.onDestroy();
        onDestroy();
    }

    public void onDestroy() {
    }

}
