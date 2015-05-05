package com.rasalhague.commandsender.commands;

import java.io.DataOutputStream;
import java.io.IOException;

public class CommandPrevious implements Command
{
    private final String COMMAND = "Preview\n";

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
