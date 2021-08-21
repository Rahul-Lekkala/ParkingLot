package com.parkinglot.model;

import com.parkinglot.exceptions.InvalidCommandException;

public class Command {
    private String commandName;
    private String[] commands;

    public String getCommandName()
    {
        return commandName;
    }

    public String[] getCommands()
    {
        return commands;
    }

    public Command(String commandFromFile)
    {
        //Split the space separated single-line command
        String[] command = commandFromFile.split(" ");
        if(command.length < 2)
        {
            throw new InvalidCommandException("Command not specified");
        }

        commandName = command[0].toLowerCase();
        commands = new String[command.length-1];

        //Copy commands given from file to local commands[]
        for(int i=1;i<command.length;i++)
            commands[i-1] = command[i];

//        for(int i=0;i<command.length-1;i++)
//            System.out.println(commands[i]);
    }
}
