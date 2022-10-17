package main.java.com.next.pong.menu;
import java.awt.Window;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.*;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;



public class Main extends Application {
	
	// Les dimensions de notre menu
	private static final double width = 630;
	private static final double height = 390;
	private Line line;
	private Line linePlay;
	Scene window;
	Scene windowPlay;
	Stage change;
	private Pane root = new Pane();
	private Pane rootPlay = new Pane();
	
	private VBox menuBox = new VBox(15);
	private VBox menuBoxPlay = new VBox(15);
	
	
	private List<Pair<String , Runnable>> menuData = Arrays.asList(
		new Pair<String, Runnable>("Play", ()->{ 
		root.getChildren().clear();
		rootPlay.getChildren().clear();
		window = new Scene(createContentPlay());
		change.setScene(window);
		
		//change.show();
		} ),
		new Pair<String, Runnable>("Option", () -> {} ),
		new Pair<String, Runnable>("Exit" , Platform::exit )
	);
	
	private List<Pair<String , Runnable>> menuData2 = Arrays.asList(
			new Pair<String, Runnable>("Player 1 vs Player2", () -> {} ),
			new Pair<String, Runnable>("Player1 vs IA", () -> {} ),
			new Pair<String, Runnable>("Back" , () ->{
			root = new Pane();
			root.getChildren().clear();
			Scene a = new Scene(createContent());
			change.setScene(a);
			//change.show();
			} )
	);
	
	
	
	private Parent createContent() {
		System.out.println("Notre scene principal");
		// Ajout de notre fond avec l'appel de notre affectationde fond
		System.out.println(" Ajout du fond ");
		addBg();
		
		// Ajout de notre titre
		System.out.println(" Ajout du titre ");
		addTitle();
		
		// Ajout de notre ligne
		double linex = width / 2 - 200 ; // augmenter la valeur pour se rapprocher du titre
		double liney = height / 3 + 50 ;
		
		addLine(linex,liney);
		
		// Ajout du menu
		System.out.println(" Menu ");
		addMenu(linex+5,liney+5);
		
		startAnimation();
		//root.setPrefSize(width, height);
	    return root;
	}
	
	private Parent createContentPlay() {
		System.out.println("\n Effacer les anciens elements");
		//root.getChildren().clear();
		
		// Ajout de notre fond avec l'appel de notre affectationde fond
		System.out.println("Ajout du fond dans notre Play ");
		addBgPlay();
		
		// Ajout de notre titre
		System.out.println("Ajout du titre dans notre Play");
		addTitle2();
		
		// Ajout de notre ligne
		double linex = width / 2 - 200 ; // augmenter la valeur pour se rapprocher du titre
		double liney = height / 3 + 50 ;
		
		System.out.println("Ajout de la ligne");
		addLinePlay(linex,liney);
		
		// Ajout du menu
		System.out.println("parametres dans play");
		addPlay2(linex+5,liney+5);
		
		startAnimationPlay();
		//root.setPrefSize(width, height);
		//root = rootPlay;
	    return rootPlay;
	}
	
	
	private void addBg() {
		Image back = new Image(getClass().getResource("image/fond.png").toExternalForm());
		// possiblite de affecter le set dans imageView
		root.getChildren().add(new ImageView(back));
	}
	private void addBgPlay() {
		Image back = new Image(getClass().getResource("image/fond.png").toExternalForm());
		// possiblite de affecter le set dans imageView
		rootPlay.getChildren().add(new ImageView(back));
	}
	// ajout d'une ligne pour le positionnement et l'effet
		private void addLine(double x, double y) {
			line = new Line(x,y,x,y+150);
			line.setStrokeWidth(3);
			line.setStroke(Color.WHITE);
			line.setEffect(new DropShadow(5,Color.BLACK));
			line.setScaleY(0);
			root.getChildren().add(line);
		}
		
	private void addTitle() {
		//System.out.println("Ajout de titre");
		MenuTitre notreTitre = new MenuTitre("PONG by NeXT");
		notreTitre.setTranslateX(width/6 - notreTitre.getWidth()/2);
		notreTitre.setTranslateY(height/3);
		root.getChildren().add(notreTitre);
	}
	
	
	private void addTitle2() {
		//System.out.println("Ajout de titre");
		MenuTitre notreTitre = new MenuTitre("PLAY");
		notreTitre.setTranslateX(width/6 - notreTitre.getWidth()/2);
		notreTitre.setTranslateY(height/3);
		rootPlay.getChildren().add(notreTitre);
	}
	
	private void addLinePlay(double x, double y) {
		linePlay = new Line(x,y,x,y+150);
		linePlay.setStrokeWidth(3);
		linePlay.setStroke(Color.WHITE);
		linePlay.setEffect(new DropShadow(5,Color.BLACK));
		linePlay.setScaleY(0);
		
		rootPlay.getChildren().add(linePlay);
	}
	
	private void addMenu(double x, double y) {
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);
		
		menuData.forEach(data->{
			MenuItem item = new MenuItem(data.getKey());
			item.setOnAction(data.getValue());
			
			// Les bons positionnements
			item.setTranslateX(-300);
			
			//item.setTranslateY(-10); // test 
			Rectangle clip = new Rectangle(300,30);
			clip.translateXProperty().bind(item.translateXProperty().negate());
			item.setClip(clip);
		
			menuBox.getChildren().addAll(item);
		});
		
		root.getChildren().add(menuBox);
	}
	

	private void addPlay2(double x, double y) {
		menuBoxPlay.setTranslateX(x);
		menuBoxPlay.setTranslateY(y);
		
		menuData2.forEach(data->{
			MenuItem item = new MenuItem(data.getKey());
			item.setOnAction(data.getValue());
			
			// Les bons positionnements
			item.setTranslateX(-300);
			
			//item.setTranslateY(-10); // test 
			Rectangle clip = new Rectangle(300,30);
			clip.translateXProperty().bind(item.translateXProperty().negate());
			item.setClip(clip);
		
			menuBoxPlay.getChildren().addAll(item);
		});
		
		rootPlay.getChildren().add(menuBoxPlay);
	}
	
	public void startAnimation() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(1),line);
		st.setToY(1);
		
		st.setOnFinished(e->{
			for(int i=0 ; i < menuBox.getChildren().size();i++) {
				
				Node n = menuBox.getChildren().get(i);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(1+i*0.15),n);
				
				tt.setToX(0);
				tt.setOnFinished(e2->n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}
	
	public void startAnimationPlay() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(1),linePlay);
		st.setToY(1);
		st.setOnFinished(e->{
			for(int i=0 ; i < menuBoxPlay.getChildren().size();i++) {
				
				Node n = menuBoxPlay.getChildren().get(i);
				
				TranslateTransition tt = new TranslateTransition(Duration.seconds(1+i*0.15),n);
				
				tt.setToX(0);
				tt.setOnFinished(e2->n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
				
			window = new Scene(createContent());
			
			primaryStage.setTitle("MENU PONG");
			primaryStage.setScene(window);
			change = primaryStage;
			 
			 // Definir les dimensions à respecter
			 primaryStage.setMinWidth(width);
			 primaryStage.setMinHeight(height);
			 
			 primaryStage.setMaxWidth(width);
			 primaryStage.setMaxHeight(height);
			 
			 change.show();
			 //new Thread(play).start();
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		launch(args);
		
	}
}

 
