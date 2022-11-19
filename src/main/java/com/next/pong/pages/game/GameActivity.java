package com.next.pong.pages.game;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.game.player.Player;
import com.next.pong.game.player.RacketController;
import com.next.pong.game.player.ai.AIPlayer;
import com.next.pong.game.player.ai.IAInterface;
import com.next.pong.game.state.Court;
import com.next.pong.game.state.CourtObs;
import com.next.pong.game.state.GameParameters;
import com.next.pong.game.state.GameParametersObs;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.pages.pause.PauseActivity;
import javafx.scene.Scene;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends Activity<GameLayout> {

    final Timer m = new Timer();

    //generating the scene
    private void initScene(RacketController playerA, RacketController playerB, Scene gameScene){

        gameScene.setOnKeyPressed(ev -> {
            switch (ev.getCode()) {
                case CONTROL -> playerA.setState(RacketController.State.GOING_UP);
                case ALT -> playerA.setState(RacketController.State.GOING_DOWN);
                case UP -> playerB.setState(RacketController.State.GOING_UP);
                case DOWN -> playerB.setState(RacketController.State.GOING_DOWN);
                case ESCAPE -> {
                	this.getLayout().pauseOpacity();
                    getLayout().getCourt().setPause(true);
                    this.getLayout().buttonConfigPause();
                    
                    //Window.goTo(new PauseActivity(this));
                }
                default -> throw new IllegalArgumentException("Unexpected value: " + ev.getCode());
            }
        });

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
                case CONTROL, ALT -> playerA.setState(RacketController.State.IDLE);
                case UP, DOWN -> playerB.setState(RacketController.State.IDLE);
            }
        });
    }

    public void stopTimer() {
        m.cancel();
    }

    private static Court generateCourt(double width, double height){
        var gp = new GameParameters(height, width);

        //generating the players
        Player playerA = new Player();
        Player playerB = new Player();//AIPlayer(gp, IAInterface.Level.MEDIUM);
           
        return new Court(playerA, playerB, gp);
    }
    
    private static CourtObs generateCourtObs(double width, double height){
        var gp = new GameParametersObs(height, width);

        //generating the players
        Player playerA = new Player();
        Player playerB = new Player();//AIPlayer(gp, IAInterface.Level.MEDIUM);
       
        // ajout de test
        Player obstacle1 = new Player();
        Player obstacle2 = new Player();
       
        return new CourtObs(playerA, playerB,obstacle1,obstacle2, gp);
    }

    

    public GameActivity(double width, double height, double scale) {
        //super(new GameLayout(generateCourt(width, height), scale));
        super(new GameLayout(generateCourt(width,height), scale));
        
        initScene(getLayout().getCourt().getPlayerA(), getLayout().getCourt().getPlayerB(), getScene());
        
        
        GameLayout g = this.getLayout();
        g.reprendre.setOnMouseClicked(e ->{
        	this.getLayout().restoreOpa();
            this.getLayout().buttonConfigPauseStop();
            
        	getLayout().getCourt().setPause(false);
        });
        g.recommencer.setOnMouseClicked(e -> {
        	Window.goTo( new GameActivity(700 , 400 ,1.0) );
        });
        g.acceuil.setOnMouseClicked(e -> {
        	Window.goTo(new HomeActivity());
        });
        g.options.setOnMouseClicked(e -> {
        	Window.goTo(new HomeActivity());
        });
        g.quitter.setOnMouseClicked(e -> {
        	Window.goTo(new HomeActivity());
        });
    }

    @Override
    public boolean onStop() {
        if(!getLayout().getCourt().getPause()) {
            boolean res = super.onStop();
            if(res) m.cancel();
            return res;
        }
        return false;
    }
}
