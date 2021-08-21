package com.parkinglot.command;

import com.parkinglot.model.Command;
import com.parkinglot.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;

    public CommandExecutor(ParkingLotService parkingLotService)
    {
        this.parkingLotService = parkingLotService;
    }

    //Validates the command
    public abstract boolean isValid(Command command);

    //Executes the command
    public abstract void execute(Command command);
}
