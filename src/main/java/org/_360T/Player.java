package org._360T;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.*;

public class Player {

    private String name;
    private Short massageSent;
    private Short messageReceived;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public Player(
            String name,
            Short massageSent,
            Short messageReceived) {
        this.name = name;
        this.massageSent = massageSent;
        this.messageReceived = messageReceived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getMassageSent() {
        return massageSent;
    }

    public void setMassageSent(Short massageSent) {
        this.massageSent = massageSent;
    }

    public Short getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(Short messageReceived) {
        this.messageReceived = messageReceived;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }
}
