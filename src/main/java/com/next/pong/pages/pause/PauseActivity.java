package com.next.pong.pages.pause;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.pages.game.GameActivity;


public class PauseActivity extends Activity<PauseLayout> {

    GameActivity gameActivity;
    public PauseActivity(GameActivity ga) {
        super(new PauseLayout());

        gameActivity = ga;

        PauseLayout layout = getLayout();

        layout.getResume().setOnMouseClicked(event -> {
            resumeGame();
        });

        getScene().setOnKeyPressed(event ->{
            switch (event.getCode()) {
                case ESCAPE -> resumeGame();
            }
        });

    }

    private void resumeGame() {
        gameActivity.getLayout().getCourt().setPause(false);
        Window.goTo(gameActivity);
    }

}
