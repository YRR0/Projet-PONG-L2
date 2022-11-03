package com.next.pong.pause;


import com.next.pong.framework.layout.Layout;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.next.pong.pause.*;

public class PauseLayout extends Layout{
	Button reprendre;
	Button recommencer;
	Button acceuil;
	Button option;
	Button quitter;
	
	Label lab = new Label("Pause");
	
	public PauseLayout() {
		super(500,500);
		
		this.screen();
		creaButton();
		
		this.style();
		
		
		position();
	}
	
	public void screen() {
		this.setMinWidth(500);
		this.setMaxWidth(500);
		this.setMinHeight(500);
		this.setMaxHeight(500);
		this.setPrefHeight(500);
		this.setPrefHeight(500);
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
		
		lab.setTranslateX(200);
		lab.setTranslateY(25);
		
		reprendre.setTranslateX(200);
		reprendre.setTranslateY(50);
		
		recommencer.setTranslateX(200);
		recommencer.setTranslateY(100);
		
		acceuil.setTranslateX(200);
		acceuil.setTranslateY(150);
		
		option.setTranslateX(200);
		option.setTranslateY(200);
		
		quitter.setTranslateX(200);
		quitter.setTranslateY(250);
		
	}
	
	public void style() {
		try {
			this.getStylesheets().add("pause.css");
			
			lab.setStyle("fx-font-size : 80; ");
			
			reprendre.getStyleClass().add("reprendre");
			
		}
		catch(Exception e) {
			System.out.println(" Il y a une erreur " + e);
		}
	}
}
