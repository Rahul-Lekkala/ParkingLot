package com.parkinglot.command;

import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;

import java.util.List;

public class GetVehiclesWithDriverAge extends CommandExecutor {
    public GetVehiclesWithDriverAge(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    //Vehicle_registration_number_for_driver_of_age 18
    @Override
    public boolean isValid(Command command) {
        String[] commands = command.getCommands();  //18

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
        int driverAge = Integer.parseInt(command.getCommands()[0]);
        try {
            List<Vehicle> vehiclesWithDriverAge = parkingLotService.getVehiclesByDriverAge(driverAge);
            if (vehiclesWithDriverAge.size() == 0) {
                System.out.println("No parked car matches the query");
            } else {
                for (int i = 0; i < vehiclesWithDriverAge.size(); i++) {
                    Vehicle vehicle = vehiclesWithDriverAge.get(i);
                    System.out.print(vehicle.getRegistrationNumber());
                    if (i != vehiclesWithDriverAge.size() - 1) {
                        System.out.print(",");
                    }
                }
                System.out.println();
            }
        }catch(ParkingLotNotFoundException exception){
            System.out.println("ParkingLot doesn't exist! Please create one!");
        }
    }
}
