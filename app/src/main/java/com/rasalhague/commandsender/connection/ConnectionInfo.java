package com.rasalhague.commandsender.connection;

public class ConnectionInfo
{
    private final State       state;
    private final Destination destination;

    public ConnectionInfo(State state, Destination destination)
    {
        this.state = state;
        this.destination = destination;
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
}
