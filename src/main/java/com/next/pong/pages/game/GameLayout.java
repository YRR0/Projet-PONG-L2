package com.next.pong.pages.game;

import java.util.Random;

import com.next.pong.framework.layout.Layout;
import com.next.pong.pages.game.elements.BallElement;
import com.next.pong.pages.game.elements.PlayerElement;

import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameLayout extends Layout {

    private final Text text;

    private final PlayerElement playerElementA;
    private final PlayerElement playerElementB;

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

        text = new Text("0 : 0");
        text.setY(70);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
        addElements(text);

        playerElementA = new PlayerElement();
        playerElementB = new PlayerElement();
        addElements(playerElementA, playerElementB);

        ballElement = new BallElement();
        
        // --------------------------------------------------------------------------------------------------------------
        playerElementA.setFill(Color.LIGHTCYAN);
        playerElementB.setFill(Color.LIGHTCYAN);
        
        // Configuration des buttons de pause;
        
        reprendre = new Button("Reprendre");
        recommencer = new Button("Recommencer");
        acceuil = new Button("Acceuil");
        quitter = new Button("Quitter");
        options = new Button("Options");
        
        reprendre.setTranslateX(Layout.DEFAULT_WIDTH/2-50);
        reprendre.setTranslateY(Layout.DEFAULT_HEIGHT/2 - 100);
        reprendre.setVisible(false);
        
        recommencer.setTranslateX(Layout.DEFAULT_WIDTH/2 - 65);
        recommencer.setTranslateY(Layout.DEFAULT_HEIGHT/2 - 50 );
        recommencer.setVisible(false);
        
        options.setTranslateX(Layout.DEFAULT_WIDTH/2 -35 );
        options.setTranslateY(Layout.DEFAULT_HEIGHT/2);
        options.setVisible(false);
        
        acceuil.setTranslateX(Layout.DEFAULT_WIDTH/2 -35);
        acceuil.setTranslateY(Layout.DEFAULT_HEIGHT/2 + 50);
        acceuil.setVisible(false);
        
        quitter.setTranslateX(Layout.DEFAULT_WIDTH/2 -35);
        quitter.setTranslateY(Layout.DEFAULT_HEIGHT/2 + 100);
        quitter.setVisible(false);
        
     // One line transition
        line = new Line(50 , 0 , Layout.DEFAULT_WIDTH-50, 0);
     	line.setStrokeWidth(10);
    	StrokeTransition stroke2 = new StrokeTransition();
     	stroke2.setAutoReverse(true);
     	stroke2.setCycleCount(1000);
     	stroke2.setDuration(Duration.millis(3000));
     	stroke2.setFromValue(Color.DARKGRAY);
     	stroke2.setToValue(Color.DARKSLATEGREY);
     	stroke2.setShape(line);
     	stroke2.play();
     	line.setFill(Color.BLACK);
     			
     // Seconde line style
     	line2 = new Line(50 , Layout.DEFAULT_HEIGHT, Layout.DEFAULT_WIDTH-50 , Layout.DEFAULT_HEIGHT);
     	line2.setStrokeWidth(10);
     	StrokeTransition stroke3 = new StrokeTransition();
     	stroke3.setAutoReverse(true);
     	stroke3.setCycleCount(1000);
     	stroke3.setDuration(Duration.millis(1000));
     	stroke3.setFromValue(Color.DARKGRAY);
     	stroke3.setToValue(Color.DARKSLATEGREY);
     	stroke3.setShape(line2);
     	stroke3.play();
     	line2.setFill(Color.BLACK);
     			
        buttonStyle();
        animationBlock();
        addElements(sun,c2,line,line2,reprendre,recommencer,acceuil,quitter,options);
        
        DropShadow ds1 = new DropShadow();
     	ds1.setOffsetY(1.0f);
     	ds1.setOffsetX(1.0f);
     	ds1.setColor(Color.WHITESMOKE);
     	
     	ballElement.setEffect(ds1);
        addElements(ballElement);
        this.setBackground(Color.CHOCOLATE);
    }

    public void setScore(int x, int y) {
        text.setText(x + " : " + y);
        center(text);
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

    private void center(Text text) {
        var width = text.getLayoutBounds().getWidth();
        text.setX(0.5 * DEFAULT_WIDTH - 0.5 * width);
    }

    
    //-------------------------------------------------------------------------------------------

    
    public void animationBlock() {
    	// Create a sun transition
		//Creation of the sun using the circle shape
		sun = new Circle();
		c2 = new Circle();
		sun.setOpacity(0.3);
	    c2.setOpacity(0.3);
		c2.setRadius(40);
		c2.setFill(Color.BLACK);
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
    		String fond = "-fx-background-color:#001253; ";
    		String style = "-fx-background-radius:20 20 20 20;"+
    		"-fx-font-family: \"Cambria\";"+
    		/*-fx-text-color:#FAF7F0;*/
    		"-fx-text-fill:#f8f8ff;"+
    		"-fx-background-color:#808080;"+
    		"-fx-font-size:20px;"+
    		"-fx-effect: dropshadow( one-pass-box , black , 10 , 0.0 , 5 , 5 );";
    		
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

    
    public void pauseOpacity() {
    	playerElementA.setOpacity(0.3);
    	playerElementB.setOpacity(0.3);
    	text.setOpacity(0.3);
    	ballElement.setOpacity(0.3);
    	line.setOpacity(0.3);
    	line2.setOpacity(0.3);
    }
    public void restoreOpa() {
    	playerElementA.setOpacity(0.9);
    	playerElementB.setOpacity(0.9);
    	text.setOpacity(0.9);
    	ballElement.setOpacity(0.9);
     	line.setOpacity(0.9);
    	line2.setOpacity(0.9);
   
    }
    
    public static Color foncAlea() {
		Random a = new Random();
		int r = a.nextInt(0,256);
		int g = a.nextInt(0,256);
		int b = a.nextInt(0,256);
		
		return Color.rgb(r,g, b);
	}
	
    
}
