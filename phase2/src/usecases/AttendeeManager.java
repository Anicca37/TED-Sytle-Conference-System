package usecases;
import entities.user.Attendee;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class AttendeeManager{
    private HashMap<Integer, Attendee> attendeeList;
    private final VipManager vipManager;
    private final RequestManager requestManager;

    /**
     * Constructor of AttendeeManager.
     */
    public AttendeeManager(){
        this.attendeeList = new HashMap<>();
        this.vipManager = new VipManager(this);
        this.requestManager = new RequestManager(this);
    }

    /**
     * Create attendee used by gateway.
     * @param userId the userId
     */
    public void recreateAttendee(int userId, String password, String username, Boolean isVip,
                                 ArrayList<Integer> messageSent, ArrayList<Integer> messageReceived,
                                 ArrayList<Integer> friendLst, ArrayList<Integer> eventLst,
                                 ArrayList<Integer> requestLst, HashMap<String, String> add_request,
                                 ArrayList<Integer> messageUnread, ArrayList<Integer> messageDeleted,
                                 ArrayList<Integer> messageArchived
                                 ){
        Attendee attendee = new Attendee(userId);
        this.attendeeList.put(userId, attendee);
        attendee.setPassword(password);
        attendee.setUsername(username);
        attendee.setStatus(isVip);
        attendee.setMessageListSent(messageSent);
        attendee.setMessageListReceived(messageReceived);
        attendee.setFriendList(friendLst);
        attendee.setEventSignedList(eventLst);
        attendee.setFriendRequestList(requestLst);
        attendee.setsRequestList(add_request);
        attendee.setMessageListarchived(messageArchived);
        attendee.setMessageListdeleted(messageDeleted);
        attendee.setMessageListunread(messageUnread);
    }

    /**
     * Only used in organizerManager.createOrganizer
     * @param password the password of the account
     * @param username the username of the account
     * @return the userid of this attendee
     */
    public int organizerAssociation(String password, String username){
        String id = '1' + Integer.toString(attendeeList.size() + 1);
        int userId = Integer.parseInt(id);
        Attendee attendee = new Attendee(userId, password, username);
        this.attendeeList.put(userId, attendee);
        return userId;
    }

    /**
     * Create an attendee account.
     * @param password the password of the attendee
     * @param username the username of the attendee
     */
    public void createAttendee(String username, String password){
        String id = '1' + Integer.toString(attendeeList.size() + 1);
        int userId = Integer.parseInt(id);
        Attendee attendee = new Attendee(userId, password, username);
        this.attendeeList.put(userId, attendee);
    }

    /**
     * Set attendeeList
     * @param attendeeList the attendeeList to set.
     */
    public void setAttendeeList(HashMap<Integer, Attendee> attendeeList){
        this.attendeeList = attendeeList;
    }

    /**
     * @return the attendee list
     */
    public HashMap<Integer, Attendee> getAttendeeList(){
        return attendeeList;
    }

    /**
     * Add event to this attendee
     * @param userid the id of the user
     * @param eventId the id of the event to be added.
     */
    public void addEvent(int userid, int eventId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> original_event = user.getEventSignedList();
        if (!original_event.contains(eventId)) {
            original_event.add(eventId);
            user.setEventSignedList(original_event);
        }
    }

    /**
     * Remove the event from the event list
     * @param userid userid the id of the user
     * @param eventId the id of the event to be removed.
     */
    public void removeEvent(int userid, int eventId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> original_event = user.getEventSignedList();
        original_event.remove(Integer.valueOf(eventId));
        user.setEventSignedList(original_event);
    }

    /**
     * Get the friend list of the attendee
     * @param userid userid the id of the user.
     * @return friendList the friend list to be set.
     */
    public ArrayList<Integer> getFriendList(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getFriendList();
    }

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param eventList event list the friend list to be set.
     */
    public void setEventList(int userid, ArrayList<Integer> eventList){
        attendeeList.get(userid).setEventSignedList(eventList);
    }

    /**
     * Get the message list of the attendee.
     * @param userid userid the id of the user.
     * @return the event list of the attendee
     */
    public ArrayList<Integer> getEventList(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getEventSignedList();
    }

    /**
     * Get the password of the attendee
     * @param userid userid the id of the user.
     * @return the password of the user
     */
    public String getPassword(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getPassword();
    }

    /**
     * Set the password for the attendee
     * @param userid userid the id of the user.
     * @param password the password to be set
     */
    public void setPassword(int userid, String password){
        Attendee user = this.attendeeList.get(userid);
        user.setPassword(password);
    }

    /**
     * @param userid the id of the user
     * @param password the password to be validated
     * @return true iff the password is not the same as the current one
     */
    public boolean validatePassword(int userid, String password){
        return this.getAttendeeList().get(userid).getPassword().equals(password);
    }

    /**
     * Get the username of the user
     * @param userid userid the id of the user.
     * @return get the username of the attendee
     */
    public String getUsername(int userid) {
        Attendee user = this.attendeeList.get(userid);
        return user.getUsername();
    }

    /**
     * Transfer the user name to id
     * @param username the name of the user
     * @return the id if name is valid
     */
    public int nameToId(String username){
        for (int userid : getAttendeeList().keySet()) {
            if (getUsername(userid).equals(username)) {
                return userid;
            }
        }
        return 4;
    }

    /**
     * Check if the username is valid
     * @param username is a string representing username
     * @return ture if valid
     */
    public boolean isValidUsername(String username){ return nameToId(username) != 4;
    }

    /**
     * Validation for attendeeLogin
     * @param username the name of the user
     * @param password the password of the user
     * @return if login success
     */
    public boolean attendeeLogin(String username, String password){
        int userid = nameToId(username);
        return (isValidUsername(username) && getPassword(userid).equals(password));
    }

    /**
     * @return the current number of attendees
     * Used for creating id for registering new attendee account.
     */
    public int getNumAttendee(){
        return this.attendeeList.size();
    }

    /**
     * Get if this user is vip
     * @return true iff the attendee is a vip
     */
    public boolean getStatus(int userId) {
        return this.attendeeList.get(userId).getStatus();
    }

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageListSent messageListSent the user to be set.
     */
    public void setMessageListSent(int userid, ArrayList<Integer> messageListSent){
        Attendee user = this.attendeeList.get(userid);
        user.setMessageListSent(messageListSent);
    }

    /**
     * Get the message list of the attendee.
     * @param userid userid the id of the user.
     * @return messageList sent the user to be get.
     */
    public ArrayList<Integer> getMessageListSent(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getMessageListSent();
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListSent(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListSent = user.getMessageListSent();
        messageListSent.add(messageId);
        setMessageListSent(userid, messageListSent);
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListReceived(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListReceived = user.getMessageListReceived();
        messageListReceived.add(messageId);
        setMessageListReceived(userid, messageListReceived);
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListUnread(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListUnread = user.getMessageListunread();
        messageListUnread.add(messageId);
        setMessageListunread(userid,messageListUnread);
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListArchived(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListArchived = user.getMessageListarchived();
        messageListArchived.add(messageId);
        setMessageListarchived(userid,messageListArchived);}

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListDeleted(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListDeleted = user.getMessageListdeleted();
        messageListDeleted.add(messageId);
        setMessageListDeleted(userid,messageListDeleted);}

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageListReceived message list Received of the user to be set.
     */
    public void setMessageListReceived(int userid, ArrayList<Integer> messageListReceived){
        Attendee user = this.attendeeList.get(userid);
        user.setMessageListReceived(messageListReceived);
    }

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageListDeleted message list Received of the user to be set.
     */
    public void setMessageListDeleted(int userid, ArrayList<Integer> messageListDeleted){
        Attendee user = this.attendeeList.get(userid);
        user.setMessageListdeleted(messageListDeleted);
    }

    /**
     * Remove the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageId the id of the message
     */
    public void removeMessageListReceived(int userid, Integer messageId){
        Attendee user = this.attendeeList.get(userid);
        ArrayList<Integer> messageListReceived = user.getMessageListReceived();
        messageListReceived.remove(messageId);
        setMessageListReceived(userid, messageListReceived);
    }

    /**
     * Get the message list received of the attendee.
     * @param userid userid the id of the user.
     * @return messageListReceived of the user
     */
    public ArrayList<Integer> getMessageListReceived(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getMessageListReceived();
    }

    /**
     * Get the message list unread of the attendee.
     * @param userid userid the id of the user.
     * @return messageListReceived of the user
     */
    public ArrayList<Integer> getMessageListunread(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getMessageListunread();
    }

    /**
     * Get the message list deleted of the attendee.
     * @param userid userid the id of the user.
     * @return messageListReceived of the user
     */
    public ArrayList<Integer> getMessageListdeleted(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getMessageListdeleted();
    }

    /**
     * Get the message list archived of the attendee.
     * @param userid userid the id of the user.
     * @return messageListReceived of the user
     */
    public ArrayList<Integer> getMessageListarchived(int userid){
        Attendee user = this.attendeeList.get(userid);
        return user.getMessageListarchived();
    }

    /**
     * Set the message list unread of the attendee.
     * @param userid userid the id of the user.
     * @param messageListunread the list to be set
     */
    public void setMessageListunread(int userid, ArrayList<Integer> messageListunread){
        Attendee user = this.attendeeList.get(userid);
        user.setMessageListunread(messageListunread);
    }

    /**
     * Set the message list archived of the attendee.
     * @param userid userid the id of the user.
     * @param messageListarchived the list to be set
     */
    public void setMessageListarchived(int userid, ArrayList<Integer> messageListarchived){
        Attendee user = this.attendeeList.get(userid);
        user.setMessageListarchived(messageListarchived);
    }

    /**
     * Add the id of attendee who request to the request list
     * @param toAdd the id to be requested as a friend
     * @param requestPerson the id that request others to be a friend.
     */
    public void addRequestList(int toAdd, int requestPerson){
        ArrayList<Integer> requestList = this.attendeeList.get(toAdd).getFriendRequestList();
        requestList.add(requestPerson);
    }

    /**
     * Getter for request list
     * @param userid the id to check request list
     * @return the requestList
     */
    public ArrayList<Integer> getRequestList(int userid){
        return this.attendeeList.get(userid).getFriendRequestList();
    }

    /**
     * @param requestId the id in the request list
     * @param userid the id that has this request list
     */
    public void deleteRequestList(int requestId, int userid){
        this.attendeeList.get(userid).deleteRequest(requestId);

    }

    /**
     * @param username the name of the user
     * @return true iff the user can be upgraded to VIP
     */
    public boolean canUpgrade(String username){
        return this.vipManager.validateUpgrade(username);
    }

    /**
     * @param username the name of the user
     * @return true iff the user can be downgraded to normal
     */
    public boolean canDowngrade(String username){
        return this.vipManager.validateDowngrade(username);
    }

    /**
     * Upgrade the user to VIP
     * @param userId the id of the user
     */
    public void setVip(int userId){this.vipManager.upgrade(userId);}

    /**
     * Downgrade the user to normal attendee
     * @param userId the id of the user
     */
    public void downgrade(int userId){this.vipManager.downgrade(userId);}

    /**
     * Send the request by this user.
     * @param userId the id of the given attendee
     * @param request the name of the request
     */
    public void request(int userId, String request) {
        requestManager.setRequest(userId, request);
    }

    /**
     * Tag a request as addressed.
     * @param userId the id of the given attendee
     * @param request the name of the request
     */
    public void setAddressed(int userId, String request) {
        requestManager.setAddressed(userId, request);
    }

    /**
     * @param userId the id of the given attendee
     * @param request the name of the request
     * @return the string representation of a single request
     */
    public String requestToString(int userId, String request) {
        return requestManager.requestToString(getUsername(userId), request);
    }

    /**
     * @return the string representation of the addressedList
     */
    public String addressedListToString() {
        return requestManager.addressedListToString();
    }

    /**
     * Getter for the pendingList.
     * @return the list of pending requests
     */
    public ArrayList<Pair<String, String>> getPendingList() {
        return requestManager.getPendingList();
    }

    /**
     * Getter for sRequestList for a specific attendee
     * @param userId the id of the given attendee
     * @return the sRequestList of that given attendee
     */
    public HashMap<String, String> getSRequestList(int userId) {
        return attendeeList.get(userId).getsRequestList();
    }

    /**
     * Setter for sRequestList for a specific attendee
     * @param userId the id of the given attendee
     * @param sRequestList the special request list to be set
     */
    public void setSRequestList(int userId, HashMap<String, String> sRequestList) {
        attendeeList.get(userId).setsRequestList(sRequestList);
    }

    /**
     * Print the info about the attendee
     * @param userId the id
     * @return the info about the attendee
     */
    public String attendeeInfo(int userId){
        return this.attendeeList.get(userId).toString();
    }

    /**
     * This method allows an attendee to add an attendee friend.
     * @param friendId the id of the user you want to add to friend list.
     */
    public void attendeeAddFriend(int userid, int friendId) {
        this.getFriendList(userid).add(friendId);
        this.getFriendList(friendId).add(userid);
    }
}
