package com.rasalhague.commandsender.connection;

import java.io.IOException;

public class StateOpened implements ConnectionState
{

    @Override
    public void open(TCPConnection tcpConnection, Destination destination)
    {
        //TODO reopen when proxy different?

        System.err.println("Already opened");

        throw new IllegalStateException("TCPConnection Already opened");
    }

    @Override
    public void close(TCPConnection tcpConnection)
    {
        try
        {
            tcpConnection.socket.close();
            tcpConnection.socket = null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        tcpConnection.setConnectionState(new StateClosed());
        tcpConnection.notifyConnectionStateListeners(new ConnectionInfo(State.CLOSED, new Destination("", -1)));
    }
}
