package com.next.pong.pages.pause;


import com.next.pong.framework.layout.Layout;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PauseLayout extends Layout{
	public Button reprendre;
	public Button recommencer;
	public Button acceuil;
	public Button option;
	public Button quitter;
	
	Label lab = new Label("Pause");
	
	public PauseLayout() {
		super(300,400);
		
		this.screen();
		creaButton();
		
		this.style();
		
		position();
	}
	
	public void screen() {
		this.setPrefSize(300,400);
		this.setMinSize(300, 400);
		this.setMaxSize(300, 400);
	}
	
	public void creaButton() {
		reprendre = new Button("Reprendre");
		recommencer = new Button("Recommencer");
		acceuil = new Button("Acceuil");
		option = new Button("Options");
		quitter = new Button("Quitter");
	
		this.addElements(lab,reprendre,recommencer,acceuil,option,quitter);
	}
	

	private void addBgPlay() {
		Image back = new Image(getClass().getResource("image/fond.png").toExternalForm());
		// possiblite de affecter le set dans imageView
		this.getChildren().add(new ImageView(back));
	}
	
	public void position() {
		
		lab.setTranslateX(90);
		lab.setTranslateY(20);
		
		reprendre.setTranslateX(85);
		reprendre.setTranslateY(100);
		
		recommencer.setTranslateX(70);
		recommencer.setTranslateY(150);
		
		acceuil.setTranslateX(100);
		acceuil.setTranslateY(200);
		
		option.setTranslateX((95));
		option.setTranslateY(250);
		
		quitter.setTranslateX(100);
		quitter.setTranslateY(300);
		
	}
	
	public void style() {
		String fond = "-fx-background-color:#001253; ";
		this.setStyle(fond);
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
		lab.setStyle(styleLab);
		reprendre.setStyle(style);
		recommencer.setStyle(style);
		acceuil.setStyle(style);
		option.setStyle(style);
		quitter.setStyle(style);
		
	}

	 public boolean onStop() {
        return super.onStop();
    }
	
}
