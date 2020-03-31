//====================================================================
//
// Application: Reservation Roster
// Class:       Shared
// Description:
//   This Android application class holds data shared among threads.
//
//====================================================================
package com.example.superspanners;

//--------------------------------------------------------------------
// enum Shared
//--------------------------------------------------------------------
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

    public int[][] laneInfo = {{0,0},{0,0},{0,0},{0,0},{0,0}};
}
