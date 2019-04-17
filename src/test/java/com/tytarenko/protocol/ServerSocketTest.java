package com.tytarenko.protocol;

import com.tytarenko.protcol.ServerSocket;
import com.tytarenko.protcol.Socket;
import org.junit.Assert;
import org.junit.Test;

public class ServerSocketTest {

    @Test
    public void test_server_socket_add_client_socket() throws InterruptedException {
        ServerSocket serverSocket = new ServerSocket("test-server-socket");
        Assert.assertEquals(serverSocket.countSocket(), 0);
        serverSocket.addClientSocket(new Socket("test-server-socket"));
        Assert.assertEquals(serverSocket.countSocket(), 1);
    }
}
