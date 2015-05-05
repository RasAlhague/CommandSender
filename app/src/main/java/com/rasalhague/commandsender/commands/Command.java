package com.rasalhague.commandsender.commands;

import java.net.Socket;

public interface Command
{
    void perform(Socket outToServer);
}
