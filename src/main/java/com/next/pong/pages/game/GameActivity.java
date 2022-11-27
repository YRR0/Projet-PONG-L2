package com.next.pong.pages.game;

import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.window.Window;
import com.next.pong.game.Game;
import com.next.pong.game.player.Player;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.utils.Vector2;
import javafx.scene.input.KeyCode;

public class GameActivity extends Activity<GameLayout> {

    private final Game game;

    public GameActivity() {
        super(new GameLayout());

        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var playerA = new Player(
                new Vector2(0.05 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.3 * height)
        );

        var playerB = new Player(
                new Vector2(0.95 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.3 * height)
        );

        game = new Game(width, height, playerA, playerB);

        setupPlayerControl(playerA, KeyCode.CONTROL, KeyCode.ALT);
        setupPlayerControl(playerB, KeyCode.UP, KeyCode.DOWN);
        
       // ------------------------------------------------------------------------------------
       
       setUpPause(KeyCode.ESCAPE,game,this);
       GameLayout g = this.layout;
       
       g.reprendre.setOnMouseClicked(e ->{
       	this.layout.restoreOpa();
        this.layout.buttonConfigPauseStop();
        game.getCourt().pause = false; 
       });
       g.recommencer.setOnMouseClicked(e -> {
       	Window.goTo( new GameActivity() );
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
    
    private void setUpPause(KeyCode pause,Game ga,GameActivity gaAc) {
    	addKeyEventListener(pause , new KeyEventListener() {
    		@Override
    		public void onPressed() {
    			if(ga.getCourt().pause) {
    				ga.getCourt().pause = false;
    				gaAc.layout.restoreOpa();
    				gaAc.layout.buttonConfigPauseStop();
    			}
    			else {
    				ga.getCourt().pause  = true;
    				gaAc.layout.pauseOpacity();
                    gaAc.layout.buttonConfigPause();;
    			}
    		}
    		
    		@Override
    		public void onReleased() {
    			System.out.println("Pause");
    		}
    	});
    }
    
    private void setupPlayerControl(Player player, KeyCode up, KeyCode down) {
        addKeyEventListener(up, new KeyEventListener() {
            @Override
            public void onPressed() {
                player.applyForceUp();
            }

            @Override
            public void onReleased() {
                if(player.isForcingUp()) {
                    player.applyNeutralForce();
                }
            }
        });

        addKeyEventListener(down, new KeyEventListener() {
            @Override
            public void onPressed() {
                player.applyForceDown();
            }

            @Override
            public void onReleased() {
                if(player.isForcingDown()) {
                    player.applyNeutralForce();
                }
            }
        });
    }

    @Override
    public void onUpdate(double deltaTime) {
        game.update(deltaTime);

        layout.setScore(game.getScorePlayerA(), game.getScorePlayerB());

        var ball = game.getBall();
        var ballPosition = ball.getPosition();
        layout.setBallProperties(ballPosition.x(), ballPosition.y(), ball.getRadius());

        var playerA = game.getPlayerA();
        var positionA = playerA.getPosition();
        var sizeA = playerA.getSize();
        layout.setPlayerElementA(positionA.x(), positionA.y(), sizeA.x(), sizeA.y());

        var playerB = game.getPlayerB();
        var positionB = playerB.getPosition();
        var sizeB = playerB.getSize();
        layout.setPlayerElementB(positionB.x(), positionB.y(), sizeB.x(), sizeB.y());
    }

}
