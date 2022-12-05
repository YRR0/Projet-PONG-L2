package com.next.pong.framework.activity;

import com.next.pong.framework.layout.Layout;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public abstract class Activity<T extends Layout> extends Scene {

    private record KeyEvent(KeyCode keyCode, KeyEventListener listener) {
    }

    public interface KeyEventListener {
        void onPressed();

        void onReleased();
    }

    protected final T layout;

    private final List<KeyEvent> listeners;

    protected Activity(T layout) {
        super(layout);
        this.layout = layout;
        this.listeners = new ArrayList<>();

        setOnKeyPressed(event -> {
            var registered = listeners.stream()
                    .filter(registeredEvent -> event.getCode() == registeredEvent.keyCode);
            registered.forEach(l -> l.listener.onPressed());
        });

        setOnKeyReleased(event -> {
            var registered = listeners.stream()
                    .filter(registeredEvent -> event.getCode() == registeredEvent.keyCode);
            registered.forEach(l -> l.listener.onReleased());
        });

    }

    public void addKeyEventListener(KeyCode keyCode, KeyEventListener listener) {
        listeners.add(new KeyEvent(keyCode, listener));
    }

    public Node findElementById(String id) {
        return lookup('#' + id);
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
