package com.rasalhague.commandsender.connection;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCPConnection
{
    private ConnectionState connectionState;
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
        return socket != null && !socket.isConnected() && socket.isClosed();
    }

    public void open(Destination destination)
    {
        connectionState.open(this, destination);
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
