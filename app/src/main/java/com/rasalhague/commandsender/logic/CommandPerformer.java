package com.rasalhague.commandsender.logic;

import com.rasalhague.commandsender.commands.Command;
import com.rasalhague.commandsender.commands.CommandNext;
import com.rasalhague.commandsender.commands.CommandPrevious;
import com.rasalhague.commandsender.connection.ConnectionInfo;
import com.rasalhague.commandsender.connection.State;
import com.rasalhague.commandsender.connection.TCPConnection;
import com.rasalhague.commandsender.volumebtncontrol.VolumeButton;
import com.rasalhague.commandsender.volumebtncontrol.VolumeButtonPressedListener;

import java.io.DataOutputStream;

public class CommandPerformer implements TCPConnection.ConnectionStateListener, VolumeButtonPressedListener
{
    Command command;
    private DataOutputStream outToServerStream;

    @Override
    public void onConnectionStateChanged(ConnectionInfo connectionInfo)
    {
        if (connectionInfo.getState() == State.OPENED)
        {
            outToServerStream = connectionInfo.getOutToServerStream();
        }
    }

    @Override
    public void onVolumeButtonPressed(VolumeButton volumeButton)
    {
        switch (volumeButton)
        {
            case UP:
                command = new CommandNext();
                break;

            case DOWN:
                command = new CommandPrevious();
                break;
        }

        if (outToServerStream != null && outToServerStream.)
        {
            command.perform(outToServerStream);
        }
    }
}
