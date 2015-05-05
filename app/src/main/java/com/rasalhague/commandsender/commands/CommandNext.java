package com.rasalhague.commandsender.commands;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandNext implements Command
{
    private final String COMMAND = "Next\n";

    @Override
    public void perform(Socket socket)
    {
        try
        {
            new DataOutputStream(socket.getOutputStream()).writeBytes(COMMAND);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
