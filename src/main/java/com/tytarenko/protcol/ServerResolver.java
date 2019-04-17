package com.tytarenko.protcol;

import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

public class ServerResolver {

    private static final Logger log = Logger.getLogger(ServerResolver.class.getName());

    private static ConcurrentHashMap<String, ServerSocket> stringServerSocket =
            new ConcurrentHashMap<>();


    public static ServerSocket getServer(String serverName) {
        return stringServerSocket.get(serverName);
    }

    public static void createServer(ServerSocket serverSocket) {
        log.info("Create new server " + serverSocket.getServerName());
        stringServerSocket.put(serverSocket.getServerName(), serverSocket);
    }

}
