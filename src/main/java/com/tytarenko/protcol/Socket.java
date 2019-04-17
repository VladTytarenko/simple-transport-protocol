package com.tytarenko.protcol;

import com.tytarenko.exceptios.EmptyServerNameException;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import org.apache.log4j.Logger;

public class Socket implements ILinkLayer, AutoCloseable {

    private static final Logger log = Logger.getLogger(Socket.class.getName());

    private final static int CHUNK_SIZE = 20;

    private String serverName;
    private PublishSubject<byte[]> source;

    public Socket() {
        this.source = PublishSubject.create();
    }

    public Socket(String serverName) {
        this.serverName = serverName;
        this.source = PublishSubject.create();
    }

    @Override
    public boolean send(byte[] data) {
        if (data.length > CHUNK_SIZE) {
            return false;
        }
        source.onNext(data);
        return true;
    }

    @Override
    public void subscribeReceiveListener(IDataReceiveListener listener) {
        source.subscribe(new Observer<byte[]>() {
            @Override
            public void onSubscribe(Disposable disposable) {}

            @Override
            public void onNext(byte[] bytes) {
                listener.onData(bytes);
            }

            @Override
            public void onError(Throwable throwable) {}

            @Override
            public void onComplete() {}
        });
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
