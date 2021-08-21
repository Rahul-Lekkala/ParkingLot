package com.parkinglot.command;

import com.parkinglot.exceptions.ParkingLotNotFoundException;
import com.parkinglot.model.Command;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.service.ParkingLotService;

import java.util.List;

public class GetSlotsWithDriverAge extends CommandExecutor {
    public GetSlotsWithDriverAge(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    //Slot_numbers_for_driver_of_age 21
    @Override
    public boolean isValid(Command command) {

        String[] commands = command.getCommands();  //21

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
        try{
            List<ParkingSpot> parkingSpotsWithDriverAge = parkingLotService.getOccupiedSpotsByDriverAge(driverAge);
            if(parkingSpotsWithDriverAge.size()==0)
            {
                System.out.println("No parked car matches the query");
            }else{
                for(int i=0;i<parkingSpotsWithDriverAge.size();i++)
                {
                    ParkingSpot parkingSpot = parkingSpotsWithDriverAge.get(i);
                    System.out.print(parkingSpot.getParkingSpotNumber());
                    if(i!=parkingSpotsWithDriverAge.size()-1)
                    {
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
