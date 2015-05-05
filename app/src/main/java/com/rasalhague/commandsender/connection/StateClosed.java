package com.rasalhague.commandsender.connection;

import java.io.IOException;
import java.net.Socket;

public class StateClosed implements ConnectionState
{
    @Override
    public void open(TCPConnection tcpConnection, Destination destination)
    {
        String ip = destination.getIP();
        int port = destination.getPort();

        try
        {
            tcpConnection.socket = new Socket(ip, port);
        }
        catch (IOException e)
        {
            tcpConnection.socket = null;
            tcpConnection.notifyConnectionStateListeners(new ConnectionInfo(State.FAILED,
                                                                            destination,
                                                                            tcpConnection.socket));

            e.printStackTrace();
        }

        if (tcpConnection.isOpen())
        {
            tcpConnection.setConnectionState(new StateOpened());
            tcpConnection.notifyConnectionStateListeners(new ConnectionInfo(State.OPENED,
                                                                            destination,
                                                                            tcpConnection.socket));
        }
    }

    @Override
    public void close(TCPConnection tcpConnection)
    {
        System.err.println("Already closed");

        throw new IllegalStateException("TCPConnection Already closed");
    }
}
