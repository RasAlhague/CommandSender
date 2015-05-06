package com.rasalhague.commandsender.connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class StateClosed implements ConnectionState
{
    private final int NO_ROUTE_TO_HOST_ERRNO = 113;
    private final int CONNECTION_TIMEOUT     = 500;

    @Override
    public void open(TCPConnection tcpConnection, Destination destination)
    {
        String ip = destination.getIP();
        int port = destination.getPort();

        try
        {
            tcpConnection.socket = new Socket();
            tcpConnection.socket.connect(new InetSocketAddress(ip, port), CONNECTION_TIMEOUT);
        }
        catch (IOException e)
        {
//            int errno = ((ErrnoException) e.getCause()).errno;
//            if (errno == NO_ROUTE_TO_HOST_ERRNO) tcpConnection.socket = null;
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
