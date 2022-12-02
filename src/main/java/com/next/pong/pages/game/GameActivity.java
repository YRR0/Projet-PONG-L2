package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Sound;
import com.next.pong.framework.window.Window;
import com.next.pong.game.Game;
import com.next.pong.game.ball.Ball;
import com.next.pong.game.player.ComputerPlayer;
import com.next.pong.game.player.Player;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.utils.Vector2;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import javax.swing.*;
import java.text.DecimalFormat;

public class GameActivity extends Activity<GameLayout> {

    private class GameTimer {

        Timer timer;
        private int seconds;
        private int minutes;
        private Text text;
        private DecimalFormat df = new DecimalFormat("00");

        private GameTimer(int m, int s) {
            this.minutes = m;
            this.seconds = s;
            text = new Text(df.format(minutes) + ":" + df.format(seconds));
            timer = new Timer(1000, e -> {
                    seconds--;
                    String formatedSeconds = df.format(seconds);
                    String formatedMinutes = df.format(minutes);
                    if(seconds == -1) {
                        seconds = 59;
                        minutes--;
                        formatedSeconds = df.format(seconds);
                        formatedMinutes = df.format(minutes);
                    }
                    if(minutes == 0 && seconds == 3) se.playSoundEffect(Resources.Music.COUNTDOWN);
                    this.text.setText(formatedMinutes + ":" + formatedSeconds);
                    if(seconds == 0 && minutes == 0) {
                       getWinner();
                    }
            });
        }
        public Text getTime() {
            return text;
        }

        public void startGameTimer() {
            timer.start();
        }
        public void stopGameTimer() {
            timer.stop();
        }

        private void getWinner() {
            timer.stop();
            Game game = GameActivity.this.game;
            int scoreA = game.getScorePlayerA(), scoreB = game.getScorePlayerB();
            if(scoreA > scoreB) {
                text.setText("PlayerA wins");
            } else if(scoreA == scoreB) {
                text.setText("Draw");
            } else {
                text.setText("PlayerB wins");
            }
        }
    }

    private final Game game;
    private final Sound music;
    private final Sound se;
    private final GameTimer gt = new GameTimer(0, 30);
    public GameActivity() {
        super(new GameLayout());

        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2(150, 150),
                10
        );

        var playerA = new ComputerPlayer(
                new Vector2(0.05 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height),
                ball
        );

        /*var playerB = new Player(
                new Vector2(0.95 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height)
        );*/

        var playerB = new ComputerPlayer(
                new Vector2(0.95 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height),
                ball
        );

        game = new Game(width, height, ball, playerA, playerB);

        if(!(playerA instanceof ComputerPlayer)) setupPlayerControl(playerA, KeyCode.CONTROL, KeyCode.ALT);
        if(!(playerB instanceof ComputerPlayer)) setupPlayerControl(playerB, KeyCode.UP, KeyCode.DOWN);
        
       // ------------------------------------------------------------------------------------
       
       setUpPause(KeyCode.ESCAPE,game,this);
       GameLayout g = this.layout;
       
       g.reprendre.setOnMouseClicked(e ->{
            this.layout.restoreOpa();
            this.layout.buttonConfigPauseStop();
            game.getCourt().pause = false;
            if(gt.minutes > 0 || gt.seconds > 0) gt.startGameTimer();
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

       music = new Sound();
       music.playMusic(Resources.Music.GAME);
       se = new Sound();
       game.setListener(new Game.Listener() {
           @Override
           public void onPlayerScored() {
               se.playSoundEffect(Resources.Music.UPDATE);
           }
           @Override
           public void onBallVerticalWallCollision(int id) {
               se.playSoundEffect(Resources.Music.BOUNCE);
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
               se.playSoundEffect(Resources.Music.KICK);
           }
       });

       // Timer mode
        gt.startGameTimer();
    }
    
    private void setUpPause(KeyCode pause,Game ga,GameActivity gaAc) {
    	addKeyEventListener(pause , new KeyEventListener() {
    		@Override
    		public void onPressed() {
    			if(ga.getCourt().pause) {
    				ga.getCourt().pause = false;
    				gaAc.layout.restoreOpa();
    				gaAc.layout.buttonConfigPauseStop();
                    gt.startGameTimer();
    			}
    			else {
    				ga.getCourt().pause  = true;
    				gaAc.layout.pauseOpacity();
                    gaAc.layout.buttonConfigPause();
                    gt.stopGameTimer();
    			}
    		}
    		
    		@Override
    		public void onReleased() {
    			System.out.println("Pause");
    		}
    	});
    }
    
    private void setupPlayerControl(Player player, KeyCode up, KeyCode down) {
        addKeyEventListener(up, player.setKeyEventListener(new KeyEventListener() {
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
        }));

        addKeyEventListener(down, player.setKeyEventListener(new KeyEventListener() {
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
        }));
    }

    @Override
    public void onUpdate(double deltaTime) {
        game.update(deltaTime);

        layout.setScore(game.getScorePlayerA(), game.getScorePlayerB());
        layout.setTime(gt.getTime());

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
        if(gt != null) gt.stopGameTimer();
        if(music != null) music.stopMusic();
    }
}
