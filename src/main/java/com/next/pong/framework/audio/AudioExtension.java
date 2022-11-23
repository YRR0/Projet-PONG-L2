package com.next.pong.framework.audio;

public enum AudioExtension {
    MP3("mp3");

    private final String value;

    AudioExtension(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

