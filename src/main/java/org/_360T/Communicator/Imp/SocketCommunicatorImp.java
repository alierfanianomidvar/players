package org._360T.Communicator.Imp;

import org._360T.Communicator.Communicator;

import java.io.*;
import java.net.*;

public class SocketCommunicatorImp implements Communicator {

    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public SocketCommunicatorImp(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void sendMessage(String message) {
        out.println(message);
    }

    @Override
    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}