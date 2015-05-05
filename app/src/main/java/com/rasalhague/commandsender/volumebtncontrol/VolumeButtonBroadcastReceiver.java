package com.rasalhague.commandsender.volumebtncontrol;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class VolumeButtonBroadcastReceiver extends WakefulBroadcastReceiver
{
    static BroadcastReceiverTarget receiver;

    static void setBroadcastReceiverTarget(BroadcastReceiverTarget receiverTarget)
    {
        receiver = receiverTarget;
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
}
