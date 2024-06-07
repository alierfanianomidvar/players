package org._360T;

import java.io.*;
import java.net.*;
import java.util.concurrent.BlockingQueue;

class Player extends Thread {
    private final BlockingQueue<String> inQueue;
    private final BlockingQueue<String> outQueue;
    private final String name;
    private final boolean initiator;

    public Player(
            String name,
            BlockingQueue<String> inQueue,
            BlockingQueue<String> outQueue,
            boolean initiator) {
        this.name = name;
        this.inQueue = inQueue;
        this.outQueue = outQueue;
        this.initiator = initiator;
    }

    @Override
    public void run() {
        try {
            int messagesSent = 0;
            int messagesReceived = 0;

            if (initiator) {
                sendNewMessage(messagesSent);
                messagesSent++;
            }

            while (messagesSent < 10 && messagesReceived < 10) {
                if (!outQueue.isEmpty()) {
                    String message = outQueue.take();
                    System.out.println(name + " received: " + message);
                    messagesReceived++;

                    if (messagesSent < 10) {
                        sendNewMessage(messagesSent);
                        messagesSent++;
                    }
                }

                Thread.sleep(1000);
            }

            System.out.println(name + " is closing the chat.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendNewMessage(int messagesSent) throws InterruptedException {
        String newMessage = "Message " + (messagesSent + 1) + " from " + name;
        inQueue.put(newMessage);
        System.out.println(name + " sent Message - > " + newMessage);
    }
}