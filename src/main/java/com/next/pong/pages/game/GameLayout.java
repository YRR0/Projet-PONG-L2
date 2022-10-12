package com.next.pong.pages.game;

import com.next.pong.framework.layout.Layout;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameLayout extends Layout {

    public GameLayout() {
        var text = new Text("Game");
        addElements(text);

        setBackground(Color.YELLOW);
    }

}
