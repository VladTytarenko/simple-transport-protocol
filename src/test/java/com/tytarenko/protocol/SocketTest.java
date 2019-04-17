package com.tytarenko.protocol;

import com.tytarenko.exceptios.EmptyServerNameException;
import com.tytarenko.protcol.Socket;
import org.junit.Assert;
import org.junit.Test;

public class SocketTest {

    @Test(expected = EmptyServerNameException.class)
    public void Connected_SocketWithoutServerName_EmptyServerNameException() throws EmptyServerNameException{
        Socket socket = new Socket();
        socket.connect();
    }

    @Test
    public void Send_ChunkLessThan20Bytes_CorrectlySendAndResive() {
        Socket socket = new Socket();
        Assert.assertTrue(socket.send("test".getBytes()));
        socket.subscribeReceiveListener(data -> Assert.assertEquals("test", new String(data)));
    }

    @Test
    public void Send_ChunkMoreThan20Bytes_FailToSend() {
        Socket socket = new Socket();
        Assert.assertFalse(socket.send("qwertyuiopasdfghjklzxcvbnm".getBytes()));
    }
}
