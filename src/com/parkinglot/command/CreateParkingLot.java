package com.parkinglot.command;

import com.parkinglot.exceptions.ParkingLotAlreadyExistsException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.service.ParkingLotService;

public class CreateParkingLot extends CommandExecutor {

    CreateParkingLot(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    //Create_parking_lot 6
    @Override
    public boolean isValid(Command command) {
        String[] commands = command.getCommands();  //6

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
        int capacity = Integer.parseInt(command.getCommands()[0]);
        ParkingLot parkingLot = new ParkingLot(capacity);
        try {
            parkingLotService.createParkingLot(parkingLot);
            System.out.println("Created parking of " + capacity + " slots");
        }catch(ParkingLotAlreadyExistsException exception){
            System.out.println("Parking lot already exists");
        }
    }
}
