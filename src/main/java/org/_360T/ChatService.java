package org._360T;

import org._360T.Communicator.Communicator;
import org._360T.Communicator.Imp.InSameProcessCommunicator;

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

        Communicator communicatorPlayerOne = new InSameProcessCommunicator(messageSentFromOneToTwoQueue, messageSentFromTwoToOneQueue);
        Communicator communicatorPlayerTwo = new InSameProcessCommunicator(messageSentFromTwoToOneQueue, messageSentFromOneToTwoQueue);


        //     ***          -- player name --   -- Communicator --   -- Am I the initiator --
        player1 = new Player("Player 1", communicatorPlayerOne, true);
        player2 = new Player("Player 2", communicatorPlayerTwo, false);
    }

    public void startChat() {
        player1.start();
        player2.start();
    }
}
