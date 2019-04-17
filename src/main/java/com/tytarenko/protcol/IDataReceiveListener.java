package com.tytarenko.protcol;

public interface IDataReceiveListener {
    /**
     * @param data from the lower layer
     */
    void onData(byte[] data);
}
