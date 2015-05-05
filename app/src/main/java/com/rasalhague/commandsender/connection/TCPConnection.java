package com.rasalhague.commandsender.connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPConnection
{
    private ConnectionState connectionState;
    private Destination     lastDestination;
    Socket socket;

    public TCPConnection()
    {
        this.connectionState = new StateClosed();
    }

    void setConnectionState(ConnectionState connectionState)
    {
        this.connectionState = connectionState;
    }

    public boolean isOpen()
    {
        return socket != null && !socket.isClosed() && socket.isConnected();
    }

    public boolean isClosed()
    {
        return socket == null || socket.isClosed();
    }

    public void open(final Destination destination)
    {
        lastDestination = destination;

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                connectionState.open(TCPConnection.this, destination);
            }
        });

        thread.setName("Connection thread");
        thread.setDaemon(true);
        thread.start();
    }

    public void reconnect()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                connectionState.open(TCPConnection.this, lastDestination);
            }
        });

        thread.setName("Connection thread");
        thread.setDaemon(true);
        thread.start();
    }

    public void close()
    {
        connectionState.close(this);
    }

    /**
     * Observer impl
     */
    private List<ConnectionStateListener> ConnectionStateListeners = new ArrayList<>();

    public void addConnectionStateListener(ConnectionStateListener observer)
    {
        ConnectionStateListeners.add(observer);
    }

    public void removeConnectionStateListener(ConnectionStateListener observer)
    {
        ConnectionStateListeners.remove(observer);
    }

    public void notifyConnectionStateListeners(ConnectionInfo connectionInfo)
    {
        for (ConnectionStateListener listener : ConnectionStateListeners)
        {
            listener.onConnectionStateChanged(connectionInfo);
        }
    }

    public interface ConnectionStateListener
    {
        void onConnectionStateChanged(ConnectionInfo connectionInfo);
    }
}
