/**
 * Sample Skeleton for 'MainMeun.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="historyOfGame"
    private Button historyOfGame; // Value injected by FXMLLoader

    @FXML // fx:id="newGameButton"
    private Button newGameButton; // Value injected by FXMLLoader

    @FXML // fx:id="quitTheGame"
    private Button quitTheGame; // Value injected by FXMLLoader

    @FXML // fx:id="settingsOfGame"
    private Button settingsOfGame; // Value injected by FXMLLoader

    @FXML
    void configuration(ActionEvent event) {

    }

    @FXML
    void newGameAction(ActionEvent event) {

    }

    @FXML
    void quit(ActionEvent event) {

    }

    @FXML
    void showHistoryOfGame(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert historyOfGame != null : "fx:id=\"historyOfGame\" was not injected: check your FXML file 'MainMeun.fxml'.";
        assert newGameButton != null : "fx:id=\"newGameButton\" was not injected: check your FXML file 'MainMeun.fxml'.";
        assert quitTheGame != null : "fx:id=\"quitTheGame\" was not injected: check your FXML file 'MainMeun.fxml'.";
        assert settingsOfGame != null : "fx:id=\"settingsOfGame\" was not injected: check your FXML file 'MainMeun.fxml'.";

    }

}
