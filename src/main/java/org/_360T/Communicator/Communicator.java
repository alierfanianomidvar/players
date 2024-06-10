package org._360T.Communicator;

import java.io.IOException;

public interface Communicator {
    void sendMessage(String message) throws InterruptedException;
    String receiveMessage() throws InterruptedException, IOException;
}
