package com.next.pong.pages.game;

import com.next.pong.framework.window.Window;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class ModeTournoi {
    boolean affichage = false;
    public static int partie = 4;

    public class Joueur {

        int id1 = 0;
        int id2 = 0;
        int id3 = 0;
        int id4 = 0;

        private SimpleStringProperty nom;
        private SimpleStringProperty score;

        Joueur(String a, String n) {
            this.nom = new SimpleStringProperty(a);
            this.score = new SimpleStringProperty(n);
        }

        public String getNom() {
            return nom.get();
        }

        public void setNom(String nom) {
            this.nom.set(nom);
            ;
        }

        public String getScore() {
            return score.get();
        }

        public void setScore(String score) {
            this.score.get();
        }
    }

    public ObservableList<Joueur> data = FXCollections.observableArrayList(
            new Joueur("Joueur 1", "0"),
            new Joueur("Joueur 2", "0"),
            new Joueur("Joueur 3", "0"),
            new Joueur("Joueur 4", "0"),
            new Joueur(" ", " ! "),
            new Joueur("Fermer la fenetre pour la prochaine partie", " ! ")
    );

    Stage modeTournoiStage = new Stage();
    TableView<Joueur> table;
    TableColumn<Joueur, String> colNom;
    TableColumn<Joueur, String> colScore;

    public ModeTournoi(boolean AI) {
        BorderPane root = new BorderPane();

        table = new TableView<Joueur>();
        table.setEditable(true);

        colNom = new TableColumn("Nom");
        colNom.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nom"));

        colScore = new TableColumn("Score");
        colScore.setCellValueFactory(new PropertyValueFactory<Joueur, String>("score"));

        colNom.setMinWidth(250);
        colScore.setMinWidth(250);

        table.getColumns().addAll(colNom, colScore);

        // Changement
        table.setItems(data);

        root.setCenter(table);
        Scene scene = new Scene(root, 500, 180);

        scene.getStylesheets().add("com/next/pong/css/tableview.css");
        table.getStyleClass().add("table-view");
        colNom.getStyleClass().add("table-view");
        colScore.getStyleClass().add("table-view");
        //table.getStyleClass().add("table-row-cell");
        colNom.getStyleClass().add("table-row-cell");
        colScore.getStyleClass().add("table-row-cell");

        modeTournoiStage.setTitle("--Table View--");
        modeTournoiStage.setScene(scene);
        modeTournoiStage.setOnCloseRequest(event -> {
            Window.goTo(new GameActivity(AI));
            partie--;
        });
    }

    public void afficherTournoi() {
        if (modeTournoiStage.isShowing()) {
            //System.out.println("Deja present");
        } else {
            modeTournoiStage.show();
        }
    }

}
