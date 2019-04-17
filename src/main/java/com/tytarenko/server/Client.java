package com.tytarenko.server;

import com.tytarenko.exceptios.EmptyServerNameException;
import com.tytarenko.protcol.Socket;
import org.apache.log4j.Logger;

public class Client implements Runnable {

    private static final Logger log = Logger.getLogger(Client.class.getName());

    private String clientName;
    private String data;

    public Client(String clientName, String data) {
        this.data = data;
        this.clientName = clientName;
    }

    @Override
    public void run() {
        log.info(clientName + ": client start");

        try(Socket socket = new Socket("test-server")) {
            // create connection with "server"
            socket.connect();
            Thread.sleep(1000);

            // send data to server
            if (socket.send(data.getBytes())) {
                log.info(clientName + ": data was sent");
            } else {
                log.error(clientName + ": the chunk have is more than 20 bytes");
            }
        } catch (InterruptedException | EmptyServerNameException e) {
            e.printStackTrace();
        }
    }
}
