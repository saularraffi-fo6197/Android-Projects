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
    }

    private void trucksIn() {
        System.out.println("trucks in");
    }

    private void carsOut() {
        System.out.println("cars out");
    }

    private void trucksOut() {
        System.out.println("trucks out");
    }
}
