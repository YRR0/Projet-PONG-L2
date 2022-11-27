package com.next.pong.content;

public class Resources {

    public enum Layout {
        HOME("home"),
        SETTINGS("settings");

        private final String text;

        Layout(String value) {
            this.text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum Music {
        IMPERIAL_MARCH("imperial_march");

        private final String text;

        Music(String value) {
            this.text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum Clip {
        ;

        private final String text;

        Clip(String value) {
            this.text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
