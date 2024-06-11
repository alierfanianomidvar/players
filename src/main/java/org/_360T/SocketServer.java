package org._360T;

import org._360T.Communicator.Imp.SocketCommunicatorImp;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketServer {
    private static final Map<Integer, Socket> clientSockets = Collections.synchronizedMap(new HashMap<>());
    private static int clientIdCounter = 1;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started on port 12345");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                int playerId = clientIdCounter++;
                clientSockets.put(playerId, clientSocket);
                System.out.println("Player " + playerId + " connected: " + clientSocket);

                new Thread(() -> handleClient(playerId, clientSocket)).start();
            }
        }
    }

    private static void handleClient(int playerId, Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("Received from player " + playerId + ": " + message);
                broadcastMessage(playerId, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientSockets.remove(playerId);
            System.out.println("Player " + playerId + " disconnected.");
        }
    }

    private static void broadcastMessage(int senderId, String message) {
        synchronized (clientSockets) {
            for (Map.Entry<Integer, Socket> entry : clientSockets.entrySet()) {
                int playerId = entry.getKey();
                Socket socket = entry.getValue();

                if (playerId != senderId) { // Exclude sender
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Player " + senderId + ": " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
