package com.next.pong.pages.settings;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import com.next.pong.framework.layout.Layout;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.home.HomeActivity;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import com.next.pong.pages.game.GameParameters;


public class SettingsLayout extends Layout {

    private double ballx = 20.3;
    private double bally = 20.3;

    private Label labelBallXSpd;
    private Label titleBallXSpd = new Label("Ball x speed");

    private Label labelBallYSpd;
    private Label titleBallYSpd = new Label("Ball y speed");

    private static final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    public final int DEFAULT_WIDTH = (int) screenBounds.getWidth();
    public final int DEFAULT_HEIGHT = (int) screenBounds.getHeight();

    public SettingsLayout() {
        super();

        this.ballx = GameParameters.ballSpeedX;
        this.bally = GameParameters.ballSpeedY;
        
        
        Pane ap = new Pane();
        ap.setId("settings");

        ap.setPrefHeight(this.DEFAULT_HEIGHT);
        ap.setPrefWidth(this.DEFAULT_WIDTH);

        String s = this.getClass().getClassLoader().getResource("com/next/pong/css/settings.css").toExternalForm();
        ap.getStylesheets().add(s);

        HBox ballXSpdContainer = this.createBallXSpdContainer();   
        HBox ballYSpdContainer = this.createBallYSpdContainer();   
        HBox home = this.createBackButtonContainer();
                
        ap.getChildren().add(home);
        ap.getChildren().add(ballXSpdContainer);
        ap.getChildren().add(ballYSpdContainer);

        addElements(ap);
    }


    private HBox createBallXSpdContainer() {
        HBox ballXSpdContainer = new HBox();

        Button inc = this.createXIncBtn();
        Button dec = this.createXDecBtn();
        this.labelBallXSpd = this.createLabelBallXSpd();


        HBox.setMargin(titleBallXSpd, new Insets(14.0, 20.0, 0.0, 0.0));
        HBox.setMargin(labelBallXSpd, new Insets(14.0, 20.0, 0.0, 0.0));


        ballXSpdContainer.setLayoutX(316.0);
        ballXSpdContainer.setLayoutY(50.0);

        // Adding paddings and positions to inner elements
        this.labelBallXSpd.setPadding(new Insets(4.0, 0.0, 0.0, 0.0));
        titleBallXSpd.setPadding(new Insets(4.0, 0.0, 0.0, 0.0));



        ballXSpdContainer.getChildren().add(titleBallXSpd);
        ballXSpdContainer.getChildren().add(this.labelBallXSpd);

        ballXSpdContainer.getChildren().add(dec);
        ballXSpdContainer.getChildren().add(inc);


        return ballXSpdContainer;
    }


    private HBox createBallYSpdContainer() {
        HBox ballYSpdContainer = new HBox();

        Button inc = this.createYIncBtn();
        Button dec = this.createYDecBtn();
        this.labelBallYSpd = this.createLabelBallYSpd();


        HBox.setMargin(titleBallYSpd, new Insets(14.0, 20.0, 0.0, 0.0));
        HBox.setMargin(labelBallYSpd, new Insets(14.0, 20.0, 0.0, 0.0));


        ballYSpdContainer.setLayoutX(316.0);
        ballYSpdContainer.setLayoutY(130.0);

        // Adding paddings and positions to inner elements
        this.labelBallYSpd.setPadding(new Insets(4.0, 0.0, 0.0, 0.0));
        titleBallYSpd.setPadding(new Insets(4.0, 0.0, 0.0, 0.0));



        ballYSpdContainer.getChildren().add(titleBallYSpd);
        ballYSpdContainer.getChildren().add(this.labelBallYSpd);

        ballYSpdContainer.getChildren().add(dec);
        ballYSpdContainer.getChildren().add(inc);


        return ballYSpdContainer;
    }

    private Button createXIncBtn() {
        Button inc = new Button("+");
        inc.setId("bye");
        this.applyButtonStyle(inc);
        inc.setOnMouseClicked(event -> {
            this.ballx = Math.round((this.ballx + 0.05) * 100.0) / 100.0;
            GameParameters.ballSpeedX = ballx;
        });
        
        return inc;
    }
    
    private Button createXDecBtn() {
        Button dec = new Button("-");
        this.applyButtonStyle(dec);
        dec.setOnMouseClicked(event -> {
            if (this.ballx - 0.05 >= 0.05) {
                this.ballx = Math.round((this.ballx - 0.05) * 100.0) / 100.0;
                GameParameters.ballSpeedX = this.ballx;
            }
        });

        return dec;
    }

    private Button createYIncBtn() {
        Button inc = new Button("+");
        this.applyButtonStyle(inc);
        inc.setOnMouseClicked(event -> {
            this.bally = Math.round((this.bally + 0.05) * 100.0) / 100.0;
            GameParameters.ballSpeedY = this.bally;
        });
        
        return inc;
    }
    
    private Button createYDecBtn() {
        Button dec = new Button("-");
        this.applyButtonStyle(dec);
        dec.setOnMouseClicked(event -> {
            if (this.bally - 0.05 >= 0.05) {
                this.bally = Math.round((this.bally - 0.05) * 100.0) / 100.0;
                GameParameters.ballSpeedY = this.bally;
            }
        });

        return dec;
    }

    private Label createLabelBallXSpd() {
        Label l = new Label(String.valueOf(this.ballx));
        l.setPrefWidth(100.0);
        return l;
    }


    private Label createLabelBallYSpd() {
        Label l = new Label(String.valueOf(this.bally));
        l.setPrefWidth(100.0);
        return l;
    }
    

    private HBox createBackButtonContainer() {
        HBox home = new HBox();

        Button back = new Button("Back");
        this.applyButtonStyle(back);

        home.setMaxHeight(40.0);
        home.setMaxWidth(300.0);

        home.setAlignment(Pos.TOP_LEFT);
        home.setLayoutX(316.0);
        home.setLayoutY(400.0);
        home.getChildren().add(back);

        back.setOnMouseClicked(event -> {
            Window.goTo(new HomeActivity());
        });

        return home;
    }

    private void applyButtonStyle(Button btn) {
        btn.setStyle("-fx-background-color: black;");
        btn.setTextFill(Color.WHITE);
    }
 
    @Override
    public void onUpdate(double deltaTime) {
        // Value becomes less than zero, when the ball bounces from a surface
        this.labelBallXSpd.setText(Double.toString(this.ballx));
        this.labelBallYSpd.setText(Double.toString(this.bally));
    }
}