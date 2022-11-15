package com.next.pong.pages.settings;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import javafx.scene.Node;

public class SettingsActivity extends Activity<SettingsLayout> {
    public SettingsActivity() {
        super(new SettingsLayout());
        Node backButton = findElementById("backButton");
        backButton.setOnMouseClicked(event -> Window.goTo(new HomeActivity()));
        Node buttonSpeed = findElementById("buttonSpeed");
        //buttonSpeed.setOnMouseClicked(event -> Window.);
        Node soundButton = findElementById("soundButton");
        //soundButton.setOnMouseClicked(event -> );}
    }
}
