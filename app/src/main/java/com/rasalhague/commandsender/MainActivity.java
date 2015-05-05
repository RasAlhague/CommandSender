package com.rasalhague.commandsender;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;
import com.rasalhague.commandsender.connection.TCPConnection;
import com.rasalhague.commandsender.gui.IPEditText;
import com.rasalhague.commandsender.gui.IndicatorTextView;
import com.rasalhague.commandsender.logic.CommandPerformer;
import com.rasalhague.commandsender.logic.IndicatorTextViewBehavior;
import com.rasalhague.commandsender.logic.TCPConnectionOnIPChangedHandler;
import com.rasalhague.commandsender.volumebtncontrol.VolumeButtonManager;

public class MainActivity extends ActionBarActivity
{
    TCPConnection                   tcpConnection;
    CommandPerformer                commandPerformer;
    IPEditText                      ipEditText;
    VolumeButtonManager             volumeButtonManager;
    TCPConnectionOnIPChangedHandler tcpConnectionOnIPChangedHandler;
    IndicatorTextView               indicatorTextView;
    IndicatorTextViewBehavior       indicatorTextViewBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tcpConnection = new TCPConnection();
        commandPerformer = new CommandPerformer();
        ipEditText = new IPEditText((EditText) findViewById(R.id.editText_IP));
        indicatorTextView = new IndicatorTextView((TextView) findViewById(R.id.textView_connectionStatus), this);
        indicatorTextViewBehavior = new IndicatorTextViewBehavior(indicatorTextView);
        volumeButtonManager = new VolumeButtonManager();

        registerRelations();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        tcpConnection.close();

        ipEditText.removeTextChangedListener(tcpConnectionOnIPChangedHandler);
        tcpConnection.removeConnectionStateListener(commandPerformer);
        tcpConnection.removeConnectionStateListener(volumeButtonManager);
        tcpConnection.removeConnectionStateListener(indicatorTextViewBehavior);
        volumeButtonManager.removeVolumeButtonPressedListener(commandPerformer);
    }

    private void registerRelations()
    {
        /**
         * Relate TCPConnection and IPEditText
         * IPEditText sends event with IP
         */
        tcpConnectionOnIPChangedHandler = new TCPConnectionOnIPChangedHandler(tcpConnection);
        ipEditText.addTextChangedListener(tcpConnectionOnIPChangedHandler);

        /**
         * Relate TCPConnection and CommandPerformer
         * CommandPerformer needs to know WHERE to send a Command
         */
        tcpConnection.addConnectionStateListener(commandPerformer);

        /**
         * Relate TCPConnection and VolumeButtonManager
         * VolumeButtonManager needs to know WHEN to activate a button listening
         */
        tcpConnection.addConnectionStateListener(volumeButtonManager);

        /**
         * Relate CommandPerformer and VolumeButtonManager
         * CommandPerformer needs to know WHEN to send a Command
         */
        volumeButtonManager.addVolumeButtonPressedListener(commandPerformer);

        /**
         * Relate TCPConnection and IndicatorTextViewBehavior
         * IndicatorTextViewBehavior needs to indicate a status
         */
        tcpConnection.addConnectionStateListener(indicatorTextViewBehavior);
    }
}
