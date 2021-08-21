package com.parkinglot.command;

import com.parkinglot.exceptions.NoSpotsAvailableException;
import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;

public class ParkVehicle extends CommandExecutor {
    public ParkVehicle(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    //Park KA-01-HH-1234 driver_age 21
    @Override
    public boolean isValid(Command command) {
        String[] commands = command.getCommands();  //KA-01-HH-1234 driver_age 21

        if(commands.length != 3)
            return false;

        try {
            Integer.parseInt(commands[2]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void execute(Command command) {
        String registrationNumber = command.getCommands()[0];
        int driverAge = Integer.parseInt(command.getCommands()[2]);

        try {
            ParkingSpot parkingSpot = parkingLotService.park(new Vehicle(registrationNumber, driverAge));
            System.out.println("Car with vehicle registration number \"" + registrationNumber + "\" " +
                    "has been parked at slot number " + parkingSpot.getParkingSpotNumber());
        }
        catch(NoSpotsAvailableException excetion){
            System.out.println("ParkingLot is full! Please visit at another time!");
        }catch(ParkingLotNotFoundException exception){
            System.out.println("ParkingLot doesn't exist! Please create one!");
        }
    }
}
