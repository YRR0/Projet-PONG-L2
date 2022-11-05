package com.next.pong;

import com.next.pong.framework.window.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Window.init(primaryStage);
        Image icon = new Image("com.next.pong/img/pong.png");
        primaryStage.setTitle("Pong Game");
        primaryStage.getIcons().add(icon);
        Window.startLoop();
    }
}
