package com.next.pong.pages.game;

import com.next.pong.content.Resources;
import com.next.pong.framework.audio.Sound;
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
    }

    private boolean isAlive;

    public void start() {
        isAlive = true;
    }

    public void stop() {
        isAlive = false;
    }

    private double accTime;

    /**
     *
     * @param deltaTime
     * @return true if the timer has reached 00:00 and false either
     */
    public boolean update(double deltaTime) {
        accTime += deltaTime;

        // execute update every 1 second
        if(accTime < 1 || !isAlive) {
            return false;
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
            new Sound().playSoundEffect(Resources.Music.COUNTDOWN);
        }

        text.setText(formatedMinutes + ":" + formatedSeconds);

        if (seconds == 0 && minutes == 0) {
            return true;
        }
        return false;
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

}

