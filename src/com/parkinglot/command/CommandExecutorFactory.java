package com.parkinglot.command;

import com.parkinglot.exceptions.InvalidCommandException;
import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;

public class CommandExecutorFactory {

    private ParkingLotService parkingLotService;

    public CommandExecutorFactory(ParkingLotService parkingLotService)
    {
        this.parkingLotService = parkingLotService;
    }

    public CommandExecutor getCommandExecutor(Command command)
    {
        CommandExecutor commandExecutor;

        switch(command.getCommandName())
        {
            case "create_parking_lot":
                commandExecutor = new CreateParkingLot(parkingLotService);
                break;

            case "park":
                commandExecutor = new ParkVehicle(parkingLotService);
                break;

            case "leave":
                commandExecutor = new VehicleLeave(parkingLotService);
                break;

            case "slot_numbers_for_driver_of_age":
                commandExecutor = new GetSlotsWithDriverAge(parkingLotService);
                break;

            case "slot_number_for_car_with_number":
                commandExecutor = new GetSlotWithCarNumber(parkingLotService);
                break;

            case "vehicle_registration_number_for_driver_of_age":
                commandExecutor = new GetVehiclesWithDriverAge(parkingLotService);
                break;

            default:
                    throw new InvalidCommandException("Command Not Found");
        }

        return commandExecutor;
    }

}
