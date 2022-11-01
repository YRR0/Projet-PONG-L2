package com.next.pong.pause;
	
/*import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
*/
/*
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			System.out.println("Reussi");
			
			// une autre manière de la lancer le fichier fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu1.fxml"));
			Parent root = loader.load();
			
			// Le controller Controller
			Controller control = loader.getController();
			Scene scene = new Scene(root);
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override 
				public void handle(KeyEvent event) {
					//System.out.println(event.getCode());
					switch(event.getCode()){
					case ESCAPE : logout(primaryStage); //control.quitter(null);
								  break;
					default : break;
					}
				}
			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logout(Stage stage) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(" Quitter");
        alert.setHeaderText(" Vous allez quitter ...... ");
        alert.setContentText(" Nous espÃ©rons vous revoir bientot");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println(" You quitted !! 	");
            stage.close();
        }
    }
   
	public static void main(String[] args) {
		launch(args);
	}
	
}
*/
