package org._360T;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Main {

    private static final int PORT = 12345;
    private static final ConcurrentMap<Socket, PrintWriter> clients = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        //ChatService chatSystem = new ChatService();
        //chatSystem.startChat();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Socket server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                clients.put(socket, out);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server received: " + message);
                    for (PrintWriter writer : clients.values()) {
                        writer.println(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                clients.remove(socket);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}