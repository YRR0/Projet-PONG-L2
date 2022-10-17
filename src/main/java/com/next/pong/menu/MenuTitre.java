package main.java.com.next.pong.menu;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.effect.*;
import javafx.scene.layout.Pane;


public class MenuTitre extends Pane{
	private Text txt;
	public MenuTitre(String name) {
	String spread = "";
	for(char c : name.toCharArray()) {
		spread+=c + " ";
	}
	//System.out.println("OK");
	txt = new Text(spread);
	txt.setFont(Font.loadFont(Main.class.getResource("police/GothamMedium.ttf").toExternalForm(), 40));
	txt.setFill(Color.WHITE);
	txt.setEffect(new DropShadow(30,Color.BLACK));
	
	getChildren().addAll(txt);
}

public double getTitleWidth() {
	return txt.getLayoutBounds().getWidth();
}
public double getTitleHeight() {
	return txt.getLayoutBounds().getHeight();
}


}
