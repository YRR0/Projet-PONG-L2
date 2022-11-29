package com.next.pong.framework.audio;

import com.next.pong.content.Resources;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;


public class Sound {

    Clip clip;
    HashMap<String, URL> sounds = new HashMap<>();
    //ArrayList<URL> sounds = new ArrayList<URL>();

    public Sound() {
        sounds.put("game", getClass().getResource("/com/next/pong/audio/game.wav"));
        sounds.put("kick", getClass().getResource("/com/next/pong/audio/kick.wav"));
        sounds.put("bounce", getClass().getResource("/com/next/pong/audio/bounce.wav"));
        sounds.put("update",getClass().getResource("/com/next/pong/audio/update.wav"));
        sounds.put("coin", getClass().getResource("/com/next/pong/audio/coin.wav"));
       // sounds.add(getClass().getResource("/com/next/pong/audio/coin.wav"));
    }

    public void setSound(String s) {
        URL soundUrl = sounds.get(s);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error on setting sound");
            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void playMusic(Resources.Music i) {
        setSound(String.valueOf(i));
        play();
        loop();
    }

    public void playSoundEffect(Resources.Music i) {
        setSound(String.valueOf(i));
        play();
    }

    public void stopMusic() {
        stop();
    }

}



