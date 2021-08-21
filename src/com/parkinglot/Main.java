package com.parkinglot;

import com.parkinglot.command.CommandExecutorFactory;
import com.parkinglot.input.InputReader;
import com.parkinglot.service.ParkingLotService;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        ParkingLotService parkingLotService = new ParkingLotService();
        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(parkingLotService);

        InputReader inputReader = new InputReader(commandExecutorFactory, args[0]);
        inputReader.parse();
    }
}
