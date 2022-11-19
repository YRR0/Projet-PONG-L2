		package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import com.next.pong.game.state.Court;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class GameLayout extends Layout {

    private final Court court;
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels
    // children of the game main node
    private final Rectangle racketA, racketB;
    private final Circle ball;
    private final Text text;

    public  Button reprendre;
    public  Button recommencer;
    public  Button acceuil;
    public  Button options;
    public  Button quitter;

    public GameLayout(Court court, double scale) {
        this.scale = scale;
        this.court = court;

        setMinWidth((int)(court.getWidth() * scale + 2 * xMargin));
        setMinHeight((int)(court.getHeight() * scale));
        
        // Configuration des buttons de pause;
        this.buttonConfig();
        this.buttonStyle();
        
        racketA = new Rectangle();
        racketA.setHeight(Court.RACKET_SIZE * scale);
        racketA.setWidth(racketThickness);
        racketA.setFill(Color.BLACK);

        racketA.setX(xMargin - racketThickness);
        racketA.setY(court.getGP().getRacketA() * scale);

        racketB = new Rectangle();
        racketB.setHeight(Court.RACKET_SIZE * scale);
        racketB.setWidth(racketThickness);
        racketB.setFill(Color.BLACK);

        racketB.setX(court.getWidth() * scale + xMargin);
        racketB.setY(court.getGP().getRacketB() * scale);

        ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getGP().getBallX() * scale + xMargin);
        ball.setCenterY(court.getGP().getBallY() * scale);

        text = new Text();
        text.setX(court.getWidth() / 2);
        text.setY(70);
        text.setFont(Font.font("Times new Roman", FontWeight.BOLD, 50));
        
        this.animationBlock();
        //add the nodes into the layout
        addElements(racketA, racketB, ball,sun,c2, text,reprendre,recommencer,acceuil,quitter,options);
    }
    
    
    
    public void pauseOpacity() {
    	racketA.setOpacity(0.1);
    	racketB.setOpacity(0.1);
    	text.setOpacity(0.1);
    	ball.setOpacity(0.1);
    }
    public void restoreOpa() {
    	racketA.setOpacity(0.9);
    	racketB.setOpacity(0.9);
    	text.setOpacity(0.9);
    	ball.setOpacity(0.9);
    }
    
    Circle sun;
    Circle c2;
    
    public void animationBlock() {
    	// Create a sun transition
		//Creation of the sun using the circle shape
		 sun = new Circle();
		 c2 = new Circle();
		 sun.setOpacity(0.5);
	    	c2.setOpacity(0.5);
		c2.setRadius(50);
		c2.setFill(Color.BLACK);
		//c2.setOpacity(0.5);
		sun.setRadius(50);
		sun.setFill(Color.CADETBLUE);
		
		//Set the sun position
		//sun.setCenterX(width/2);
		//sun.setCenterY(height/2);
		//c2.setCenterX(width/2);
		//c2.setCenterY(height/2);
		
		sun.setLayoutX(court.getWidth()/2-(court.getWidth()/4));
		sun.setLayoutY(court.getHeight());
		
		c2.setLayoutX(court.getWidth()/2+(court.getWidth()/4)+100);
		
		// Create a translate transition
		TranslateTransition movement = new TranslateTransition();
		TranslateTransition movement2 = new TranslateTransition();
		
		//Setting the duration of the transition
		movement.setDuration(Duration.millis(2000));
		movement2.setDuration(Duration.millis(2000));
		
		//Set node for the transition
		movement.setNode(sun);
		movement2.setNode(c2);
		
		// Set the value of the transition along the Y axis
		movement.setByY(-court.getHeight());
		//movement.setByX(width);
		movement2.setByY(court.getHeight());
		//movement2.setByX(-width);
		
		
		// Set cycle count for transition
		movement.setCycleCount(100);
		movement2.setCycleCount(100);
		
		//Set reverse value to true
		movement.setAutoReverse(true);
		movement2.setAutoReverse(true);
		
		//Play animation
		movement.play();
		movement2.play();
    }
    
    
    public void buttonConfig(){
        reprendre = new Button("Reprendre");
        recommencer = new Button("Recommencer");
        acceuil = new Button("Acceuil");
        quitter = new Button("Quitter");
        options = new Button("Options");
        
        reprendre.setTranslateX(court.getWidth()/2);
        reprendre.setTranslateY(court.getHeight()/2 - 100);
        reprendre.setVisible(false);
        
        recommencer.setTranslateX(court.getWidth()/2 - 15);
        recommencer.setTranslateY(court.getHeight()/2 - 50 );
        recommencer.setVisible(false);
        
        options.setTranslateX(court.getWidth()/2 + 15);
        options.setTranslateY(court.getHeight()/2);
        options.setVisible(false);
        
        acceuil.setTranslateX(court.getWidth()/2 + 15);
        acceuil.setTranslateY(court.getHeight()/2 + 50);
        acceuil.setVisible(false);
        
        quitter.setTranslateX(court.getWidth()/2 + 15);
        quitter.setTranslateY(court.getHeight()/2 + 100);
        quitter.setVisible(false);
    }
    
    public void buttonStyle() {
    		String fond = "-fx-background-color:#001253; ";
    		String style = "-fx-background-radius:20 20 20 20;"+
    		"-fx-font-family: \"Cambria\";"+
    		/*-fx-text-color:#FAF7F0;*/
    		"-fx-text-fill:#f8f8ff;"+
    		"-fx-background-color:#4169e1;"+
    		"-fx-font-size:20px;"+
    		"-fx-effect: dropshadow( one-pass-box , black , 10 , 0.0 , 5 , 0 );";
    		
    		String styleLab = "-fx-alignment: center;"
    						  + "-fx-font-size:40px;"+
    						  "-fx-text-fill:#f8f8ff";
    		// assign css properties for the button
    		reprendre.setStyle(style);
    		recommencer.setStyle(style);
    		acceuil.setStyle(style);
    		options.setStyle(style);
    		quitter.setStyle(style);
    }
    
    public void buttonConfigPause(){
    	 reprendre.setVisible(true);
    	 recommencer.setVisible(true);
    	 acceuil.setVisible(true);
    	 options.setVisible(true);
    	 quitter.setVisible(true);
    	 
    	 sun.setVisible(false);
    	 c2.setVisible(false);
    }
    public void buttonConfigPauseStop(){
   	 reprendre.setVisible(false);
   	 recommencer.setVisible(false);
   	 acceuil.setVisible(false);
   	 options.setVisible(false);
   	 quitter.setVisible(false);
     
   	sun.setVisible(true);
	c2.setVisible(true);
   }

    @Override
    public void onUpdate(double deltaTime){
        court.update(deltaTime);
        racketA.setY(court.getGP().getRacketA() * scale);
        racketB.setY(court.getGP().getRacketB() * scale);
        ball.setCenterX(court.getGP().getBallX() * scale + xMargin);
        ball.setCenterY(court.getGP().getBallY() * scale);
        text.setText(court.getScoreL() + " : " + court.getScoreR());
    }

    @Override
    public boolean onStop() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" Quitter");
        alert.setHeaderText(" Vous allez quitter ...... ");
        alert.setContentText(" Nous espérons vous revoir bientot");
        return (alert.showAndWait().get() == ButtonType.OK);
    }

    public Court getCourt(){
        return court;
    }

}
