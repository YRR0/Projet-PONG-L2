package src.main.java.com.next.pong.menu;

public class MenuItem extends Pane{
	// un attribut text
	private Text texte;
	
	// Des affets pour le texte
	private Effect shadow = new DropShadow(5,Color.BLACK);
	private Effect blur = new BoxBlur(1,1,3);
	
	// Creation des elements d'un menu 
	public MenuItem(String a){
		Polygon bg = new Polygon(
			0,0,	
			200,0,
			215,15,
			200,30,
			0,30);
		
		// Couleur des bordures
		bg.setStroke(Color.WHITESMOKE);
		
		// un effeft sur le polygon
		//bg.setEffect(new GaussianBlur());
		
		// Propriérés à l'appui
		bg.fillProperty().bind( // les liaisons 
				Bindings.when(pressedProperty())
			    .then(Color.CORNFLOWERBLUE)
			    .otherwise(Color.STEELBLUE));
		
		// Le textee qu'on va mettre sur cette disposition du menu
		texte = new Text(a);
		
		//Position du texte dans le polygon 
		texte.setTranslateX(5);
		texte.setTranslateY(20);
		texte.setFill(Color.WHITE);
		
		// texte.setFont 
		texte.setFont(Font.loadFont(Main.class.getResource("police/GothamMedium.ttf").toExternalForm(), 20));
		
		// Essayer d'ajouter un fond mais ne marche pas 
		/*texte.effectProperty().bind(
				Bindings.when(hoverProperty())
				.then(shadow)
				.otherwise(blur));
		*/
		// ajout de tous dans notre pane
		getChildren().addAll(bg,texte);
	}
	
	// Definir les actions à faire si on appuie
	public void setOnAction(Runnable action) {
		setOnMouseClicked( e -> action.run());
	}
	
}
