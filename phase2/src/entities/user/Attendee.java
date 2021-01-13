package entities.user;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Stores information about attendee.
 */
public class Attendee extends User{
    private ArrayList<Integer> friendList;
    private ArrayList<Integer> messageListReceived;
    private ArrayList<Integer> messageListunread = new ArrayList<>();
    private ArrayList<Integer> messageListdeleted = new ArrayList<>();
    private ArrayList<Integer> messageListarchived= new ArrayList<>();
    private ArrayList<Integer> eventSignedList;
    private boolean isVip;
    private ArrayList<Integer> friendRequestList;
    private HashMap<String, String> sRequestList;


    /**
     * Constructor for Attendee.
     * @param userId the userId of Attendee
     */
    //should only be used in user usecase to recreate entities
    public Attendee(int userId){
        super(userId);
        this.friendList = new ArrayList<>();
        this.eventSignedList = new ArrayList<>();
        this.isVip = false;
        this.friendRequestList = new ArrayList<>();
        this.sRequestList = new HashMap<>();
        sRequestList.put("Sea food allergy.", "None");
        sRequestList.put("Vegetarian.", "None");
        sRequestList.put("Peanut allergy.", "None");
        sRequestList.put("Accessibility requirements", "None");
        sRequestList.put("Mental health support.", "None");
    }


    /**
     * Constructor for Attendee.
     * @param userId the userId of Attendee
     * @param password the password of Attendee
     * @param username the username of Attendee
     */
    public Attendee(int userId, String password, String username){
        super(userId, password, username);
        this.friendList = new ArrayList<>();
        this.messageListReceived = new ArrayList<>();
        this.messageListdeleted = new ArrayList<>();
        this.messageListarchived = new ArrayList<>();
        this.messageListunread = new ArrayList<>();
        this.eventSignedList = new ArrayList<>();
        this.friendRequestList = new ArrayList<>();
        this.sRequestList = new HashMap<>();
        sRequestList.put("Sea food allergy.", "None");
        sRequestList.put("Vegetarian.", "None");
        sRequestList.put("Peanut allergy.", "None");
        sRequestList.put("Accessibility requirements", "None");
        sRequestList.put("Mental health support.", "None");
    }

    /**
     * Getter for the friendList.
     * @return friendList of Attendee.
     */
    public ArrayList<Integer> getFriendList() {
        return friendList;
    }

    /**
     * Getter for the eventSignedList.
     * @return eventSignedList of Attendee.
     */
    public ArrayList<Integer> getEventSignedList() {
        return eventSignedList;
    }

    /**
     * Setter for the friendList.
     * @param friendList friendList of Attendee.
     */
    public void setFriendList(ArrayList<Integer> friendList) {
        this.friendList = friendList;
    }

    /**
     * Setter for the eventSignedList.
     * @param eventSignedList eventSignedList of Attendee.
     */
    public void setEventSignedList(ArrayList<Integer> eventSignedList) {
        this.eventSignedList = eventSignedList;
    }

    /**
     * Get the information that if this user is vip
     * @return true iff the attendee is a vip
     */
    public boolean getStatus(){return this.isVip;}

    /**
     * Set the status of attendee
     * Precondition: status is different from this.isVip
     * @param status the status to be set
     */
    public void setStatus(boolean status){
        this.isVip = status;
    }

    /**
     * Getter for the messageListReceived
     * @return messageListReceived
     */
    public ArrayList<Integer> getMessageListReceived() {
        return messageListReceived;
    }

    /**
     * Getter for friendRequestList
     * @return requestList
     */
    public ArrayList<Integer> getFriendRequestList() {
        return friendRequestList;
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
     * setter for messageListReceived
     * @param messageListReceived parameter
     */
    public void setMessageListReceived(ArrayList<Integer> messageListReceived) {
        this.messageListReceived = messageListReceived;
    }

    /**
     * Setter for friendRequestList
     * @param requestList the friend request list to be set
     */
    public void setFriendRequestList(ArrayList<Integer> requestList){
        this.friendRequestList = requestList;
    }

    /**
     * Delete the id in the request list
     * @param requestId the id to be deleted
     */
    public void deleteRequest(int requestId){
        this.friendRequestList.remove(new Integer(requestId));
    }

    /**
     * Getter for sRequestList
     * @return sRequestList
     */
    public HashMap<String, String> getsRequestList() {
        return sRequestList;
    }

    /**
     * Setter for sRequestList
     * @param sRequestList the special request list to be set
     */
    public void setsRequestList(HashMap<String, String> sRequestList) {
        this.sRequestList = sRequestList;
    }

    /**
     * String representation of organizer
     * @return a string
     */
    @Override
    public String toString() {
        return "Attendee{" +
                "id=" + getUserId() +
                ", pwd=" + getPassword() +
                ", username=" + getUsername() +
                ", msgsent=" + getMessageListSent() +
                ", friendList=" + friendList +
                ", messageListReceived=" + messageListReceived +
                ", eventSignedList=" + eventSignedList +
                ", isVip=" + isVip +
                ", sRequestList=" +
                '}';
    }
}
