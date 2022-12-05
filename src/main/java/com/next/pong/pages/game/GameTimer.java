package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.game.Game;
import javafx.scene.text.Text;

import javax.swing.*;
import java.text.DecimalFormat;

public class GameTimer {

    Timer timer;
    private int seconds;
    private int minutes;
    private Text text;
    private DecimalFormat df = new DecimalFormat("00");
    private Game game;

    public GameTimer(Game game, int m, int s) {
        this.minutes = m;
        this.seconds = s;

        text = new Text(df.format(minutes) + ":" + df.format(seconds));
        text.setId("chrono");

        timer = new Timer(1000, e -> {
            seconds--;
            String formatedSeconds = df.format(seconds);
            String formatedMinutes = df.format(minutes);
            if (seconds == -1) {
                seconds = 59;
                minutes--;
                formatedSeconds = df.format(seconds);
                formatedMinutes = df.format(minutes);
            }

            if (minutes == 0 && seconds == 3) {
                // se.playSoundEffect(Resources.Music.COUNTDOWN); TODO: Sound effect
            }

            text.setText(formatedMinutes + ":" + formatedSeconds);

            if (seconds == 0 && minutes == 0) {
                getWinner();
            }
        });
    }

    public Text getTime() {
        return text;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void startGameTimer() {
        timer.start();
    }

    public void stopGameTimer() {
        timer.stop();
    }

    // TODO: What????
    private boolean vtimer = false;

    public boolean getVTimer() {
        return vtimer;
    }

    public void setVtimer(boolean vtimer) {
        this.vtimer = vtimer;
    }

    private void getWinner() {
        timer.stop();

        int scoreA = game.getScorePlayerA(), scoreB = game.getScorePlayerB();

        if (scoreA > scoreB) {
            text.setText("PlayerA wins");
        } else if (scoreA == scoreB) {
            text.setText("Draw");
        } else {
            text.setText("PlayerB wins");
        }

        game.getCourt().pause = true;
        vtimer = game.getCourt().pause;
    }
}

