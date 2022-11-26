package com.next.pong.framework.audio;

import com.next.pong.content.Resources;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

    private final MediaPlayer player;

    public Music(Resources.Music resource) {
        var path = "com/next/pong/audio/" + resource + ".mp3";
        var media = new Media(ClassLoader.getSystemResource(path).toExternalForm());
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
