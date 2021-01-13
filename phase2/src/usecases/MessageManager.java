package usecases;

import entities.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the message manager of the program.
 */
public class MessageManager {
    private HashMap<Integer, Message> messageList;

    /**
     * Constructor of message manager.
     */
    public MessageManager(){
        this.messageList = new HashMap<>();
    }

    /**
     * recreateMessage should only be used in Gateway
     * @param messageId the id of the message
     * @param time the time of the message
     */
    //recreateMessage should only be used in Gateway
    public void recreateMessage(int messageId,
                                String content,
                                int sender,
                                int receiver,
                                LocalDateTime time,
                                ArrayList<Integer> replyLst){
        Message message = new Message(messageId,content,sender,receiver);
        messageList.put(messageId, message);
        message.setReplyList(replyLst);
        message.setTime(time);
    }

    /**
     * Create a message.
     * @param content the content of the message
     * @param sender the sender of the message
     * @param receiver the receiver of the message
     */
    public void createMessage(String content, int sender, int receiver){
        int messageId = messageList.size() + 1;
        Message message = new Message(messageId,content,sender,receiver);
        HashMap<Integer, Message> messageList = this.getMessageList();
        messageList.put(messageId, message);
        this.setMessageList(messageList);
    }

    /**
     * Getter for the messageList.
     * @return the messageList.
     */
    public HashMap<Integer, Message> getMessageList() {
        return messageList;
    }

    /**
     * Setter for the messageList.
     * @param messageList the messageList.
     */
    public void setMessageList(HashMap<Integer, Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * Getter for the time of Message.
     * @param messageId the id of Message.
     * @return the time of Message.
     */
    public LocalDateTime getTime(int messageId) {
        return messageList.get(messageId).getTime();
    }

    /**
     * Getter for the content of Message.
     * @param messageId the id of Message.
     * @return the content of Message.
     */
    public String getContent(int messageId) {
        return messageList.get(messageId).getContent();
    }

    /**
     * Getter for the sender of Message.
     * @param messageId the id of Message.
     * @return the sender of Message.
     */
    public int getSender(int messageId) {
        return messageList.get(messageId).getSender();
    }

    /**
     * Getter for the receiver of Message.
     * @param messageId the id of Message.
     * @return the receiver of Message.
     */
    public int getReceiver(int messageId) {
        return messageList.get(messageId).getReceiver();
    }

    /**
     * Getter for the replyList.
     * @param messageId the id of Message.
     * @return the replyList.
     */
    public ArrayList<Integer> getReplyList(int messageId) {
        return messageList.get(messageId).getReplyList();
    }

    /**
     * Setter for the replyList.
     * @param messageId the id of Message.
     * @param replyList the replyList.
     */
    public void setReplyList(int messageId, ArrayList<Integer> replyList) {
        messageList.get(messageId).setReplyList(replyList);
    }

    /**
     * Validation for the id of Message.
     * @param messageId the id of Message.
     * @return true if and only if this message is in the messageList.
     */
    public boolean validateMessageId(int messageId){//should be used in controller to validate id
        return messageList.containsKey(messageId);
    }

    /**
     * Add the replyList.
     * @param messageId the id of Message
     * @param messageId2 the id of reply
     */
    public void addReplyList(int messageId, int messageId2) {
        ArrayList<Integer> replyList = getReplyList(messageId);
        replyList.add(messageId2);
        Message message = messageList.get(messageId);
        message.setReplyList(replyList);
    }
}