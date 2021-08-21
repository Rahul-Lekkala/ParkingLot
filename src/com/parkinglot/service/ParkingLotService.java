package com.parkinglot.service;
import com.parkinglot.exceptions.ParkingLotAlreadyExistsException;
import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotService {
    private ParkingLot parkingLot;

    public void createParkingLot(ParkingLot parkingLot)
    {
        if (this.parkingLot != null) {
            throw new ParkingLotAlreadyExistsException();
        }
        this.parkingLot = parkingLot;
    }

    public ParkingSpot park(Vehicle vehicle)
    {
        if (parkingLot == null) {
            throw new ParkingLotNotFoundException();
        }

        ParkingSpot nextAvailableSpot = parkingLot.getNextAvailableSpot();
        parkingLot.park(vehicle,nextAvailableSpot.getParkingSpotNumber());
        return nextAvailableSpot;
    }

    public void unPark(Integer parkingSpotNumber)
    {
        if (parkingLot == null) {
            throw new ParkingLotNotFoundException();
        }
        parkingLot.unPark(parkingSpotNumber);
    }

    private List<ParkingSpot> getOccupiedSpots()
    {
        if (parkingLot == null) {
            throw new ParkingLotNotFoundException();
        }
        List<ParkingSpot> occupiedSpots = new ArrayList<>();
        List<ParkingSpot> allParkingSpots = parkingLot.getParkingSpots();
        for(int parkingSpotNumber=0; parkingSpotNumber<parkingLot.getCapacity(); parkingSpotNumber++)
        {
            //if(allParkingSpots.contains(parkingSpotNumber))
            {
                ParkingSpot parkingSpot = allParkingSpots.get(parkingSpotNumber);
                if(!parkingSpot.isSpotFree())
                {
                    occupiedSpots.add(parkingSpot);
                }
            }
        }
        return occupiedSpots;
    }

    public List<ParkingSpot> getOccupiedSpotsByDriverAge(int driverAge)
    {
        if (parkingLot == null) {
            throw new ParkingLotNotFoundException();
        }
        List<ParkingSpot> occupiedSpots = new ArrayList<>();
        List<ParkingSpot> allParkingSpots = parkingLot.getParkingSpots();
        for(int parkingSpotNumber=1;parkingSpotNumber<=parkingLot.getCapacity();parkingSpotNumber++)
        {
            if(allParkingSpots.contains(parkingSpotNumber))
            {
                ParkingSpot parkingSpot = allParkingSpots.get(parkingSpotNumber);
                if(parkingSpot.getParkedVehicle()!=null) {
                    if (parkingSpot.getParkedVehicle().getDriverAge() == driverAge) {
                        occupiedSpots.add(parkingSpot);
                    }
                }
            }
        }
        return occupiedSpots;
    }

    public ParkingSpot getSpotByVehicleNumber(String registrationNumber)
    {
        ParkingSpot parkingSpot = null;
        List<ParkingSpot> occupiedParkingSpots = getOccupiedSpots();
        for(ParkingSpot occupiedParkingSpot:occupiedParkingSpots)
        {
            if(occupiedParkingSpot.getParkedVehicle()!=null) {
                if(occupiedParkingSpot.getParkedVehicle().getRegistrationNumber().equals(registrationNumber))
                {
                    parkingSpot = occupiedParkingSpot;
                    break;
                }
            }
        }
        return parkingSpot;
    }

    public List<Vehicle> getVehiclesByDriverAge(int driverAge)
    {
        List<ParkingSpot> occupiedParkingSpots = getOccupiedSpots();
        List<Vehicle> vehicles = new ArrayList<>();
        for(ParkingSpot parkingSpot:occupiedParkingSpots)
        {
            if(parkingSpot.getParkedVehicle().getDriverAge() == driverAge)
            {
                vehicles.add(parkingSpot.getParkedVehicle());
            }
        }
        return vehicles;
    }

    public ParkingSpot getParkingSpot(Integer parkingSpotNumber)
    {
        return parkingLot.getParkingSpot(parkingSpotNumber);
    }
}
