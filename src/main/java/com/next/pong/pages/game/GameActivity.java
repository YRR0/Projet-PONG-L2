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

public class GameActivity extends Activity<GameLayout> {

    private final Game game;
    private final Sound music;
    private boolean muted;
    private final Sound soundEffect;
    private GameTimer gameTimer = null;

    public GameActivity(boolean AI) {
        super(new GameLayout());

        int width = GameLayout.DEFAULT_WIDTH;
        int height = GameLayout.DEFAULT_HEIGHT;

        var ball = new Ball(
                new Vector2(0.5 * width, 0.5 * height),
                new Vector2((SettingsActivity.ballSpeedX() / 100) * width, (SettingsActivity.ballSpeedY() / 100) * height),
                10
        );

        var playerA = new Player(
                new Vector2(0.05 * width, 0.5 * height),
                new Vector2(0.0, 0.0),
                new Vector2(0.01 * width, 0.2 * height)
        );

        var positionB = new Vector2(0.95 * width, 0.5 * height);
        var speedB = new Vector2(0.0, 0.0);
        var sizeB = new Vector2(0.01 * width, 0.2 * height);

        var playerB = AI ? new ComputerPlayer(positionB, speedB, sizeB , ComputerPlayer.Level.HARD) :
                new Player(positionB, speedB, sizeB);

        game = new Game(width, height, ball, playerA, playerB);
        if(SettingsActivity.timerSet()) gameTimer = new GameTimer(SettingsActivity.timerMinutes(), SettingsActivity.timerSeconds());

        setupPlayerControl(playerA, SettingsActivity.up1(), SettingsActivity.down1());

        if (!(playerB instanceof ComputerPlayer)) {
            setupPlayerControl(playerB, SettingsActivity.up2(), SettingsActivity.down2());
        }

        setUpPause(KeyCode.ESCAPE, game, this);
        GameLayout g = this.layout;

        g.gamePause.reprendre.setOnMouseClicked(e -> {
            g.restoreOpa();
            g.gamePause.buttonConfigPauseStop();
            game.getCourt().pause = false;

            if (gameTimer != null && gameTimer.getMinutes() > 0 && (gameTimer.getSeconds() >= 0)) {
                gameTimer.start();
            }
        });

        g.gamePause.recommencer.setOnMouseClicked(e -> Window.goTo(new GameActivity(AI)));

        g.gamePause.acceuil.setOnMouseClicked(e -> Window.goTo(new HomeActivity()));

        g.gamePause.options.setOnMouseClicked(e -> Window.goTo(new SettingsActivity()));

        g.gamePause.quitter.setOnMouseClicked(e -> System.exit(0));

        g.nextGame.setOnMouseClicked(e -> Window.goTo(new GameActivity(AI)));

        music = new Sound();
        music.playMusic(Resources.Music.GAME);
        soundEffect = new Sound();

        game.setListener(new Game.Listener() {
            @Override
            public void onPlayerScored() {
                soundEffect.playSoundEffect(Resources.Music.UPDATE);
            }

            @Override
            public void onBallVerticalWallCollision(int id) {
                if(!muted) soundEffect.playSoundEffect(Resources.Music.BOUNCE);
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
                if(!muted) soundEffect.playSoundEffect(Resources.Music.KICK);
            }
        });

        // Timer mode
        if(gameTimer != null) gameTimer.start();
    }

    private void setUpPause(KeyCode pause, Game ga, GameActivity gaAc) {
        addKeyEventListener(pause, new KeyEventListener() {
            @Override
            public void onPressed() {
                if (ga.getCourt().pause) {
                    ga.getCourt().pause = false;
                    gaAc.layout.restoreOpa();
                    gaAc.layout.gamePause.buttonConfigPauseStop();
                    if(gameTimer != null && (gameTimer.getSeconds() >= 0) && (gameTimer.getMinutes() >= 0)) gameTimer.start();
                } else {
                    ga.getCourt().pause = true;
                    gaAc.layout.pauseOpacity();
                    gaAc.layout.gamePause.buttonConfigPause();
                    if(gameTimer != null) gameTimer.stop();
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

    @Override
    public void onUpdate(double deltaTime) {
        game.update(deltaTime);

        layout.setScore(game.getScorePlayerA(), game.getScorePlayerB());
        if(gameTimer != null) layout.setTime(gameTimer.getTime());

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

        if(gameTimer != null && gameTimer.update(deltaTime)) {
            game.getCourt().pause = true;
            int scoreA = game.getScorePlayerA();
            int scoreB = game.getScorePlayerB();
            if(scoreA > scoreB) this.layout.setWinner("Left Player has won the game");
            else if(scoreB > scoreA) this.layout.setWinner("Right Player has won the game");
            else this.layout.setWinner("Draw; No one has won the game");
        }

        if(SettingsActivity.scoreToWin() > 0) {
            if(game.getScorePlayerA() >= SettingsActivity.scoreToWin()) {
                game.getCourt().pause = true;
                this.layout.setWinner("Left Player has won the game");
            } else if(game.getScorePlayerB() >= SettingsActivity.scoreToWin()) {
                game.getCourt().pause = true;
                this.layout.setWinner("Right Player has won the game");
            }
        }
    }

    //------------------------------------------------------------------------

    private void setWinner(String winner) {
        layout.setWinner(winner);
    }

    private boolean gameEnded() {
        return (gameTimer.getMinutes() <= 0) && (gameTimer.getSeconds() <= 0);
    }

    private void mute() {
        muted = true;
        music.stopMusic();
    }

    private void unmute() {
        muted = false;
        music.playMusic(Resources.Music.GAME);
    }

    //------------------------------------------------------------------------

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

}
