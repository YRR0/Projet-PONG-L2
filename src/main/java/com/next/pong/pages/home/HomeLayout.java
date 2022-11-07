package com.next.pong.pages.home;

import com.next.pong.framework.layout.Layout;
import com.sun.javafx.geom.Rectangle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



import java.util.Collection;

import static javafx.scene.paint.Color.LIGHTBLUE;


public abstract class HomeLayout extends Layout {

    public HomeLayout() {
        Pane root = new Pane();
        MenuItem newGame = new MenuItem("New game");
        MenuItem options = new MenuItem("Settings");
        MenuItem exitGame = new MenuItem("Quit");
        SubMenu mainMenu = new SubMenu(newGame, options, exitGame);
        MenuItem sound = new MenuItem("Sound");
        MenuItem video = new MenuItem("Video");
        MenuItem keys = new MenuItem("Control");
        MenuItem optionsBack = new MenuItem("Back");
        SubMenu optionsMenu = new SubMenu(sound, video, keys, optionsBack);
        MenuBox menuBox = new MenuBox(mainMenu);
    }

    public void start(Stage primaryStage) {
        newGame.setOnMouseClicked(event -> menuBox.setSubMenu(newGameMenu));
        options.setOnMouseClicked(event -> menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event -> System.exit(0));
        optionsBack.setOnMouseClicked(event -> menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(menuBox);

        Scene scene = new Scene(root, 900, 600);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1), menuBox);
                if (!menuBox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
                } else {
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> menuBox.setVisible(false));
                    ft.play();

                }
            }
        });
        primaryStage.setTitle("Pause");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

