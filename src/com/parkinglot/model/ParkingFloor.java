package com.parkinglot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        parkingSpots = new ArrayList<>();
    }
}
