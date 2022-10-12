package com.next.pong.framework.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActivityPayload {

    private final Map<String, Serializable> data;

    public ActivityPayload() {
        data = new HashMap<>();
    }

    public void putString(String key, String value) {
        data.put(key, value);
    }

    public String getString(String key) {
        return data.get(key).toString();
    }

    public void putInt(String key, int value) {
        data.put(key, value);
    }

    public int getInt(String key) {
        return (Integer) data.get(key);
    }

    public void putFloat(String key, float value) {
        data.put(key, value);
    }

    public float getFloat(String key) {
        return (Float) data.get(key);
    }

}
