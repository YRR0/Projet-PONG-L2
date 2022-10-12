package com.next.pong.framework.window;

import com.next.pong.framework.activity.Activity;
import com.next.pong.pages.menu.MenuActivity;
import com.next.pong.utils.TimeUtils;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Window extends Navigation {

    public static void init(Stage stage) {
        var startActivity = new MenuActivity();

        setOnActivityChangeListener((oldActivity, newActivity) -> {
            stage.setScene(newActivity.getScene());
            stage.show();
        });

        goTo(startActivity);
    }

    public static void startLoop() {
        new AnimationTimer() {

            private long previousTimeNs = 0;

            @Override
            public void handle(long timeNs) {

                if(previousTimeNs == 0) {
                    previousTimeNs = timeNs;
                }

                var deltaMs = (float) TimeUtils.nsToMs(timeNs - previousTimeNs);
                var fps = (int)(1000 / deltaMs);

                previousTimeNs = timeNs;

                long navUpdateTime = System.currentTimeMillis();
                update(deltaMs);
                navUpdateTime = System.currentTimeMillis() - navUpdateTime;

                System.out.println("Frame: " + deltaMs + "ms | " + fps + "fps");
                System.out.println("Work: " + navUpdateTime + "ms");
                System.out.println("------------------------------");
            }

        }.start();
    }

    public static void goTo(Activity activity) {
        Navigation.goTo(activity);
    }

}
