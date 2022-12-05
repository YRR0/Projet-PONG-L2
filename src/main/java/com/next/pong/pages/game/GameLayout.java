package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;
import com.next.pong.pages.game.elements.Racket;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Random;

public class GameLayout extends Layout {

    private final Text score;
    private Text time;

    private final Racket playerElementA;
    private final Racket playerElementB;

    private final BallElement ballElement;
    
    public  Button reprendre;
    public  Button recommencer;
    public  Button acceuil;
    public  Button options;
    public  Button quitter;
    
    Circle sun;
    Circle c2;
    
    Line line;
    Line line2;
    
    public GameLayout() {
        super();

        score = new Text("0 : 0");
        score.setY(70);
        score.setFill(Color.WHITE);
        score.setId("score");
        addElements(score);

        time = new Text(); // Pour le score
        time.setY(460);
        time.setId("time");
        addElements(time);


        playerElementA = new Racket();
        playerElementB = new Racket();
        addElements(playerElementA, playerElementB);

        ballElement = new BallElement();
        
        // --------------------------------------------------------------------------------------------------------------
        playerElementA.setFill(Color.LIGHTCYAN);
        playerElementB.setFill(Color.LIGHTCYAN);
        
        // Configuration des buttons de pause;
        
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
        
        reprendre.setTranslateX(167.0);
        reprendre.setTranslateY(124.0);
        reprendre.setVisible(false);
        
        recommencer.setTranslateX(362.0);
        recommencer.setTranslateY(124.0);
        recommencer.setVisible(false);
        
        options.setTranslateX(362.0);
        options.setTranslateY(224.0);
        options.setVisible(false);
        
        acceuil.setTranslateX(512.0);
        acceuil.setTranslateY(224.0);
        acceuil.setVisible(false);
        
        quitter.setTranslateX(512.0);
        quitter.setTranslateY(336.0);
        quitter.setVisible(false);
        
     // One line transition
        line = new Line(50 , 0 , Layout.DEFAULT_WIDTH-50, 0);
     	line.setStrokeWidth(10);
     			
     // Seconde line style
     	line2 = new Line(50 , Layout.DEFAULT_HEIGHT, Layout.DEFAULT_WIDTH-50 , Layout.DEFAULT_HEIGHT);
     	line2.setStrokeWidth(10);

        buttonStyle();
        animationBlock();
        addElements(sun,c2,line,line2,reprendre,recommencer,acceuil,quitter,options);
        
        DropShadow ds1 = new DropShadow();
     	ds1.setOffsetY(1.0f);
     	ds1.setOffsetX(1.0f);
     	ds1.setColor(Color.WHITESMOKE);
     	
     	ballElement.setEffect(ds1);
        addElements(ballElement);
        this.setBackground(Color.BLACK);

		addStyleSheet(Resources.Style.FIELD_STYLE);
		line.getStyleClass().add("line");
		line2.getStyleClass().add("line");
    }

    public void setScore(int x, int y) {
        score.setText(x + " : " + y);
        center(score);
    }

    public void setBallProperties(double x, double y, double radius) {
        ballElement.setBallProperties(x, y, radius);
    }

    public void setPlayerElementA(double x, double y, double width, double height) {
        playerElementA.setPlayerProperties(x, y, width, height);
    }

    public void setPlayerElementB(double x, double y, double width, double height) {
        playerElementB.setPlayerProperties(x, y, width, height);
    }

    public static void center(Text text) {
        var width = text.getLayoutBounds().getWidth();
        text.setX(0.5 * DEFAULT_WIDTH - 0.5 * width);
    }

    
    //-------------------------------------------------------------------------------------------


    public void setTime(Text time) {
        this.time.setText(time.getText());
        center(this.time);
    }
    
    public void animationBlock() {
    	// Create a sun transition
		//Creation of the sun using the circle shape
		sun = new Circle();
		c2 = new Circle();
		sun.setOpacity(0.3);
	    c2.setOpacity(0.3);
		c2.setRadius(40);
		c2.setFill(Color.LIGHTGREEN);
		//c2.setOpacity(0.5);
		sun.setRadius(40);
		sun.setFill(Color.CADETBLUE);
		
		//Set the sun position
		sun.setCenterX(Layout.DEFAULT_WIDTH/2-200);
		sun.setCenterY(10);
		c2.setCenterX(Layout.DEFAULT_WIDTH/2+200);
		c2.setCenterY(Layout.DEFAULT_HEIGHT-10);
		
		// Create a translate transition
		TranslateTransition movement = new TranslateTransition();
		TranslateTransition movement2 = new TranslateTransition();
		
		//Setting the duration of the transition
		movement.setDuration(Duration.millis(5000));
		movement2.setDuration(Duration.millis(5000));
		
		//Set node for the transition
		movement.setNode(sun);
		movement2.setNode(c2);
		
		// Set the value of the transition along the Y axis
		movement.setByY(Layout.DEFAULT_HEIGHT);
		//movement.setByX(width);
		movement2.setByY(-Layout.DEFAULT_HEIGHT);
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
    
    public void buttonStyle() {
    		// assign css properties for the button
			addStyleSheet(Resources.Style.BUTTON_STYLE);
			reprendre.getStyleClass().add("btn");
			recommencer.getStyleClass().add("btn");
			acceuil.getStyleClass().add("btn");
			options.getStyleClass().add("btn");
			quitter.getStyleClass().add("btn");
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

    
    public void pauseOpacity() {
    	playerElementA.setOpacity(0.3);
    	playerElementB.setOpacity(0.3);
    	score.setOpacity(1);
    	ballElement.setOpacity(0.3);
    	line.setOpacity(0.3);
    	line2.setOpacity(0.3);
        time.setOpacity(0.3);
    }
    public void restoreOpa() {
    	playerElementA.setOpacity(0.9);
    	playerElementB.setOpacity(0.9);
    	score.setOpacity(0.9);
    	ballElement.setOpacity(0.9);
     	line.setOpacity(0.9);
    	line2.setOpacity(0.9);
        time.setOpacity(0.9);
    }
    
    public static Color foncAlea() {
		Random a = new Random();
		int r = a.nextInt(0,256);
		int g = a.nextInt(0,256);
		int b = a.nextInt(0,256);
		
		return Color.rgb(r,g, b);
	}
}
