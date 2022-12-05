package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.activity.Activity;
import com.next.pong.framework.audio.Sound;
import com.next.pong.framework.layout.Layout;
import com.next.pong.framework.window.Window;
import com.next.pong.game.Game;
import com.next.pong.game.ball.Ball;
import com.next.pong.game.player.ComputerPlayer;
import com.next.pong.game.player.Player;
import com.next.pong.pages.home.HomeActivity;
import com.next.pong.pages.settings.SettingsActivity;
import com.next.pong.utils.Vector2;
import javafx.scene.input.KeyCode;

import javax.swing.*;

public class GameActivity extends Activity<GameLayout> {

    private final Game game;
    private final Sound music;
    private final Sound se;
    private final GameTimer gameTimer;
    private boolean isComputerPlayerB;

    public GameActivity(boolean AI) {
        super(new GameLayout());

        isComputerPlayerB = AI;
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

        var positionB = new Vector2(0.95 * width, 0.5 * height);
        var speedB = new Vector2(0.0, 0.0);
        var sizeB = new Vector2(0.01 * width, 0.25 * height);

        var playerB = AI ? new ComputerPlayer(positionB, speedB, sizeB) :
                new Player(positionB, speedB, sizeB);

        game = new Game(width, height, ball, playerA, playerB);
        gameTimer = new GameTimer(game, 0, 30);

        setupPlayerControl(playerA, KeyCode.CONTROL, KeyCode.ALT);

        if (!(playerB instanceof ComputerPlayer)) {
            setupPlayerControl(playerB, KeyCode.UP, KeyCode.DOWN);
        }

        setUpPause(KeyCode.ESCAPE, game, this);
        GameLayout g = this.layout;

        g.reprendre.setOnMouseClicked(e -> {
            layout.restoreOpa();
            layout.buttonConfigPauseStop();
            game.getCourt().pause = false;

            if (gameTimer.getMinutes() > 0 || gameTimer.getSeconds() > 0) {
                gameTimer.start();
            }
        });

        g.recommencer.setOnMouseClicked(e -> Window.goTo(new GameActivity(AI)));

        g.acceuil.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));

        g.options.setOnMouseClicked(e -> Window.goTo(new SettingsActivity()));

        g.quitter.setOnMouseClicked(e -> System.exit(0));

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
                if (id == 1) {
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
        gameTimer.start();
    }

    private void setUpPause(KeyCode pause, Game ga, GameActivity gaAc) {
        addKeyEventListener(pause, new KeyEventListener() {
            @Override
            public void onPressed() {
                if (ga.getCourt().pause) {
                    ga.getCourt().pause = false;
                    gaAc.layout.restoreOpa();
                    gaAc.layout.buttonConfigPauseStop();
                    gameTimer.start();
                } else {
                    ga.getCourt().pause = true;
                    gaAc.layout.pauseOpacity();
                    gaAc.layout.buttonConfigPause();
                    gameTimer.stop();
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
                if (player.isForcingUp()) {
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
                if (player.isForcingDown()) {
                    player.applyNeutralForce();
                }
            }
        });
    }

    ModeTournoi gerer = new ModeTournoi(isComputerPlayerB);

    @Override
    public void onUpdate(double deltaTime) {
        game.update(deltaTime);

        layout.setScore(game.getScorePlayerA(), game.getScorePlayerB());
        layout.setTime(gameTimer.getTime());

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

        if (playerB instanceof ComputerPlayer player) {
            player.update(ball, Layout.DEFAULT_WIDTH);
        }

        gameTimer.update(deltaTime);

        if (gameTimer.getVTimer() && ModeTournoi.partie > 0) {
            // Mis a jour des scores
            gestionTournoi();
        } else {
            gameTimer.setVtimer(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (gameTimer != null) {
            gameTimer.stop();
        }

        if (music != null) {
            music.stopMusic();
        }
    }

    boolean last = true;
    int n = 0;

    public void gestionTournoi() {

        if (ModeTournoi.partie == 4) {
            System.out.println("Partie 1");

            String sc1 = String.valueOf(game.getScorePlayerA());
            String sc2 = String.valueOf(game.getScorePlayerB());

            if (game.getScorePlayerA() > game.getScorePlayerB()) {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est  Joueur 1", " ! "));
            } else {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est Joueur 2", " ! "));
            }

            gerer.data.set(0, gerer.new Joueur("Joueur 1", sc1));
            gerer.data.set(1, gerer.new Joueur("Joueur 2", sc2));

            this.layout.sun.setVisible(false);
            this.layout.c2.setVisible(false);

            gerer.afficherTournoi();
        }

        if (ModeTournoi.partie == 3) {
            System.out.println("Partie 2");

            String sc1 = String.valueOf(game.getScorePlayerA());
            String sc2 = String.valueOf(game.getScorePlayerB());

            if (game.getScorePlayerA() > game.getScorePlayerB()) {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 3", " ! "));
            } else {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 4", " ! "));
            }
            gerer.data.set(2, gerer.new Joueur("Joueur 3", sc1));
            gerer.data.set(3, gerer.new Joueur("Joueur 4", sc2));

            this.layout.sun.setVisible(false);
            this.layout.c2.setVisible(false);

            gerer.afficherTournoi();
        }

        if (ModeTournoi.partie == 2) {
            System.out.println("Partie 3");

            if (game.getScorePlayerA() > game.getScorePlayerB()) {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 1", " ! "));
            } else {
                gerer.data.set(4, gerer.new Joueur("Le gagnat est JOUEUR 3", " ! "));
            }

            if (n == 0) {
                int reelSC = Integer.parseInt(gerer.data.get(0).getScore()) + game.getScorePlayerA();
                System.out.println(reelSC);
                int reelSC2 = Integer.parseInt(gerer.data.get(2).getScore()) + game.getScorePlayerB();
                System.out.println(reelSC2);

                gerer.data.set(0, gerer.new Joueur("Joueur 1", String.valueOf(reelSC)));
                gerer.data.set(2, gerer.new Joueur("Joueur 3", String.valueOf(reelSC2)));

                this.layout.sun.setVisible(false);
                this.layout.c2.setVisible(false);

                gerer.afficherTournoi();
            }

            n++;
        }

        if (ModeTournoi.partie == 1) {
            System.out.println("Partie 4");

            if (game.getScorePlayerA() > game.getScorePlayerB()) {
                gerer.data.set(4, gerer.new Joueur("Le gagnant est JOUEUR 2", " "));
            } else {
                gerer.data.set(4, gerer.new Joueur("Le gagnat est JOUEUR 4", " "));
            }

            if (last) {
                int reelSC = Integer.parseInt(gerer.data.get(1).getScore()) + game.getScorePlayerA();
                int reelSC2 = Integer.parseInt(gerer.data.get(3).getScore()) + game.getScorePlayerB();

                gerer.data.set(1, gerer.new Joueur("Joueur 2", String.valueOf(reelSC)));
                gerer.data.set(3, gerer.new Joueur("Joueur 4", String.valueOf(reelSC2)));

                layout.sun.setVisible(false);
                layout.c2.setVisible(false);

                gerer.afficherTournoi();
                last = false;
            }

        }
    }

}
