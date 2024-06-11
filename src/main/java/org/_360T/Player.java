package org._360T;

import org._360T.Communicator.Communicator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Player extends Thread {
    private final String playerName;
    private final boolean initiator;
    private final Communicator communicator;

    public Player(String playerName, Communicator communicator, boolean initiator) {
        this.playerName = playerName;
        this.communicator = communicator;
        this.initiator = initiator;
    }

    @Override
    public void run() {
//        try {
//            int messagesSent = 0;
//            int messagesReceived = 0;
//
//            if (initiator) {
//                sendNewMessage(messagesSent);
//                messagesSent++;
//            }
//
//            while (messagesSent < 10 && messagesReceived < 10) {
//                String message = communicator.receiveMessage();
//                if (message != null) {
//                    System.out.println(playerName + " received: " + message);
//                    messagesReceived++;
//                    sendNewMessage(messagesSent);
//                    messagesSent++;
//                }
//
//                Thread.sleep(1000);
//            }
//
//            System.out.println(playerName + " is closing the chat.");
//        } catch (InterruptedException | IOException e) {
//            System.out.println("We have an error the message is -> " + e.getMessage());
//        }

        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            if (initiator) {
                sendNewMessage(0);
            }

            Thread readerThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = communicator.receiveMessage()) != null) {
                        System.out.println(playerName + " received: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            readerThread.start();

            String userInput;
            int messagesSent = 1;
            while ((userInput = stdIn.readLine()) != null) {
                communicator.sendMessage(userInput);
                messagesSent++;
                if (messagesSent >= 10) {
                    break;
                }
            }

            System.out.println(playerName + " is closing the chat.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendNewMessage(int messagesSent) throws InterruptedException, IOException {
        String newMessage = "! Message " + (messagesSent + 1) + " from " + playerName;
        System.out.println(playerName + " sent Message -> " + newMessage);
        communicator.sendMessage(newMessage);
    }
}
