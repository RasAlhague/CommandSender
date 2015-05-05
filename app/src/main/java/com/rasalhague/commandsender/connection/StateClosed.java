package com.rasalhague.commandsender.connection;

import java.io.DataOutputStream;
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
            tcpConnection.outToServerStream = new DataOutputStream(tcpConnection.socket.getOutputStream());
        }
        catch (IOException e)
        {
            tcpConnection.socket = null;
            tcpConnection.notifyConnectionStateListeners(new ConnectionInfo(State.FAILED,
                                                                            destination,
                                                                            tcpConnection.outToServerStream));

            e.printStackTrace();
        }

        if (tcpConnection.isOpen())
        {
            tcpConnection.setConnectionState(new StateOpened());
            tcpConnection.notifyConnectionStateListeners(new ConnectionInfo(State.OPENED,
                                                                            destination,
                                                                            tcpConnection.outToServerStream));
        }
    }

    @Override
    public void close(TCPConnection tcpConnection)
    {
        System.err.println("Already closed");

        throw new IllegalStateException("TCPConnection Already closed");
    }
}
