package com.parkinglot.input;

import com.parkinglot.command.CommandExecutor;
import com.parkinglot.command.CommandExecutorFactory;
import com.parkinglot.exceptions.InvalidCapacityException;
import com.parkinglot.exceptions.InvalidCommandException;
import com.parkinglot.model.Command;

import java.io.*;

public class InputReader {
    private CommandExecutorFactory commandExecutorFactory;
    private String fileName;

    public InputReader(CommandExecutorFactory commandExecutorFactory, String fileName)
    {
        this.commandExecutorFactory = commandExecutorFactory;
        this.fileName = fileName;
    }

    public void parse() throws IOException
    {
        File file = new File(fileName);
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException error){
            System.out.println("File Not Found");
            return;
        }

        String line = reader.readLine();
        while(line!=null)
        {
            try {
                Command command = new Command(line);
                process(command);
            }catch(InvalidCommandException exception)
            {
                System.out.println(exception);
            }
            line = reader.readLine();
        }
    }

    private void process(Command command)
    {
        CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if(commandExecutor.isValid(command)) {
            commandExecutor.execute(command);
        }else{
            throw new InvalidCommandException("Command Validation failed. Please enter valid command");
        }
    }
}
