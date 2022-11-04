package com.next.pong;

import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    ArrayList<URL> sounds = new ArrayList<URL>();

    public Sound() {
        sounds.add(getClass().getResource("/com.next.pong/audio/bounce.wav"));
        sounds.add(getClass().getResource("/com.next.pong/audio/kick.wav"));
        sounds.add(getClass().getResource("/com.next.pong/audio/game.wav"));
        sounds.add(getClass().getResource("/com.next.pong/audio/update.wav"));
    }

    public void setSound(int i) {
        URL soundUrl = sounds.get(i);
        System.out.println(soundUrl);
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

    public void playMusic(int i) {
        setSound(i);
        play();
        loop();
    }

    public void playSoundEffect(int i) {
        setSound(i);
        play();
    }

    public void stopMusic() {
        stop();
    }
    
}
