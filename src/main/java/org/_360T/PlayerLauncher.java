package org._360T;

import org._360T.Communicator.Imp.SocketCommunicatorImp;

import java.io.IOException;
import java.net.Socket;

public class PlayerLauncher {
    public static void main(String[] args) {
        String playerId = "0";
        if (args.length == 1) {
            playerId = args[0];
        }

        try {
            Socket socket = new Socket("localhost", 12345);
            Player player = new Player(
                    "player" + playerId,
                    new SocketCommunicatorImp(socket),
                    false
            );

            System.out.println("Connected to the chat server");
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
