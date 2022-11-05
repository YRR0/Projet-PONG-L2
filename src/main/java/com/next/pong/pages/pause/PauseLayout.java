package com.next.pong.pages.pause;

import com.next.pong.framework.layout.Layout;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PauseLayout extends Layout {

    private Text t = new Text("Game Paused");
    private final Button resume;
    private final Button options;
    private final Button exit;

    public PauseLayout() {
        t.setX(getWidth() / 3);
        resume = new Button("resume");
        resume.setTranslateY(40);
        options = new Button("options");
        options.setTranslateY(80);
        exit = new Button("exit");
        exit.setTranslateY(120);
        setBackground(Color.BEIGE);
        addElements(t, resume, options, exit);
    }

    public Button getResume() {
        return resume;
    }

    public Button getOptions() {
        return options;
    }

    public Button getExit() {
        return exit;
    }

    @Override
    public void onUpdate(double deltaT) {
        super.onUpdate(deltaT);
    }
}
