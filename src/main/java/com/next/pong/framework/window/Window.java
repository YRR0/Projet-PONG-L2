package com.next.pong.framework.window;

import com.next.pong.framework.activity.Activity;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.utils.TimeUtils;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

public class Window extends Navigation {

    private static AnimationTimer at;
    private static boolean restarted;

    public static void init(Stage stage) {
        var startActivity = new HomeActivity();

        setOnActivityChangeListener((oldActivity, newActivity) -> {
            stage.setScene(newActivity.getScene());
            stage.show();
            stage.setOnCloseRequest(ev -> {
                restarted = false;
                ev.consume();
                at.stop();
                if (!newActivity.onStop()){
                    at.start();
                    restarted = true;
                }else{
                    stage.close();
                }
            });
        });

        goTo(startActivity);
    }

    public static void startLoop() {
        at =  new AnimationTimer() {

            private long previousTimeNs = 0;

            @Override
            public void handle(long timeNs) {

                if(previousTimeNs == 0 || restarted) {
                    previousTimeNs = timeNs;
                    restarted = false;
                }

                var deltaMs = TimeUtils.nsToMs(timeNs - previousTimeNs);
                var fps = (int)(1000 / deltaMs);

                previousTimeNs = timeNs;

                long navUpdateTime = System.currentTimeMillis();
                update(deltaMs);
                navUpdateTime = System.currentTimeMillis() - navUpdateTime;

                /*System.out.println("Frame: " + deltaMs + "ms | " + fps + "fps");
                System.out.println("Work: " + navUpdateTime + "ms");
                System.out.println("------------------------------");*/
            }

        };
        at.start();
    }

    public static void goTo(Activity activity) {
        Navigation.goTo(activity);
    }
}
