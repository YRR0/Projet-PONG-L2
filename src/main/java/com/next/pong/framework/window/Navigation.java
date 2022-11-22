package com.next.pong.framework.window;

import com.next.pong.framework.activity.Activity;

class Navigation {

    private static Activity<?> currentActivity = null;
    private static OnActivityChangeListener onActivityChangeListener = null;

    interface OnActivityChangeListener {
        void onChange(Activity<?> oldActivity, Activity<?> newActivity);
    }

    protected static void goTo(Activity<?> activity) {

        if (currentActivity != null) {
            currentActivity.destroy();
        }

        if (onActivityChangeListener != null) {
            onActivityChangeListener.onChange(currentActivity, activity);
        }

        currentActivity = activity;
    }

    protected static void update(double deltaMs) {

        if (currentActivity == null) {
            return;
        }

        currentActivity.update(deltaMs);
    }

    protected static void setOnActivityChangeListener(OnActivityChangeListener listener) {
        onActivityChangeListener = listener;
    }

}
