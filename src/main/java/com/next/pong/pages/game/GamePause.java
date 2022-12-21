package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GamePause extends Pane {
    public Button reprendre;
    public Button recommencer;
    public Button acceuil;
    public Button options;
    public Button quitter;

    public GamePause() {
        reprendre = new Button("Resume");
        reprendre.setId("reprendre");
        recommencer = new Button("Restart");
        recommencer.setId("recommencer");
        acceuil = new Button("Home");
        acceuil.setId("accueil");
        quitter = new Button("Exit");
        quitter.setId("quitter");
        options = new Button("Options");
        options.setId("options");

        int width = Layout.DEFAULT_WIDTH;
        int height = Layout.DEFAULT_HEIGHT;

        reprendre.setTranslateX(0.15 * width);
        reprendre.setTranslateY(0.15 * height);
        reprendre.setPrefWidth(0.225 * width);
        reprendre.setPrefHeight(0.725 * height);
        reprendre.setVisible(false);

        recommencer.setTranslateX(0.4 * width);
        recommencer.setTranslateY(0.15 * height);
        recommencer.setPrefWidth(0.475 * width);
        recommencer.setPrefHeight(0.2 * height);
        recommencer.setVisible(false);

        options.setTranslateX(0.4 * width);
        options.setTranslateY(0.4 * height);
        options.setPrefHeight(0.475 * height);
        options.setPrefWidth(0.2 * width);
        options.setVisible(false);

        acceuil.setTranslateX(0.625 * width);
        acceuil.setTranslateY(0.4 * height);
        acceuil.setPrefWidth(0.25 * width);
        acceuil.setPrefHeight(0.225 * height);
        acceuil.setVisible(false);

        quitter.setTranslateX(0.625 * width);
        quitter.setTranslateY(0.65 * height);
        quitter.setPrefHeight(0.225 * height);
        quitter.setPrefWidth(0.25 * width);
        quitter.setVisible(false);

        this.getChildren().addAll(reprendre, recommencer, acceuil, options, quitter);
    }

    public void buttonStyle() {
        // assign css properties for the button
        reprendre.getStyleClass().add("btn");
        recommencer.getStyleClass().add("btn");
        acceuil.getStyleClass().add("btn");
        options.getStyleClass().add("btn");
        quitter.getStyleClass().add("btn");
    }

    public void buttonConfigPause() {
        reprendre.setVisible(true);
        recommencer.setVisible(true);
        acceuil.setVisible(true);
        options.setVisible(true);
        quitter.setVisible(true);
    }

    public void buttonConfigPauseStop() {
        reprendre.setVisible(false);
        recommencer.setVisible(false);
        acceuil.setVisible(false);
        options.setVisible(false);
        quitter.setVisible(false);
    }

}
