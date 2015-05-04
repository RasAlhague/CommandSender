package com.rasalhague.commandsender;

import android.test.AndroidTestCase;
import com.rasalhague.commandsender.connection.*;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ConnectionTest extends AndroidTestCase
{
    TCPConnection tcpConnection;
    Destination   destination;
    SocketAddress socketAddress;
    final static String HOST = "192.168.1.10";
    final static int    PORT = 4444;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();

        tcpConnection = new TCPConnection();

        socketAddress = new InetSocketAddress(HOST, PORT);
        destination = new Destination(HOST, PORT);
    }

    @Override
    public void tearDown() throws Exception
    {
        super.tearDown();

        if (tcpConnection.isOpen())
        {
            tcpConnection.close();
        }
    }

    public void testConnectionOpen() throws Exception
    {
        final ConnectionInfo[] connectionI = new ConnectionInfo[1];
        tcpConnection.addConnectionStateListener(new TCPConnection.ConnectionStateListener()
        {
            @Override
            public void onConnectionStateChanged(ConnectionInfo connectionInfo)
            {
                connectionI[0] = connectionInfo;
            }
        });

        tcpConnection.open(destination);

        assertNotNull(connectionI[0]);

        assertEquals(connectionI[0].getIP(), HOST);
        assertEquals(connectionI[0].getPort(), PORT);

        assertEquals(tcpConnection.isOpen(), true);
    }

    public void testIllegalStateException() throws Exception
    {
        IllegalStateException stateException = null;
        try
        {
            tcpConnection.open(destination);

            //getting exception
            tcpConnection.open(destination);
        }
        catch (IllegalStateException e)
        {
            stateException = e;
        }

        assertNotNull(stateException);
    }

    public void testStateClassOpen() throws Exception
    {
        tcpConnection.open(destination);

        Field connectionStateField = tcpConnection.getClass().getDeclaredField("connectionState");
        connectionStateField.setAccessible(true);
        Object o = connectionStateField.get(tcpConnection);

        assertEquals(o.getClass(), StateOpened.class);
    }

    public void testStateClassClosed() throws Exception
    {
        tcpConnection.open(destination);
        tcpConnection.close();

        Field connectionStateField = tcpConnection.getClass().getDeclaredField("connectionState");
        connectionStateField.setAccessible(true);
        Object o = connectionStateField.get(tcpConnection);

        assertEquals(o.getClass(), StateClosed.class);
    }

}
