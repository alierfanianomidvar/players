package org._360T;

import java.util.concurrent.BlockingQueue;

class Player extends Thread {

    private final String name; // name - Id.
    private final boolean initiator; // Base on this we can change who is the first one will send the message.

    private final BlockingQueue<String> sentMessageQueue;
    private final BlockingQueue<String> receivedMessageQueue;

    public Player(
            String name,
            BlockingQueue<String> sentMessageQueue,
            BlockingQueue<String> receivedMessageQueue,
            boolean initiator) {
        this.name = name;
        this.sentMessageQueue = sentMessageQueue;
        this.receivedMessageQueue = receivedMessageQueue;
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
                // we send back the message when we received the message from the other player.
                if (!receivedMessageQueue.isEmpty()) {
                    String message = receivedMessageQueue.take();
                    System.out.println(name + " received: " + message);
                    messagesReceived++;

                    sendNewMessage(messagesSent);
                    messagesSent++;
                }

                Thread.sleep(1000);
            }

            System.out.println(name + " is closing the chat.");
        } catch (InterruptedException e) {
            System.out.println("We have an error the message is - >" + e.getMessage());
        }
    }

    private void sendNewMessage(int messagesSent) throws InterruptedException {
        String newMessage = "! Message " + (messagesSent + 1) + " from " + name;
        sentMessageQueue.put(newMessage);
        System.out.println(name + " sent Message - > " + newMessage);
    }
}