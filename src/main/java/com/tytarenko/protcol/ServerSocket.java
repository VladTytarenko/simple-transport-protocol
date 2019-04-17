package com.tytarenko.protcol;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ServerSocket implements IServerSocket {

    private BlockingQueue<Socket> sockets;
    private String serverName;

    public ServerSocket(String serverName) {
        this.sockets  = new ArrayBlockingQueue<>(1);
        this.serverName = serverName;
        ServerResolver.createServer(this);
    }

    @Override
    public Socket acceptable() throws InterruptedException {
        return sockets.take();
    }

    @Override
    public void addClientSocket(Socket socket) throws InterruptedException {
        sockets.put(socket);
    }

    @Override
    public void remove(Socket socket) {
        sockets.remove(sockets);
    }

    String getServerName() {
        return serverName;
    }

    public int countSocket() {
        return sockets.size();
    }
}
