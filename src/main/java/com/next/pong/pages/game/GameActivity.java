package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Sound;
import com.next.pong.framework.window.Window;
import com.next.pong.game.Game;
import com.next.pong.game.ball.Ball;
import com.next.pong.game.player.ComputerPlayer;
import com.next.pong.game.player.Player;
import com.next.pong.pages.game.ModeTournoi.Joueur;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.pages.settings.SettingsActivity;
import com.next.pong.utils.Vector2;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
            text.setId("chrono");
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

        public boolean vtimer=false;
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
            game.getCourt().pause = true;
            vtimer = game.getCourt().pause;
        }
    }

    private final Game game;
    private final Sound music;
    private final Sound se;
    private final GameTimer gt = new GameTimer(0, 30);
    private boolean ordi;
    public GameActivity(boolean AI) {
        super(new GameLayout());
        ordi = AI;
        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2(150, 150),
                10
        );
        
        
        var playerA = new Player(
                new Vector2(0.05 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height)//,
                //ball
        );

        var playerB = new Player(
                new Vector2(0.95 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.25 * height)
        );

        if (AI) {
            playerB = new ComputerPlayer(
                    new Vector2(0.95 * width, 0.5 * height),
                    new Vector2(0.0, 0.0),
                    new Vector2(0.01 * width, 0.25 * height),
                    ball
            );
        }

        game = new Game(width, height, ball, playerA, playerB);

        setupPlayerControl(playerA, KeyCode.CONTROL, KeyCode.ALT);

        if(!(playerB instanceof ComputerPlayer)) {
            setupPlayerControl(playerB, KeyCode.UP, KeyCode.DOWN);
        }

       setUpPause(KeyCode.ESCAPE,game,this);
       GameLayout g = this.layout;
       
       g.reprendre.setOnMouseClicked(e ->{
            this.layout.restoreOpa();
            this.layout.buttonConfigPauseStop();
            game.getCourt().pause = false;
            if(gt.minutes > 0 || gt.seconds > 0) gt.startGameTimer();
       });
       g.recommencer.setOnMouseClicked(e -> {
       	    Window.goTo( new GameActivity(AI) );
       });
       g.acceuil.setOnMouseClicked(e -> {
       	    Window.goTo(new HomeActivity());
       });
       g.options.setOnMouseClicked(e -> {
            Window.goTo(new SettingsActivity());
       });
       g.quitter.setOnMouseClicked(e -> {
       	    System.exit(0);
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
    
    ModeTournoi gerer = new ModeTournoi(this.ordi);
    
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

        if(playerB instanceof ComputerPlayer player) {
            player.move();
        }

         if(gt.vtimer && ModeTournoi.partie > 0){
        	// Mis a jour des scores 
        	this.gestionnTournoi();  
         }else {
        	 gt.vtimer = false;
         }
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(gt != null) gt.stopGameTimer();
        if(music != null) music.stopMusic();
    }
    
  boolean last=true;int n=0;
  public void gestionnTournoi() {
    	if(ModeTournoi.partie == 4) {
    		System.out.println("Partie 1");
    		String sc1 = String.valueOf(game.getScorePlayerA());
        	String sc2 = String.valueOf(game.getScorePlayerB());
        	
        	if(game.getScorePlayerA() > game.getScorePlayerB()) {
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est  Joueur 1"," ! "));
        	}
        	else {
        		String mess = gerer.data.get(4).getNom();
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est Joueur 2"," ! "));
        	}
            gerer.data.set(0, gerer.new Joueur("Joueur 1",sc1));
            gerer.data.set(1, gerer.new Joueur("Joueur 2",sc2));
            //System.out.println(game.getScorePlayerA());
            //System.out.println(game.getScorePlayerB());
            this.layout.sun.setVisible(false);
            this.layout.c2.setVisible(false); 
            gerer.afficherTournoi();
    	}
    	if(ModeTournoi.partie == 3) {
    		System.out.println("Partie 2");
    		String sc1 = String.valueOf(game.getScorePlayerA());
        	String sc2 = String.valueOf(game.getScorePlayerB());
        	
        	if(game.getScorePlayerA() > game.getScorePlayerB()) {
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 3"," ! "));
        	}
        	else {
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 4"," ! "));
        	}
            gerer.data.set(2, gerer.new Joueur("Joueur 3",sc1));
            gerer.data.set(3, gerer.new Joueur("Joueur 4",sc2));
            //System.out.println(game.getScorePlayerA());
            //System.out.println(game.getScorePlayerB());
            this.layout.sun.setVisible(false);
            this.layout.c2.setVisible(false); 
            gerer.afficherTournoi();
            
    	}
    	if(ModeTournoi.partie == 2 ) {
    		System.out.println("Partie 3");
    		if(game.getScorePlayerA() > game.getScorePlayerB()) {
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 1"," ! "));
        	}
        	else {
        		gerer.data.set(4, gerer.new Joueur("Le gagnat est JOUEUR 3"," ! "));
        	}
    		if(n==0) {
    			int reelSC = Integer.valueOf(gerer.data.get(0).getScore())+game.getScorePlayerA();
        		System.out.println(reelSC);
    			int reelSC2 = Integer.valueOf(gerer.data.get(2).getScore())+game.getScorePlayerB();
                System.out.println(reelSC2);
    			gerer.data.set(0, gerer.new Joueur("Joueur 1",String.valueOf(reelSC)));
                gerer.data.set(2, gerer.new Joueur("Joueur 3",String.valueOf(reelSC2)));
                //System.out.println(game.getScorePlayerA());
                //System.out.println(game.getScorePlayerB());
                this.layout.sun.setVisible(false);
                this.layout.c2.setVisible(false); 
                gerer.afficherTournoi();	
           }
    		n++;
    	}
    	if(ModeTournoi.partie == 1){
    		System.out.println("Partie 4");
    		if(game.getScorePlayerA() > game.getScorePlayerB()) {
        		gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 2"," "));
        	}
        	else {
        		gerer.data.set(4, gerer.new Joueur("Le gagnat est JOUEUR 4"," "));
        	}
    		
    		if(last) {
    			int reelSC = Integer.valueOf(gerer.data.get(1).getScore())+game.getScorePlayerA();
        		int reelSC2 = Integer.valueOf(gerer.data.get(3).getScore())+game.getScorePlayerB();
                gerer.data.set(1, gerer.new Joueur("Joueur 2",String.valueOf(reelSC)));
                gerer.data.set(3, gerer.new Joueur("Joueur 4",String.valueOf(reelSC2)));
                //System.out.println(game.getScorePlayerA());
                //System.out.println(game.getScorePlayerB());
                this.layout.sun.setVisible(false);
                this.layout.c2.setVisible(false); 
                gerer.afficherTournoi();	
                last = false;
    		}
    		
    	}
    }
    
}
