package com.rasalhague.commandsender.connection;

public class Destination
{
    private final String IP;
    private final int    port;

    public Destination(String IP, int port)
    {
        this.IP = IP;
        this.port = port;
    }

    public String getIP()
    {
        return IP;
    }

    public int getPort()
    {
        return port;
    }

    @Override
    public String toString()
    {
        return "Destination{" +
                "IP='" + IP + '\'' +
                ", port=" + port +
                '}';
    }
}
