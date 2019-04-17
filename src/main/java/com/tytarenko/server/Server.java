package com.tytarenko.server;

import com.tytarenko.protcol.ServerSocket;
import com.tytarenko.protcol.Socket;
import org.apache.log4j.Logger;

public class Server implements Runnable {

    private static final Logger log = Logger.getLogger(Server.class.getName());

    @Override
    public void run() {
        log.info("Server start");
        // init server
        ServerSocket serverSocket = new ServerSocket("test-server");
        try {
            while (true) {
                // get client's socket
                Socket socket = serverSocket.acceptable();
                log.info("Server wait for data...");
                socket.subscribeReceiveListener(data -> {
                    log.info("Server received data: " + new String(data));
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
