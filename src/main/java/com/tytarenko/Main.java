package com.tytarenko;

import com.tytarenko.server.Client;
import com.tytarenko.server.Server;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Test server with two clients
        new Thread(new Server()).start();

        new Thread(new Client("Client1", "Test-payload 1")).start();
        Thread.sleep(1000);
        new Thread(new Client("Client2", "Test-payload 2")).start();
    }
}
