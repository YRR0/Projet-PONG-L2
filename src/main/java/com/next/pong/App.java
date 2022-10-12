package com.next.pong;

import com.next.pong.framework.window.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Window.init(primaryStage);
        Window.startLoop();
    }

}
