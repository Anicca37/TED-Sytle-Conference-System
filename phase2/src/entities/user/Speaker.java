package entities.user;

import java.util.ArrayList;

/**
 * Stores information about speaker.
 */
public class Speaker extends User {
    private ArrayList<Integer> speechList;
    private ArrayList<Integer> messageListReceived;
    private ArrayList<Integer> messageListunread;
    private ArrayList<Integer> messageListdeleted;
    private ArrayList<Integer> messageListarchived;

    /**
     * Constructor for speaker.
     * @param userId the userId of speaker
     */
    //should only be used in user usecase to recreate entities
    public Speaker(int userId){
        super(userId);
        this.speechList = new ArrayList<>();
        this.messageListReceived = new ArrayList<>();
        this.messageListdeleted = new ArrayList<>();
        this.messageListunread = new ArrayList<>();
        this.messageListarchived = new ArrayList<>();
    }

    /**
     * Constructor for speaker.
     * @param userId the userId of speaker
     * @param password the password of speaker
     * @param username the username of speaker
     */
    public Speaker(int userId, String password, String username){
        super(userId, password, username);
        this.speechList = new ArrayList<>();
        this.messageListReceived = new ArrayList<>();
        this.messageListdeleted = new ArrayList<>();
        this.messageListunread = new ArrayList<>();
        this.messageListarchived = new ArrayList<>();
    }

    /**
     * Getter for MessageListarchived
     * @return Listarchived
     */
    public ArrayList<Integer> getMessageListarchived() {
        return messageListarchived;
    }

    /**
     * Getter for MessageListdeleted
     * @return Listdeleted
     */
    public ArrayList<Integer> getMessageListdeleted() {
        return messageListdeleted;
    }

    /**
     * Getter for MessageListunread
     * @return Listunread
     */
    public ArrayList<Integer> getMessageListunread() {
        return messageListunread;
    }

    /**
     * setter for MessageListarchived
     * @param messageListarchived parameter
     */
    public void setMessageListarchived(ArrayList<Integer> messageListarchived) {
        this.messageListarchived = messageListarchived;
    }

    /**
     * setter for MessageListdeleted
     * @param messageListdeleted parameter
     */
    public void setMessageListdeleted(ArrayList<Integer> messageListdeleted) {
        this.messageListdeleted = messageListdeleted;
    }

    /**
     * setter for MessageListunread
     * @param messageListunread parameter
     */
    public void setMessageListunread(ArrayList<Integer> messageListunread) {
        this.messageListunread = messageListunread;
    }

    /**
     * Getter for the speechList.
     * @return the speechList of Speaker.
     */
    public ArrayList<Integer> getSpeechList() {
        return speechList;
    }

    /**
     * Setter for the speechList.
     * @param speechList the speechList of Speaker.
     */
    public void setSpeechList(ArrayList<Integer> speechList) {
        this.speechList = speechList;
    }

    /**
     * Getter for messageListReceived
     * @return messageListReceived
     */
    public ArrayList<Integer> getMessageListReceived() {
        return messageListReceived;
    }

    /**
     * setter for messageListReceived
     * @param messageListReceived the messageListReceived
     */
    public void setMessageListReceived(ArrayList<Integer> messageListReceived) {
        this.messageListReceived = messageListReceived;
    }
}