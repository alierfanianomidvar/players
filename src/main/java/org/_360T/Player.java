package org._360T;

import org._360T.Communicator.Communicator;

import java.util.concurrent.BlockingQueue;

class Player extends Thread {

    private final String name; // name - Id.
    private final boolean initiator; // Base on this we can change who is the first one will send the message.

    private final Communicator communicator;


    public Player(
            String name,
            Communicator communicator,
            boolean initiator) {
        this.name = name;
        this.communicator = communicator;
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
                String message = communicator.receiveMessage();
                if (message != null) {
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
        System.out.println(name + " sent Message - > " + newMessage);
        communicator.sendMessage(newMessage);
    }
}