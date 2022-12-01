package com.next.pong.framework.window;

import com.next.pong.framework.activity.Activity;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.utils.TimeUtils;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Window extends Navigation {

    public static void init(Stage stage) {
        stage.setResizable(false);

        var startActivity = new HomeActivity();

        setOnActivityChangeListener((oldActivity, newActivity) -> {
            stage.setScene(newActivity);
            stage.show();
        });

        goTo(startActivity);
    }

    public static void startLoop() {
        new AnimationTimer() {

            private long previousTimeNs = 0;

            @Override
            public void handle(long timeNs) {

                if (previousTimeNs == 0) {
                    previousTimeNs = timeNs;
                }

                var deltaTime = TimeUtils.nsToSec(timeNs - previousTimeNs);
                var fps = (int) (1.0 / deltaTime);

                previousTimeNs = timeNs;

                long navUpdateTime = System.currentTimeMillis();
                update(deltaTime);
                navUpdateTime = System.currentTimeMillis() - navUpdateTime;

                /*System.out.println("Frame: " + (deltaTime * 1000) + "ms | " + fps + "fps");
                System.out.println("Work: " + navUpdateTime + "ms");
                System.out.println("------------------------------");*/
            }

        }.start();
    }

    public static void goTo(Activity activity) {
        Navigation.goTo(activity);
    }
}
