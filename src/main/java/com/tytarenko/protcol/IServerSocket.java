package com.tytarenko.protcol;

public interface IServerSocket {

    Socket acceptable() throws InterruptedException;
    void addClientSocket(Socket socket) throws InterruptedException;
    void remove(Socket socket);
}
