package com.rasalhague.commandsender.volumebtncontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class VolumeButtonBroadcastReceiver extends BroadcastReceiver
{
    private static BroadcastReceiverTarget receiver;
    private static VolumeButtonBroadcastReceiver instance = new VolumeButtonBroadcastReceiver();

    private VolumeButtonBroadcastReceiver() { }

    public static VolumeButtonBroadcastReceiver getInstance()
    {
        return instance;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        receiver.onReceive(context, intent);
    }

    interface BroadcastReceiverTarget
    {
        void onReceive(Context context, Intent intent);
    }

    static void setBroadcastReceiverTarget(BroadcastReceiverTarget receiverTarget)
    {
        receiver = receiverTarget;
    }
}
