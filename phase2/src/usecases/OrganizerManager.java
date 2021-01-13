package usecases;

import entities.user.Organizer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Store all information of organizers.
 */
public class OrganizerManager{
    private HashMap<Integer, Organizer> organizerList;

    /**
     * Constructor of OrganizerManager
     */
    public OrganizerManager(){
        this.organizerList = new HashMap<>();
    }

    /**
     * create Organizer, used only by gateway
     * @param userId id of the user
     */
    public void recreateOrganizer(int userId, String password, String username, int attendeeId,
                                         ArrayList<Integer> messageSent, ArrayList<Integer> eventLst){
        Organizer user = new Organizer(userId);
        this.organizerList.put(userId, user);
        user.setPassword(password);
        user.setUsername(username);
        user.setAttendeeId(attendeeId);
        user.setMessageListSent(messageSent);
        user.setEventHostList(eventLst);
    }

//    /**
//     * create an organizer account.
//     * @param password the password of the account
//     * @param username the name of the account
//     * @param attendeeManager the system to add corresponding attendee.
//     */
//    public void createOrganizer(String password,String username, AttendeeManager attendeeManager){
//        String id = '3' + Integer.toString(organizerList.size() + 1);
//        int userId = Integer.parseInt(id);
//        int attendeeId = attendeeManager.organizerAssociation(password, username);
//        Organizer organizer = new Organizer(userId, password, username, attendeeId);
//        HashMap<Integer, Organizer> organizerList = this.getOrganizerList();
//        organizerList.put(userId, organizer);
//    }

    /**
     * Set the organizer list
     * @param organizerList the organizer list to be set
     */
    public void setOrganizerList(HashMap<Integer, Organizer> organizerList){
        this.organizerList = organizerList;
    }

    /**
     * Get organizer list
     * @return the list of organizer
     */
    public HashMap<Integer, Organizer> getOrganizerList() {
        return organizerList;
    }

    /**
     * Add event to a certain organizer
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    public void addEvent(int userId, int eventId){
        Organizer user = this.organizerList.get(userId);
        ArrayList<Integer> original_event = user.getEventHostList();
        if (!original_event.contains(eventId)) {
            original_event.add(eventId);
            user.setEventHostList(original_event);
        }
    }

    /**
     * Remove event from event list
     * @param userId the id of the user
     * @param eventId the id of the event
     */
    public void removeEvent(int userId, int eventId) {
        Organizer user = this.organizerList.get(userId);
        ArrayList<Integer> original_event = user.getEventHostList();
        original_event.remove(new Integer(eventId));
        user.setEventHostList(original_event);
    }

    /**
     * Get the event host list.
     * @param userId the id of the user
     * @return the event host list
     */
    public ArrayList<Integer> getEventHostList(int userId) {
        Organizer user = this.organizerList.get(userId);
        return user.getEventHostList();
    }

    /**
     * Get the message list that is sent
     * @param userId the id of the user
     * @return the message list
     */
    public ArrayList<Integer> getMessageListSent(int userId) {
        Organizer user = this.organizerList.get(userId);
        return user.getMessageListSent();
    }

    /**
     * Set the message list that is sent
     * @param userId the id of the user
     * @param messageListSent the message list to be set
     */
    public void setMessageListSent(int userId, ArrayList<Integer> messageListSent) {
        Organizer user = this.organizerList.get(userId);
        user.setMessageListSent(messageListSent);
    }

    /**
     * Get the password of the user.
     * @param userId the id of the user
     * @return password of the user
     */
    public String getPassword(int userId) {
        Organizer user = this.organizerList.get(userId);
        return user.getPassword();
    }

    /**
     * Set the password of the user.
     * @param userId the id of the user
     * @param password password to be set
     */
    public void setPassword(int userId, String password) {
        Organizer user = this.organizerList.get(userId);
        user.setPassword(password);
    }

    /**
     * @param userid the id of the user
     * @param password the password to be validated
     * @return true iff the password is not the same as the current one
     */
    public boolean validatePassword(int userid, String password){
        return this.getOrganizerList().get(userid).getPassword().equals(password);
    }

    /**
     * Get the username
     * @param userId the id of the user
     * @return username
     */
    public String getUsername(int userId) {
        Organizer user = this.organizerList.get(userId);
        return user.getUsername();
    }

    /**
     * Get the id of the organizer
     * @param userId the id of the user
     * @return the attendee id
     */
    public int getAttendeeId(int userId){
        Organizer user = this.organizerList.get(userId);
        return user.getAttendeeId();
    }

    /**
     * Add a messageId to the organizer.
     * @param userid the id of the user
     * @param messageId the message id to be added.
     */
    public void addMessageList(int userid, Integer messageId){
        Organizer user = this.organizerList.get(userid);
        ArrayList<Integer> messageList = user.getMessageListSent();
        messageList.add(messageId);
        setMessageListSent(userid, messageList);
    }

    /**
     * @param username the username
     * @return the id
     */
    public int nameToId(String username){
        for (int userid : getOrganizerList().keySet()) {
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
    public boolean isValidUsername(String username){
        return nameToId(username) != 4;
    }

    /**
     * @param username the username
     * @param password the password
     * @return the login status
     */
    public boolean attendeeLogin(String username, String password){
        int userid = nameToId(username);
        return (isValidUsername(username) && getPassword(userid).equals(password));
    }
}