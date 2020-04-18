package com.example.sensorssensibilities;

import java.util.TimerTask;

public class TimerTasks extends TimerTask {

    private MainActivity parent;

    public TimerTasks(MainActivity parent) {
        this.parent = parent;
    }

    public void run() {
        parent.updateUI.sendEmptyMessage(0);
    }
}
