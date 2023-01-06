package com.next.pong.pages.settings;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;

public class SettingsActivity extends Activity<SettingsLayout> {

    private static KeyCode up1 = KeyCode.Z, up2 = KeyCode.UP, down1 = KeyCode.S, down2 = KeyCode.DOWN;
    private static int timerMinutes = 0, timerSeconds = 0, scoreToWin = 5;
    private static double ballSpeedX = 20, ballSpeedY = 30;

    private Button backButton = (Button) findElementById("back");
    private Button player1Up = (Button) findElementById("btnUp1");
    private Button player1Down = (Button) findElementById("btnDown1");
    private Button player2Up = (Button) findElementById("btnUp2");
    private Button player2Down = (Button) findElementById("btnDown2");
    private Slider ballSpeedXS  = (Slider) findElementById("ballSpeedX"), ballSpeedYS = (Slider) findElementById("ballSpeedY");
    private Slider timerM  = (Slider) findElementById("timerMinutes"), timerS = (Slider) findElementById("timerSeconds");
    private Slider score   = (Slider) findElementById("score");

    public SettingsActivity() {
        super(new SettingsLayout());

        ballSpeedXS.setValue(20);
        ballSpeedYS.setValue(30);

        styleSlider(ballSpeedXS, 10, 100, 7);
        styleSlider(ballSpeedYS, 10, 100, 7);
        styleSlider(timerM, 0, 10, 1);
        styleSlider(timerS, 0, 59, 1);
        styleSlider(score, 5, 20, 1);

        backButton.setOnMouseClicked(mouseEvent -> Window.goTo(new HomeActivity()));

        player1Up.setOnMouseClicked( mouseEvent -> {
            hideComponent();
            layout.waitInput();
            setOnKeyPressed( keyEvent -> {
                up1 = keyEvent.getCode();
                player1Up.setText(up1.toString());
                layout.clearInput();
                showComponent();
            });
        });

        player1Down.setOnMouseClicked( mouseEvent -> {
            hideComponent();
            layout.waitInput();
            setOnKeyPressed( keyEvent -> {
                down1 = keyEvent.getCode();
                player1Down.setText(down1.toString());
                layout.clearInput();
                showComponent();
            });
        });

        player2Up.setOnMouseClicked(mouseEvent -> {
            hideComponent();
            layout.waitInput();
            setOnKeyPressed( keyEvent -> {
                up2 = keyEvent.getCode();
                player2Up.setText(up2.toString());
                layout.clearInput();
                showComponent();
            });
        });

        player2Down.setOnMouseClicked( mouseEvent -> {
            hideComponent();
            layout.waitInput();
            setOnKeyPressed( keyEvent -> {
                down2 = keyEvent.getCode();
                player2Down.setText(down2.toString());
                layout.clearInput();
                showComponent();
            });
        });

        timerS.setOnMouseReleased(event -> {
            timerSeconds = (int) timerS.getValue();
            if(timerSeconds > 0) {
                scoreToWin = -1;
                score.setOpacity(0);
            }
            else if(timerMinutes == 0 && timerSeconds == 0) score.setOpacity(1);
        });

        timerM.setOnMouseReleased(event -> {
            timerMinutes = (int) timerM.getValue();
            if(timerMinutes > 0) {
                score.setOpacity(0);
                scoreToWin = -1;
            }
            else if(timerMinutes == 0 && timerSeconds == 0) score.setOpacity(1);
        });

        ballSpeedXS.setOnMouseReleased(event -> {
            ballSpeedX = (int) ballSpeedXS.getValue();
        });

        ballSpeedYS.setOnMouseReleased(event -> {
            ballSpeedY = (int) ballSpeedYS.getValue();
        });

        score.setOnMouseReleased(event -> {
            timerS.setValue(0);
            timerM.setValue(0);
            timerSeconds = 0;
            timerMinutes = 0;
            scoreToWin = (int) score.getValue();
        });

    }

    private void styleSlider(Slider slider, int min, int max, int inc) {
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMin(min);
        slider.setMax(max);
        slider.setMinorTickCount(1);
        slider.setMajorTickUnit(1);
        slider.setBlockIncrement(inc);
    }

    private void hideComponent() {
        backButton.setOpacity(0.2);
        ballSpeedXS.setOpacity(0.2);
        ballSpeedYS.setOpacity(0.2);
        player1Up.setOpacity(0.2);
        player2Up.setOpacity(0.2);
        player1Down.setOpacity(0.2);
        player2Down.setOpacity(0.2);
        timerM.setOpacity(0.2);
        timerS.setOpacity(0.2);
    }

    private void showComponent() {
        backButton.setOpacity(1);
        ballSpeedXS.setOpacity(1);
        ballSpeedYS.setOpacity(1);
        player1Up.setOpacity(1);
        player2Up.setOpacity(1);
        player1Down.setOpacity(1);
        player2Down.setOpacity(1);
        timerM.setOpacity(1);
        timerS.setOpacity(1);
    }

    public static KeyCode up1() {
        return up1;
    }

    public static KeyCode up2() {
        return up2;
    }

    public static KeyCode down1() {
        return down1;
    }

    public static KeyCode down2() {
        return down2;
    }

    public static double ballSpeedX() {
        return ballSpeedX;
    }

    public static double ballSpeedY() {
        return ballSpeedY;
    }

    public static boolean timerSet() {
        if(timerMinutes == 0 && timerSeconds == 0) return false;
        return true;
    }

    public static int timerMinutes() {
        return timerMinutes;
    }

    public static int timerSeconds() {
        return timerSeconds;
    }

    public static int scoreToWin() {
        return scoreToWin;
    }

}
