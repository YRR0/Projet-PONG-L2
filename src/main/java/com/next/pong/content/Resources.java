package com.next.pong.content;

public class Resources {

    public enum Layout {
        HOME("home");

        private final String text;

        Layout(String value) {
            this.text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

}
