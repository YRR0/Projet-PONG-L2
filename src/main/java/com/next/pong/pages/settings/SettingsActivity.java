package com.next.pong.pages.settings;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class SettingsActivity extends Activity<SettingsLayout> {

    public SettingsActivity() {
        super(new SettingsLayout());

        Node backButton = findElementById("backToMenu");
        backButton.setOnMouseClicked(event -> Window.goTo(new HomeActivity()));

        Button player1Up = (Button) findElementById("btnUp1");
        Button player1Down = (Button) findElementById("btnDown1");
        Button player2Up = (Button) findElementById("btnUp2");
        Button player2Down = (Button) findElementById("btnDown2");
        Slider ballSpeedX  = (Slider) findElementById("ballSpeedX");
        Slider ballSpeedY  = (Slider) findElementById("ballSpeedY");
        Slider timer  = (Slider) findElementById("timer");
        styleSlider(ballSpeedX);
        styleSlider(ballSpeedY);
        styleSlider(timer);

    }

    private void styleSlider(Slider slider) {
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMinorTickCount(9);
        slider.setMajorTickUnit(10);
        slider.setBlockIncrement(1);
    }

}
