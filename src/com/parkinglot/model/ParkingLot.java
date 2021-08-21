package com.parkinglot.model;

import com.parkinglot.exceptions.InvalidCapacityException;
import com.parkinglot.exceptions.InvalidSpotException;
import com.parkinglot.exceptions.NoSpotsAvailableException;
import com.parkinglot.exceptions.ParkingSpotOccupiedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<Integer, ParkingSpot> parkingSpots;
    private List<ParkingSpot> parkingSlots;

    public ParkingLot(int capacity)
    {
        if(capacity<=0)
        {
            throw new InvalidCapacityException("Capacity of the parking lot cannot be less than 0");
        }

        this.capacity = capacity;
        this.parkingSpots = new HashMap<>();
        this.parkingSlots = new ArrayList<>();
        for(int spotNumber = 1; spotNumber<=capacity;spotNumber++)
        {
            this.parkingSpots.put(spotNumber,new ParkingSpot(spotNumber));
        }

        for(int spotNumber = 1; spotNumber <= capacity; spotNumber++)
        {
            this.parkingSlots.add(new ParkingSpot(spotNumber));
        }
    }

    public int getCapacity()
    {
        return capacity;
    }

    public List<ParkingSpot> getParkingSpots(){
        return parkingSlots;
    }

    public ParkingSpot getParkingSpot(Integer parkingSpotNumber)
    {
        for(ParkingSpot parkingSpot:parkingSlots)
        {
            if(parkingSpot.getParkingSpotNumber() == parkingSpotNumber)
            {
                return parkingSpot;
            }
        }

        throw new InvalidSpotException();

    }

    public ParkingSpot park(Vehicle vehicle, Integer parkingSpotNumber)
    {
        ParkingSpot parkingSpot = getParkingSpot(parkingSpotNumber);
        if(!parkingSpot.isSpotFree()){
            throw new ParkingSpotOccupiedException();
        }
        parkingSpot.parkVehicle(vehicle);
        return parkingSpot;
    }

    public ParkingSpot unPark(Integer parkingSpotNumber)
    {
        ParkingSpot parkingSpot = getParkingSpot(parkingSpotNumber);
        parkingSpot.unParkVehicle();
        return parkingSpot;
    }

    public ParkingSpot getNextAvailableSpot()
    {
        ParkingSpot nextFreeSpot = null;

        for(ParkingSpot parkingSpot:parkingSlots)
        {
            if(parkingSpot.isSpotFree())
            {
                nextFreeSpot = parkingSpot;
                break;
            }
        }
        if(nextFreeSpot==null)
        {
            throw new NoSpotsAvailableException();
        }
        return nextFreeSpot;
    }

}
