package com.next.pong.pause;

import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {
	
	Stage stage;
	
	private BorderPane sc;
	
	public void reprendre( ActionEvent e) {
		System.out.println("Reprendre");
	}
	
	public void recommencer( ActionEvent e) {
		System.out.println("Recommencer");
	}
	
	public void acceuil(ActionEvent e) {
		System.out.println("Acceuil");
	}
	
	public void option(ActionEvent e) {
		System.out.println("Option");
	}
	
	public void quitter(ActionEvent e){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(" Quitter");
		alert.setHeaderText(" Vous allez quitter ...... ");
		alert.setContentText(" Nous esperons vous revoir bientot");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage = (Stage)sc.getScene().getWindow();
			System.out.println("You quit !!");
			stage.close();
		}
		System.out.println("Quitter");
		
	}
	
}
