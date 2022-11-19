package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import com.next.pong.game.state.Court;
import com.next.pong.game.state.CourtObs;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class GameLayoutObs extends Layout {

    private final CourtObs court;
    private final double scale;
    private final double xMargin = 50.0, racketThickness = 10.0; // pixels
    // children of the game main node
    private final Rectangle racketA, racketB, obstacle1 , obstacle2;
    private final Circle ball;
    private final Text text;

    public  Button reprendre;
    public  Button recommencer;
    public  Button acceuil;
    public  Button quitter;

    public GameLayoutObs(CourtObs court, double scale) {
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
        
        // Ajout des obstacles
        obstacle1 = new Rectangle();
        obstacle1.setHeight(Court.RACKET_SIZE * scale);
        obstacle1.setWidth(racketThickness);
        obstacle1.setFill(Color.BLACK);

        obstacle1.setX(court.getWidth()/2 * scale + 190);
        obstacle1.setY(court.getGP().getRacketA() * scale);

        obstacle2 = new Rectangle();
        obstacle2.setHeight(Court.RACKET_SIZE * scale);
        obstacle2.setWidth(racketThickness);
        obstacle2.setFill(Color.BLACK);

        obstacle2.setX(court.getWidth()/2 * scale - 100);
        obstacle2.setY(court.getGP().getRacketB() * scale);

        
        ball = new Circle();
        ball.setRadius(court.getBallRadius());
        ball.setFill(Color.BLACK);

        ball.setCenterX(court.getGP().getBallX() * scale );
        ball.setCenterY(court.getGP().getBallY() * scale);

        text = new Text();
        text.setX(court.getWidth() / 2);
        text.setY(70);
        text.setFont(Font.font("Times new Roman", FontWeight.BOLD, 50));

        //add the nodes into the layout
        addElements(racketA, racketB,obstacle1,obstacle2, ball, text,reprendre,recommencer,acceuil,quitter);
    }

    public void buttonConfig(){
        reprendre = new Button("Reprendre");
        recommencer = new Button("Recommencer");
        acceuil = new Button("Acceuil");
        quitter = new Button("Quitter");
        
        reprendre.setTranslateX(court.getWidth()/2);
        reprendre.setTranslateY(court.getHeight()/2 - 50);
        reprendre.setVisible(false);
        
        recommencer.setTranslateX(court.getWidth()/2 -20);
        recommencer.setTranslateY(court.getHeight()/2 );
        recommencer.setVisible(false);
        
        acceuil.setTranslateX(court.getWidth()/2 + 22);
        acceuil.setTranslateY(court.getHeight()/2 + 50);
        acceuil.setVisible(false);
        
        quitter.setTranslateX(court.getWidth()/2 + 20);
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
    	//	option.setStyle(style);
    		quitter.setStyle(style);
    }
    
    public void buttonConfigPause(){
    	 reprendre.setVisible(true);
    	 recommencer.setVisible(true);
    	 acceuil.setVisible(true);
    	 quitter.setVisible(true);
          
    }
    public void buttonConfigPauseStop(){
   	 reprendre.setVisible(false);
   	 recommencer.setVisible(false);
   	 acceuil.setVisible(false);
   	 quitter.setVisible(false);
         
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

    public CourtObs getCourt(){
        return court;
    }

}
