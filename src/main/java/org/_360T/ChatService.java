package org._360T;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatService {
    private final Player player1;
    private final Player player2;

    public ChatService() {
        //Base on the file we need just 10 message to be sent and received for player.
        BlockingQueue<String> messageSentFromOneToTwoQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<String> messageSentFromTwoToOneQueue = new ArrayBlockingQueue<>(10);

        //     ***          -- player name --       -- sent message --          -- received message --    -- Am I the initiator --
        player1 = new Player("Player 1", messageSentFromOneToTwoQueue, messageSentFromTwoToOneQueue, true);
        player2 = new Player("Player 2", messageSentFromTwoToOneQueue, messageSentFromOneToTwoQueue, false);
    }

    public void startChat() {
        player1.start();
        player2.start();
    }
}
