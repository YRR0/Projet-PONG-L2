package com.next.pong.pages.pause;

import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PauseLayout extends Layout {

    private Text t = new Text("Game Paused");
    private  Button resume;
    private  Button recommencer;
    private  Button acceuil;
    private  Button option;
    private  Button exit;
    private  Label lab;

    public PauseLayout() {
        //super(300,400);
        creaButton();
        this.position();
        style();
    }
    

	public void creaButton() {
        lab = new Label("Game Pause");
		resume = new Button("Reprendre");
		recommencer = new Button("Recommencer");
		acceuil = new Button("Acceuil");
		option = new Button("Options");
		exit = new Button("Quitter");
		this.addElements(lab,resume,recommencer,acceuil,option,exit);
	}


	public void screen() {
		this.setPrefSize(300,400);
		this.setMinSize(300, 400);
		this.setMaxSize(300, 400);
	}

    public void position(){

		lab.setTranslateX(90);
		lab.setTranslateY(20);
		
		resume.setTranslateX(85);
		resume.setTranslateY(100);
		
		recommencer.setTranslateX(70);
		recommencer.setTranslateY(150);
		
		acceuil.setTranslateX(100);
		acceuil.setTranslateY(200);
		
		option.setTranslateX((95));
		option.setTranslateY(250);
		
		exit.setTranslateX(100);
		exit.setTranslateY(300);
	
    }
	
    public void style(){
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
		resume.setStyle(style);
		recommencer.setStyle(style);
		acceuil.setStyle(style);
		option.setStyle(style);
		exit.setStyle(style);
		
    }

    public Button getResume() {
        return resume;
    }

    public Button getOptions() {
        return option;
    }

    public Button getExit() {
        return exit;
    }

    public Button getRecommencer(){
        return recommencer;
    }

    public Button getAcceuil(){
        return acceuil;
    }

    @Override
    public void onUpdate(double deltaT) {
        super.onUpdate(deltaT);
    }
}
