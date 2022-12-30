package com.next.pong.pages.settings;


// package 
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;
import javafx.scene.Node;


public class SettingsActivity extends Activity<SettingsLayout> {

    public SettingsActivity() {
        super(new SettingsLayout());

        // Node backButton = findElementById("backToMenu");
        // backButton.setOnMouseClicked(event -> Window.goTo(new HomeActivity()));
    }

}
