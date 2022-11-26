package com.next.pong.framework.audio;

import com.next.pong.content.Resources;
import javafx.scene.media.AudioClip;

public class Clip {

    public static final int INDEFINITE = AudioClip.INDEFINITE;

    private final AudioClip clip;

    public Clip(Resources.Clip resource, int cycleCount) {
        var path = "com/next/pong/audio/" + resource + ".mp3";
        clip = new AudioClip(ClassLoader.getSystemResource(path).toExternalForm());
        clip.setCycleCount(cycleCount);
    }

    public void play() {
        clip.play();
    }

    public void stop() {
        clip.stop();
    }

}
