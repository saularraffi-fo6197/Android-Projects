package com.example.superspanners;

import android.util.Log;
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
        parent.updateUI.sendEmptyMessage(0);
        System.out.println("This is my message");
    }

    private void carsIn() {
        int openCarLanes = getOpenCarLaneCount();
        if (openCarLanes != 0) {
            int incomingCarsPerCarLane =  Shared.Data.carEntryRate / openCarLanes;
            populateCarLanes(incomingCarsPerCarLane);
        }
    }

    private void trucksIn() {
        int openTruckLanes = getOpenTruckLaneCount();
        if (openTruckLanes != 0) {
            int incomingTrucksPerCarLane =  Shared.Data.truckEntryRate / openTruckLanes;
            populateTruckLanes(incomingTrucksPerCarLane);
        }
    }

    private void carsOut() {
        int carsInQueue;
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 1 && Shared.Data.laneInfo[i][1] != 0) {
                carsInQueue = Shared.Data.laneInfo[i][1] - Shared.Data.carsProcessRate;
                if (carsInQueue <= 0)
                    Shared.Data.laneInfo[i][1] = 0;
                else
                    Shared.Data.laneInfo[i][1] = carsInQueue;
            }
        }
    }

    private void trucksOut() {
        int trucksInQueue;
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 2 && Shared.Data.laneInfo[i][1] != 0) {
                trucksInQueue = Shared.Data.laneInfo[i][1] - Shared.Data.trucksProcessRate;
                if (trucksInQueue <= 0)
                    Shared.Data.laneInfo[i][1] = 0;
                else
                    Shared.Data.laneInfo[i][1] = trucksInQueue;
            }
        }
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

    private void populateCarLanes(int carsPerCarLane) {
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 1)
                Shared.Data.laneInfo[i][1] = Shared.Data.laneInfo[i][1] + carsPerCarLane;
        }
    }

    private void populateTruckLanes(int trucksPerCarLane) {
        for (int i = 0; i < 5; i++) {
            if (Shared.Data.laneInfo[i][0] == 2)
                Shared.Data.laneInfo[i][1] = Shared.Data.laneInfo[i][1] + trucksPerCarLane;
        }
    }
}
