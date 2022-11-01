package com.next.pong;


import com.next.pong.framework.window.Window;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;
import com.next.pong.game.state.Court;
import com.next.pong.game.state.GameParameters;
import com.next.pong.pages.game.views.GameView;
import com.next.pong.pause.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;



public class App extends Application {
	boolean copieGameView;
    @Override
    public void start(Stage primaryStage) {
        /*Window.init(primaryStage);
        Window.startLoop();*/
        var root = new Pane();
        var gameScene = new Scene(root);

        var gameParameters = new GameParameters(1000, 600);

        var playerA = new Player();
        var playerB = new AIPlayer(gameParameters);

        gameScene.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case CONTROL -> playerA.setState(RacketController.State.GOING_UP);
                case ALT -> playerA.setState(RacketController.State.GOING_DOWN);
                case UP -> playerB.setState(RacketController.State.GOING_UP);
                case DOWN -> playerB.setState(RacketController.State.GOING_DOWN);
            
                default -> throw new IllegalArgumentException("Unexpected value: " + ev.getCode());
            }
        });

        final Timer m = new Timer();
        final TimerTask task = new TimerTask() {
            public void run() {
                ((AIPlayer)playerB).upOrDown();
            }
        };
        if(playerB instanceof AIPlayer) {
            m.schedule(task, 0, 110);
        }

        gameScene.setOnKeyReleased(ev -> {
            switch (ev.getCode()) {
                case CONTROL:
                    if (playerA.getState() == RacketController.State.GOING_UP){
                        playerA.setState(RacketController.State.IDLE);
                    }
                    break;
                case ALT:
                    if (playerA.getState() == RacketController.State.GOING_DOWN) {
                        playerA.setState(RacketController.State.IDLE);
                    }
                    break;
                case UP:
                    if (playerB.getState() == RacketController.State.GOING_UP) {
                        playerB.setState(RacketController.State.IDLE);
                    }
                    break;
                case DOWN:
                    if (playerB.getState() == RacketController.State.GOING_DOWN) {
                        playerB.setState(RacketController.State.IDLE);
                    }
                    break;
                default:
                    break;
            }
        });

        var court = new Court(playerA, playerB, 1000, 600, gameParameters);
        var gameView = new GameView(court, root, 1.0 );
        
        copieGameView = gameView.getPause();
        
        primaryStage.setScene(gameScene);
        primaryStage.show();
        
        // Gestion de menu
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("com/next/pong/menu/Menu1.fxml"));
        Parent rooty;
        Scene sceneP;
		try {
			rooty = loader.load();
			Controller control = loader.getController();
		    sceneP = new Scene(rooty);
		   
		    // Le controller
			Stage pause = new Stage();
			pause.setScene(sceneP);
			gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override 
				public void handle(KeyEvent event) {
					//System.out.println(event.getCode());
					switch(event.getCode()){
					case ESCAPE : gameView.animeStop();
									pause.show();
									// Cas ou le joueur utilise le clavier
									if(copieGameView) {
						            	gameView.animeContinu();
						            }
									
									//logout(primaryStage); //control.quitter(null);
									break;
					default : break;
					}
				}
			});
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		*/
        
        primaryStage.setOnCloseRequest(ev -> {
            ev.consume();
            gameView.animeStop();
            logout(primaryStage);
            if(copieGameView) {
            	gameView.animeContinu();
            }
            //task.cancel();
            //m.cancel();
        });
        
        if(copieGameView) {
        	gameView.animeStart();
        }
        
    }
    
    
    public void logout(Stage stage) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(" Quitter");
        alert.setHeaderText(" Vous allez quitter ...... ");
        alert.setContentText(" Nous espérons vous revoir bientot");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println(" You quitted !! ");
            stage.close();
        }
        else {
        	copieGameView = true;
        }
    	
    }
    
   
}
