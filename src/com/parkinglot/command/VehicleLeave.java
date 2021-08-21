package com.parkinglot.command;

import com.parkinglot.exceptions.InvalidSpotException;
import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.service.ParkingLotService;

public class VehicleLeave extends CommandExecutor {
    public VehicleLeave(ParkingLotService parkingLotService){
        super(parkingLotService);
    }

    //Leave 2
    @Override
    public boolean isValid(Command command) {
        String[] commands = command.getCommands();  //2

        if(commands.length != 1)
            return false;

        try {
            Integer.parseInt(commands[0]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void execute(Command command) {
        int parkingSpotNumber = Integer.parseInt(command.getCommands()[0]);
        try {
            ParkingSpot parkingSpot = parkingLotService.getParkingSpot(parkingSpotNumber);
            if(parkingSpot == null){
                throw new NullPointerException();
            }
            String registrationNumber = parkingSpot.getParkedVehicle().getRegistrationNumber();
            int driverAge = parkingSpot.getParkedVehicle().getDriverAge();
            parkingLotService.unPark(parkingSpotNumber);
            System.out.println("Slot number " + parkingSpot.getParkingSpotNumber() + " vacated, " +
                    "the car with vehicle registration number \"" + registrationNumber + "\" " +
                    "left the space, the driver of the car was of age " + driverAge);
        }
        catch(InvalidSpotException exception){
            System.out.println("No slot exists with given slot number");
        }catch(NullPointerException nullPointerException){
            System.out.println("No slot exists with given slot number");
        }catch(ParkingLotNotFoundException exception){
            System.out.println("ParkingLot doesn't exist! Please create one!");
        }
    }
}
