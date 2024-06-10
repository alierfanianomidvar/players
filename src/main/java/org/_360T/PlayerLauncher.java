package org._360T;

import org._360T.Communicator.Communicator;
import org._360T.Communicator.Imp.SocketCommunicatorImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Main class to start a Player.
 * Responsibilities:
 * - Initialize Player with SocketCommunicator.
 * - Start the Player thread.
 */
public class PlayerLauncher {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the chat server");

            Thread readerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            readerThread.start();

            String userMessage;
            while ((userMessage = stdIn.readLine()) != null) {
                out.println(userMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}