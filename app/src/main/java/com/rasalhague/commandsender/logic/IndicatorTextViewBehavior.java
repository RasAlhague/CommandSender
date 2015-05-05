package com.rasalhague.commandsender.logic;

import com.rasalhague.commandsender.connection.ConnectionInfo;
import com.rasalhague.commandsender.connection.TCPConnection;
import com.rasalhague.commandsender.gui.IndicatorTextView;

public class IndicatorTextViewBehavior implements TCPConnection.ConnectionStateListener
{
    private final IndicatorTextView indicatorTextView;

    public IndicatorTextViewBehavior(IndicatorTextView indicatorTextView) {this.indicatorTextView = indicatorTextView;}

    @Override
    public void onConnectionStateChanged(ConnectionInfo connectionInfo)
    {
        indicatorTextView.setText(connectionInfo.toString());
    }
}
