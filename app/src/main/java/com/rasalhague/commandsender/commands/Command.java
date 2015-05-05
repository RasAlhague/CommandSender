package com.rasalhague.commandsender.commands;

import java.io.DataOutputStream;

public interface Command
{
    void perform(DataOutputStream outToServer);
}
