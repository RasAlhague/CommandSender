package com.rasalhague.commandsender.logic;

import android.text.Editable;
import android.text.TextWatcher;
import com.rasalhague.commandsender.App;
import com.rasalhague.commandsender.R;
import com.rasalhague.commandsender.connection.Destination;
import com.rasalhague.commandsender.connection.TCPConnection;

public class TCPConnectionOnIPChangedHandler implements TextWatcher
{
    private final TCPConnection tcpConnection;

    public TCPConnectionOnIPChangedHandler(TCPConnection tcpConnection)
    {

        this.tcpConnection = tcpConnection;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        boolean matches = s.toString().matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        if (matches)
        {
            tcpConnection.open(new Destination(s.toString(),
                                               App.getContext().getResources().getInteger(R.integer.port)));
            //            tcpConnection.open(new Destination(s.toString(), Resources.getSystem().getInteger(R.integer.port)));
            //            tcpConnection.open(new Destination(s.toString(), 4444));
        }
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }
}
