import com.tytarenko.exceptios.EmptyServerNameException;
import com.tytarenko.protcol.ServerSocket;
import com.tytarenko.protcol.Socket;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void Test_Send_Data_From_Client_to_Server() throws InterruptedException {
        // server
        new Thread(() -> {
            ServerSocket serverSocket = new ServerSocket("test-socket");
            try {
                Socket s = serverSocket.acceptable();
                s.subscribeReceiveListener(data -> {
                    Assert.assertEquals("test1", new String(data));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread.sleep(1000);

        // client
        new Thread(() -> {
            Socket socket = new Socket("test-socket");
            try {
                socket.connect();
                socket.send("test1".getBytes());
            } catch (EmptyServerNameException emptyServerName) {
                emptyServerName.printStackTrace();
            }

        });
    }

    @Test
    public void Test_Two_Server() {
        // server 1
        new Thread(() -> {
            ServerSocket serverSocket = new ServerSocket("test-server-1");
            try {
                Socket s = serverSocket.acceptable();
                s.subscribeReceiveListener(data -> {
                    Assert.assertEquals("test1", new String(data));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // server 2
        new Thread(() -> {
            ServerSocket serverSocket = new ServerSocket("test-server-2");
            try {
                Socket s = serverSocket.acceptable();
                s.subscribeReceiveListener(data -> {
                    Assert.assertEquals("test2", new String(data));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // client for server 1
        new Thread(() -> {
            Socket socket = new Socket("test-server-1");
            try {
                socket.connect();
                socket.send("test1".getBytes());
            } catch (EmptyServerNameException emptyServerName) {
                emptyServerName.printStackTrace();
            }
        });

        // client for server 2
        new Thread(() -> {
            Socket socket = new Socket("test-server-2");
            try {
                socket.connect();
                socket.send("test2".getBytes());
            } catch (EmptyServerNameException emptyServerName) {
                emptyServerName.printStackTrace();
            }
        });

    }

}
