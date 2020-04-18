package com.example.sensorssensibilities;

import java.util.TimerTask;

public class TimerTasks extends TimerTask {

    private MainActivity parent;

    public TimerTasks(MainActivity parent) {
        this.parent = parent;
    }

    public void run() {
        Shared.Data.sensorOutput = Shared.Data.sensorOutput + 1;
        parent.updateUI.sendEmptyMessage(0);
    }
}
