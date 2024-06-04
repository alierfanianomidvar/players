package org._360T;

public class Player {

    private String name;
    private Short massageSent;
    private Short messageReceived;

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
}
