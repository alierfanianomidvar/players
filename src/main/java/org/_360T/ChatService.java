package org._360T;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ChatService {
    private final Player player1;
    private final Player player2;

    public ChatService() {
        BlockingQueue<String> queue1to2 = new ArrayBlockingQueue<>(10);
        BlockingQueue<String> queue2to1 = new ArrayBlockingQueue<>(10);

        player1 = new Player("Player 1", queue1to2, queue2to1, true);
        player2 = new Player("Player 2", queue2to1, queue1to2, false);
    }

    public void startChat() {
        player1.start();
        player2.start();
    }
}
