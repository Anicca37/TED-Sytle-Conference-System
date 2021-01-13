package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stores information about message.
 */
public class Message {
    private String content;
    private LocalDateTime time;
    private int sender;
    private int receiver;
    private final int messageId;
    private ArrayList<Integer> replyList;


    /**
     * Constructor for Message.
     * @param messageId the id of the message
     * @param content the content of the message
     * @param sender the sender of the message
     * @param receiver the receiver of the message
     */
    public Message(int messageId, String content, int sender, int receiver){
        this.messageId = messageId;
        this.content = content;
        this.time = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
        this.replyList = new ArrayList<>();
    }

    /**
     * Getter for the id of message.
     * @return the id of message
     */
    public int getMessageId() {return messageId;}

    /**
     * Getter for the time of message.
     * @return the time of message.
     */
    public LocalDateTime getTime(){
        return time;
    }

    /**
     * Getter for the content of message.
     * @return the content of message.
     */
    public String getContent(){
        return content;
    }

    /**
     * Getter for the sender of message.
     * @return the sender of message.
     */
    public int getSender(){
        return sender;
    }

    /**
     * Getter for the receiver of message.
     * @return the receiver of message.
     */
    public int getReceiver(){
        return receiver;
    }

    /**
     * Getter for the replyList.
     * @return the replyList.
     */
    public ArrayList<Integer> getReplyList() {
        return replyList;
    }

    /**
     * Setter for the replyList.
     * @param replyList the replyList.
     */
    public void setReplyList(ArrayList<Integer> replyList) {
        this.replyList = replyList;
    }

    /**
     * Setter for the content of message.
     * @param content the content of message.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Setter for the time of message.
     * @param time the time of message.
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Setter for the receiver of message.
     * @param receiver the receiver of message.
     */
    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    /**
     * Setter for the sender of message.
     * @param sender the sender of message.
     */
    public void setSender(int sender) {
        this.sender = sender;
    }
}

