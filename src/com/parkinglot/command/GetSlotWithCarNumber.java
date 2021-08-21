package com.parkinglot.command;

import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.service.ParkingLotService;

public class GetSlotWithCarNumber extends CommandExecutor {

    public GetSlotWithCarNumber(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    //Slot_number_for_car_with_number PB-01-HH-6789
    @Override
    public boolean isValid(Command command) {
        String[] commands = command.getCommands();  //PB-01-HH-6789

        return commands.length == 1;
    }

    @Override
    public void execute(Command command) {
        String registrationNumber = command.getCommands()[0];
        try {
            ParkingSpot parkingSpot = parkingLotService.getSpotByVehicleNumber(registrationNumber);
            if (parkingSpot == null) {
                System.out.println("No parked car matches the query");
            } else {
                System.out.println(parkingSpot.getParkingSpotNumber());
            }
        }
        catch(ParkingLotNotFoundException exception){
            System.out.println("ParkingLot doesn't exist! Please create one!");
        }
    }
}
