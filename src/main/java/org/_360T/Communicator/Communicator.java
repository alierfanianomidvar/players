package org._360T.Communicator;

public interface Communicator {
    void sendMessage(String message) throws InterruptedException;
    String receiveMessage() throws InterruptedException;
}
