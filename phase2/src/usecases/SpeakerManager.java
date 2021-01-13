package usecases;

import entities.user.Attendee;
import entities.user.Speaker;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * This class stores the information of all speakers.
 */
public class SpeakerManager{
    private HashMap<Integer, Speaker> speakerList;

    /**
     * Constructor of the speaker
     */
    public SpeakerManager(){
        this.speakerList = new HashMap<>();
    }

    /**
     * Create speaker used by gateway
     * @param userId the id of the user
     */
    public void recreateSpeaker(int userId, String password, String username, ArrayList<Integer> messageSent,
                                ArrayList<Integer> eventLst, ArrayList<Integer> messageReceived,
                                ArrayList<Integer> messageUnread, ArrayList<Integer> messageDeleted,
                                ArrayList<Integer> messageArchived
                                ){
        Speaker user = new Speaker(userId);
        this.speakerList.put(userId, user);
        user.setUsername(username);
        user.setPassword(password);
        user.setMessageListSent(messageSent);
        user.setSpeechList(eventLst);
        user.setMessageListReceived(messageReceived);
        user.setMessageListarchived(messageArchived);
        user.setMessageListdeleted(messageDeleted);
        user.setMessageListunread(messageUnread);
    }

    /**
     * Add a speaker to the speakerList.
     * @param password the password to be set
     * @param username the username to be set
     */
    public void createSpeaker(String password, String username){
        String id = '2' + Integer.toString(speakerList.size() + 1);
        int userId = Integer.parseInt(id);
        Speaker speaker = new Speaker(userId, password, username);
        this.speakerList.put(userId, speaker);
    }

    /**
     * Update the speakerList.
     * @param speakerList the speakerList to be updated
     */
    public void setSpeakerList(HashMap<Integer, Speaker> speakerList){
        this.speakerList = speakerList;
    }

    /**
     * @return The speakerList
     */
    public HashMap<Integer, Speaker> getSpeakerList() {
        return speakerList;
    }

    /**
     * Add the event to the speaker if this event is not in the speechList.
     * @param userId: the id of the speaker
     * @param eventId the id of the event to be added
     */
    public void addEvent(int userId, int eventId){
        Speaker user = this.speakerList.get(userId);
        ArrayList<Integer> original_event = user.getSpeechList();
        if (!original_event.contains(eventId)) {
            original_event.add(eventId);
            user.setSpeechList(original_event);
        }
    }

    /**
     * Remove the event from the list
     * @param userId the id of user
     * @param eventId the id of event to be removed
     */
    public void removeEvent(int userId, int eventId) {
        Speaker user = this.speakerList.get(userId);
        ArrayList<Integer> original_event = user.getSpeechList();
        original_event.remove(Integer.valueOf(eventId));
        user.setSpeechList(original_event);
    }

    /**
     * @param userId id of the user
     * @return speechList
     */
    public ArrayList<Integer> getSpeechList(int userId) {
        Speaker user = this.speakerList.get(userId);
        return user.getSpeechList();
    }

    /**
     * @param userId the id of the user
     * @return the password of user
     */
    public String getPassword(int userId) {
        Speaker user = this.speakerList.get(userId);
        return user.getPassword();
    }

    /**
     * @param userId the id of the user
     * @param password the password to be set
     */
    public void setPassword(int userId, String password) {
        Speaker user = this.speakerList.get(userId);
        user.setPassword(password);
    }

    /**
     * @param userid the id of the user
     * @param password the password to be validated
     * @return true iff the password is not the same as the current one
     */
    public boolean validatePassword(int userid, String password){
        return this.getSpeakerList().get(userid).getPassword().equals(password);
    }

    /**
     * @param userId the id of the user
     * @return the name of the user
     */
    public String getUsername(int userId) {
        Speaker user = this.speakerList.get(userId);
        return user.getUsername();
    }

    /**
     * @param username the username
     * @return the user id
     */
    public int nameToId(String username){
        for (int userid : getSpeakerList().keySet()) {
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

    public boolean attendeeLogin(String username, String password){
        int userid = nameToId(username);
        return (isValidUsername(username) && getPassword(userid).equals(password));
    }

    /**
     * @return the current number of speakers
     * Used for creating id for registering new speaker account.
     */
    public int getNumSpeaker(){
        return this.speakerList.size();
    }

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageListSent messageListSent the user to be set.
     */
    public void setMessageListSent(int userid, ArrayList<Integer> messageListSent){
        Speaker user = this.speakerList.get(userid);
        user.setMessageListSent(messageListSent);
    }

    /**
     * Get the message list of the attendee.
     * @param userid userid the id of the user.
     * @return messageList sent the user to be get.
     */
    public ArrayList<Integer> getMessageListSent(int userid){
        Speaker user = this.speakerList.get(userid);
        return user.getMessageListSent();

    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListSent(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListSent = user.getMessageListSent();
        messageListSent.add(messageId);
        setMessageListSent(userid, messageListSent);
    }

    /**
     * Get message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListReceived(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListReceived = user.getMessageListReceived();
        messageListReceived.add(messageId);
        setMessageListReceived(userid, messageListReceived);
    }

    /**
     * get message to the list
     * @param userid the id of the attendee
     */
    public ArrayList<Integer> getMessageListunread(int userid){
        Speaker user = this.speakerList.get(userid);
        return user.getMessageListunread();
    }

    /**
     * get message to the list
     * @param userid the id of the attendee
     */
    public ArrayList<Integer> getMessageListdeleted(int userid){
        Speaker user = this.speakerList.get(userid);
        return user.getMessageListdeleted();
    }

    /**
     * get message to the list
     * @param userid the id of the attendee
     */
    public ArrayList<Integer> getMessageListarchived(int userid){
        Speaker user = this.speakerList.get(userid);
        return user.getMessageListarchived();
    }

    /**
     * set message to the list
     * @param userid the id of the attendee
     * @param messageListunread the list to be set
     */
    public void setMessageListunread(int userid, ArrayList<Integer> messageListunread){
        Speaker user = this.speakerList.get(userid);
        user.setMessageListunread(messageListunread);
    }

    /**
     * Set the message list of the attendee
     * @param userid userid the id of the user.
     * @param messageListReceived message list Received of the user to be set.
     */
    public void setMessageListReceived(int userid, ArrayList<Integer> messageListReceived){
        Speaker user = this.speakerList.get(userid);
        user.setMessageListReceived(messageListReceived);
    }

    /**
     * Get the message list of the attendee.
     * @param userid userid the id of the user.
     * @return messageListReceived of the user
     */
    public ArrayList<Integer> getMessageListReceived(int userid){
        Speaker user = this.speakerList.get(userid);
        return user.getMessageListReceived();
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListUnread(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListUnread = user.getMessageListunread();
        messageListUnread.add(messageId);
        setMessageListunread(userid,messageListUnread);
    }

    /**
     * Add message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void addMessageListDeleted(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListDeleted = user.getMessageListdeleted();
        messageListDeleted.add(messageId);
        setMessageListDeleted(userid,messageListDeleted);
    }

    /**
     * Set the message list of the speaker
     * @param userid userid the id of the user.
     * @param messageListDeleted message list Received of the user to be set.
     */
    public void setMessageListDeleted(int userid, ArrayList<Integer> messageListDeleted){
        Speaker user = this.speakerList.get(userid);
        user.setMessageListdeleted(messageListDeleted);
    }

    /**
     * remove message to the list
     * @param userid the id of the attendee
     * @param messageId the messageId to be added
     */
    public void removeMessageListReceived(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListReceived = user.getMessageListReceived();
        messageListReceived.remove(Integer.valueOf(messageId));
        setMessageListReceived(userid, messageListReceived);
    }

    /**
     * String representation for all speakers.
     * @return a string
     */
    public String allSpeakersToString(){
        StringBuilder res = new StringBuilder();
        for (int speakerId : this.speakerList.keySet()){
            String name  = this.speakerList.get(speakerId).getUsername();
            res.append("The id of this speaker is ").append(speakerId).
                    append(" ,  and the username of the speaker is ").append(name).append(" .\n");
        }
        return res.toString();
    }
    public void addMessageListArchived(int userid, Integer messageId){
        Speaker user = this.speakerList.get(userid);
        ArrayList<Integer> messageListArchived = user.getMessageListarchived();
        messageListArchived.add(messageId);
        setMessageListarchived(userid,messageListArchived);
    }

    public void setMessageListarchived(int userid, ArrayList<Integer> messageListArchived){
        Speaker user = this.speakerList.get(userid);
        user.setMessageListarchived(messageListArchived);
    }
}