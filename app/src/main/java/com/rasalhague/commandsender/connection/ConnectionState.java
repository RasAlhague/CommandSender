package com.rasalhague.commandsender.connection;

public interface ConnectionState
{
    void open(final TCPConnection tcpConnection, Destination destination);

    void close(final TCPConnection tcpConnection);
}
