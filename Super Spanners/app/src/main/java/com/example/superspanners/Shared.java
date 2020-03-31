package com.example.superspanners;

public enum Shared
{
    Data;

    public final int THREAD_PAUSE = 1;

    public final int carsProcessRate = 2;
    public final int trucksProcessRate = 1;

    public int carsEnteringUS = 0;
    public int trucksEnteringUS = 0;

    public int numberOfCarEntryLanes = 0;
    public int numberOfTruckEntryLanes = 0;

    // lane status (index 0) and vehicle count (index 1) for each lane
    public int[][] laneInfo = {{0,0},{0,0},{0,0},{0,0},{0,0}};
}
