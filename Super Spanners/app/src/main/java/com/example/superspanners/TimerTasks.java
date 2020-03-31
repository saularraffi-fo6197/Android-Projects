package com.example.superspanners;

import android.widget.TextView;

import java.util.TimerTask;

public class TimerTasks extends TimerTask {

    private MainActivity parent;

    public TimerTasks(MainActivity parent) {
        this.parent = parent;
    }

    public void run() {
        carsIn();
        trucksIn();
        carsOut();
        trucksOut();
    }

    private void carsIn() {
        System.out.println("cars in");
        int incomingCarsPerCarLane = getOpenCarLaneCount() / Shared.Data.carEntryRate;
    }

    private void trucksIn() {
        System.out.println("trucks in");
        int incomingTrucksPerCarLane = getOpenTruckLaneCount() / Shared.Data.truckEntryRate;
    }

    private void carsOut() {
        System.out.println("cars out");
    }

    private void trucksOut() {
        System.out.println("trucks out");
    }

    private int getOpenCarLaneCount() {
        int totalCarLanes = 0;
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 1)
                totalCarLanes = totalCarLanes + 1;
        }
        return totalCarLanes;
    }

    private int getOpenTruckLaneCount() {
        int totalTruckLanes = 0;
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 2)
                totalTruckLanes = totalTruckLanes + 1;
        }
        return totalTruckLanes;
    }
}
