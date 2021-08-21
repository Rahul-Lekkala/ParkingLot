package com.parkinglot.model;

public class ParkingSpot {
    private Vehicle parkedVehicle;
    private Integer parkingSpotNumber;
    private ParkingSpotStatus parkingSpotStatus;

    public ParkingSpot(Integer parkingSpotNumber)
    {
        this.parkingSpotNumber = parkingSpotNumber;
        this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
    }

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public Integer getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public boolean isSpotFree() {
        return parkingSpotStatus == ParkingSpotStatus.AVAILABLE;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.parkingSpotStatus = ParkingSpotStatus.OCCUPIED;
    }

    public void unParkVehicle() {
        this.parkedVehicle = null;
        this.parkingSpotStatus = ParkingSpotStatus.AVAILABLE;
    }
}
