package org._360T.Communicator.Imp;

import org._360T.Communicator.Communicator;

import java.util.concurrent.*;

public class InSameProcessCommunicator implements Communicator {

    private final BlockingQueue<String> sentMessageQueue;
    private final BlockingQueue<String> receivedMessageQueue;

    public InSameProcessCommunicator(
            BlockingQueue<String> sentMessageQueue,
            BlockingQueue<String> receivedMessageQueue) {
        this.sentMessageQueue = sentMessageQueue;
        this.receivedMessageQueue = receivedMessageQueue;
    }

    @Override
    public void sendMessage(String message) throws InterruptedException {
        sentMessageQueue.put(message);
    }

    @Override
    public String receiveMessage() throws InterruptedException {
        return receivedMessageQueue.take();
    }
}