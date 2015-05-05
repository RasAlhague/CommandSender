package com.rasalhague.commandsender.gui;

import android.text.TextWatcher;
import android.widget.EditText;

public class IPEditText
{
    private final EditText ipEditText;

    public IPEditText(EditText ipEditText)
    {
        this.ipEditText = ipEditText;
    }

    public void addTextChangedListener(TextWatcher watcher) {ipEditText.addTextChangedListener(watcher);}

    public void removeTextChangedListener(TextWatcher watcher) {ipEditText.removeTextChangedListener(watcher);}
}
