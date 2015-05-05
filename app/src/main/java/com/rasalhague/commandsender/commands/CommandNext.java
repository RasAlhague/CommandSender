package com.rasalhague.commandsender.commands;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandNext implements Command
{
    private final String COMMAND = "Next\n";

    @Override
    public void perform(DataOutputStream outToServer)
    {
        try
        {
            outToServer.writeBytes(COMMAND);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
