package com.rasalhague.commandsender.connection;

import java.net.Socket;

public class ConnectionInfo
{
    private final State       state;
    private final Destination destination;
    private final Socket      socket;

    public ConnectionInfo(State state, Destination destination, Socket socket)
    {
        this.state = state;
        this.destination = destination;
        this.socket = socket;
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

    public Socket getSocket()
    {
        return socket;
    }

    @Override
    public String toString()
    {
        return "state = " + state +
                "\nIP = " + destination.getIP() +
                "\nport = " + destination.getPort();
    }
}
