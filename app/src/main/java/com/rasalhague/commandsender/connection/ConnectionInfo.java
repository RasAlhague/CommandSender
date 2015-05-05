package com.rasalhague.commandsender.connection;

import java.io.DataOutputStream;

public class ConnectionInfo
{
    private final State            state;
    private final Destination      destination;
    private final DataOutputStream outToServerStream;

    public ConnectionInfo(State state, Destination destination, DataOutputStream outToServerStream)
    {
        this.state = state;
        this.destination = destination;
        this.outToServerStream = outToServerStream;
    }

    public State getState()
    {
        return state;
    }

    public String getIP()
    {
        return destination.getIP();
    }

    public int getPort()
    {
        return destination.getPort();
    }

    public DataOutputStream getOutToServerStream()
    {
        return outToServerStream;
    }

    @Override
    public String toString()
    {
        return "state = " + state +
                "\nIP = " + destination.getIP() +
                "\nport = " + destination.getPort();
    }
}
