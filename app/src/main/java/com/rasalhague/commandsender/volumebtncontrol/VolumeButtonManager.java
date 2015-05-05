package com.rasalhague.commandsender.volumebtncontrol;

import android.content.Context;
import android.content.Intent;
import com.rasalhague.commandsender.connection.ConnectionInfo;
import com.rasalhague.commandsender.connection.State;
import com.rasalhague.commandsender.connection.TCPConnection;

import java.util.ArrayList;
import java.util.List;

public class VolumeButtonManager implements TCPConnection.ConnectionStateListener, VolumeButtonBroadcastReceiver.BroadcastReceiverTarget
{
    private BlankInfinityPlayer blankInfinityPlayer;

    public VolumeButtonManager()
    {
        this.blankInfinityPlayer = new BlankInfinityPlayer();

        VolumeButtonBroadcastReceiver.setBroadcastReceiverTarget(this);
    }

    private List<VolumeButtonPressedListener> volumeButtonPressedListeners = new ArrayList<VolumeButtonPressedListener>();

    public void addVolumeButtonPressedListener(VolumeButtonPressedListener observer)
    {
        volumeButtonPressedListeners.add(observer);
    }

    public void removeVolumeButtonPressedListener(VolumeButtonPressedListener observer)
    {
        volumeButtonPressedListeners.remove(observer);
    }

    public void notifyVolumeButtonPressedListeners(VolumeButton volumeButton)
    {
        for (VolumeButtonPressedListener listener : volumeButtonPressedListeners)
        {
            listener.onVolumeButtonPressed(volumeButton);
        }
    }

    @Override
    public void onConnectionStateChanged(ConnectionInfo connectionInfo)
    {
        State state = connectionInfo.getState();

        if (state == State.OPENED)
        {
            blankInfinityPlayer.start();
        }

        if (state == State.CLOSED)
        {
            blankInfinityPlayer.stop();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        notifyVolumeButtonPressedListeners(VolumeButton.UP);
    }
}
