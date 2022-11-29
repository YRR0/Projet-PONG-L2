package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Sound;
import com.next.pong.framework.window.Window;
import com.next.pong.game.Game;
import com.next.pong.game.ball.Ball;
import com.next.pong.game.court.Court;
import com.next.pong.game.player.Player;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.utils.Vector2;
import javafx.scene.input.KeyCode;

public class GameActivity extends Activity<GameLayout> {

    private final Game game;
    private final Sound sound;
    public GameActivity() {
        super(new GameLayout());

        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2(200, 300),
                10
        );

        var playerA = new Player(
                new Vector2(0.05 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height)
        );

        var playerB = new Player(
                new Vector2(0.95 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height)
        );

        game = new Game(width, height, ball, playerA, playerB);

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

       sound = new Sound();

       game.getCourt().setListener(new Court.Listener() {
           @Override
           public void onPlayerScored(int id) {
                sound.playSoundEffect(Resources.Music.UPDATE);
           }
           @Override
           public void onBallVerticalWallCollision(int id) {
               sound.playSoundEffect(Resources.Music.BOUNCE);
                if(id == 1) {
                    g.line.getStyleClass().add("line-ball-collision");
                    g.line2.getStyleClass().remove("line-ball-collision");
                } else {
                    g.line2.getStyleClass().add("line-ball-collision");
                    g.line.getStyleClass().remove("line-ball-collision");
                }
           }

           @Override
           public void onBallPlayerCollision(int id) {
                sound.playSoundEffect(Resources.Music.KICK);
           }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        sound.stop();
    }
}
