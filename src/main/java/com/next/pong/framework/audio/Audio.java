package com.next.pong.framework.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {

    private final MediaPlayer player;

    public Audio(String name, AudioExtension extension) {
        String path = "/com/next/pong/audio/" + name + '.' + extension.toString();
        Media media = new Media(getClass().getResource(path).toExternalForm());
        player = new MediaPlayer(media);
    }

    public void play() {
        player.play();
    }

    public void stop() {
        player.stop();
    }

    public void dispose() {
        player.dispose();
    }

}
