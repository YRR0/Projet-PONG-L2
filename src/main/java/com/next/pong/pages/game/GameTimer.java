package com.next.pong.pages.game;

import com.next.pong.game.Game;
import javafx.scene.text.Text;

import java.text.DecimalFormat;

public class GameTimer {

    private int seconds;
    private int minutes;
    private final Text text;
    private final DecimalFormat df = new DecimalFormat("00");

    public GameTimer(int m, int s) {
        this.minutes = m;
        this.seconds = s;

        text = new Text(df.format(minutes) + ":" + df.format(seconds));
        text.setId("chrono");
    }

    private boolean isAlive;

    public void start() {
        isAlive = true;
    }

    public void stop() {
        isAlive = false;
    }

    private double accTime;

    public void update(double deltaTime, Game game) {
        accTime += deltaTime;

        // execute update every 1 second
        if(accTime < 1 && isAlive) {
            return;
        }

        accTime = 0;

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
            getWinner(game);
        }
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

    private void getWinner(Game game) {
        int scoreA = game.getScorePlayerA(), scoreB = game.getScorePlayerB();

        if (scoreA > scoreB) {
            text.setText("PlayerA wins");
        } else if (scoreA == scoreB) {
            text.setText("Draw");
        } else {
            text.setText("PlayerB wins");
        }
        game.getCourt().pause = true;
    }
}

