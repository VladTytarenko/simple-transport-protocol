package com.tytarenko.protcol;

import com.tytarenko.exceptios.EmptyServerNameException;
import org.apache.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Socket implements ILinkLayer, AutoCloseable {

    private static final Logger log = Logger.getLogger(Socket.class.getName());

    private final static int CHUNK_SIZE = 20;

    private String serverName;
    private Queue<byte[]> source;

    public Socket() {
        this.source = new ConcurrentLinkedQueue<>();
    }

    public Socket(String serverName) {
        this.serverName = serverName;
        this.source = new ConcurrentLinkedQueue<>();
    }

    @Override
    public boolean send(byte[] data) {
        if (data == null || data.length > CHUNK_SIZE) {
            return false;
        }
        return source.offer(data);
    }

    @Override
    public void subscribeReceiveListener(IDataReceiveListener listener) {
        new Thread(() -> {
            while (true) {
                byte[] payload = source.poll();
                if (payload != null) {
                    listener.onData(payload);
                }
            }
        }).start();
    }

    public void connect() throws EmptyServerNameException {
        if (serverName == null || serverName.isEmpty()) {
            throw new EmptyServerNameException("Empty server name. Set up server name to connect.");
        }
        try {
            ServerResolver.getServer(serverName).addClientSocket(this);
            log.info("Socket connected to " + serverName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        ServerResolver.getServer(serverName).remove(this);
        log.info("Connection close");
    }

}
