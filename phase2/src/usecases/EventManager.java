package usecases;

import entities.event.*;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * This class represents the event manager of the program.
 */
public class EventManager {
    private HashMap<Integer, Event> eventList;
    private HashMap<Integer, Talk> talkList;
    private HashMap<Integer, Discussion> discussionList;
    private HashMap<Integer, Party> partyList;

    /**
     * Constructor of event manager.
     */
    public EventManager(){
        this.eventList = new HashMap<>();
        this.talkList = new HashMap<>();
        this.discussionList = new HashMap<>();
        this.partyList = new HashMap<>();
    }

    /**
     * Create a talk.
     * @param speaker the id of the speaker of this event
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public void createTalk(int speaker,
                            Pair<LocalDateTime, LocalDateTime> time,
                            String title,
                            int capacity,
                            boolean isVip,
                            int roomId){
        int eventId = eventList.size() + 1;
        Talk event = new Talk(eventId, time, title, speaker, capacity, isVip, roomId);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Talk> talkList = this.getTalkList();
        eventList.put(eventId, event);
        talkList.put(eventId, event);
        this.setEventList(eventList);
        this.setTalkList(talkList);
    }

    /**
     * Create a talk.
     * @param id id of the event
     * @param speaker the id of the speaker of this event
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     *              should only be used by gateway
     */
    public void recreateTalk(int id,
                             Pair<LocalDateTime, LocalDateTime> time,
                             String title,
                             int capacity,
                             boolean isVip,
                             int roomId,
                             int speaker,
                             boolean cancel,
                             ArrayList<Integer> att){
        Talk event = new Talk(id, time, title, speaker, capacity, isVip, roomId);
        if (cancel){
            event.setToCancelled();
        }
        event.setAttendants(att);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Talk> talkList = this.getTalkList();
        eventList.put(id, event);
        talkList.put(id, event);
        this.setEventList(eventList);
        this.setTalkList(talkList);
    }

    /**
     * Create a discussion.
     * @param speaker the id of the speakers of this event
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public void createDiscussion(ArrayList<Integer> speaker,
                            Pair<LocalDateTime, LocalDateTime> time,
                            String title,
                            int capacity,
                            boolean isVip, int roomId){
        int eventId = eventList.size() + 1;
        Discussion event = new Discussion(eventId, time, title, speaker, capacity, isVip, roomId);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Discussion> discussionList = this.getDiscussionList();
        eventList.put(eventId, event);
        discussionList.put(eventId, event);
        this.setEventList(eventList);
        this.setDiscussionList(discussionList);
    }

    /**
     * recreate a discussion.
     * @param id id of the event
     * @param speaker the id of the speakers of this event
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     *              should only be use in gateway.
     */
    public void recreateDiscussion(int id,
                                   Pair<LocalDateTime, LocalDateTime> time,
                                   String title,
                                   int capacity,
                                   boolean isVip,
                                   int roomId,
                                   ArrayList<Integer> speaker,
                                   boolean cancel,
                                   ArrayList<Integer> att){
        Discussion event = new Discussion(id, time, title, speaker, capacity, isVip, roomId);
        if (cancel){
            event.setToCancelled();
        }
        event.setAttendants(att);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Discussion> discussionList = this.getDiscussionList();
        eventList.put(id, event);
        discussionList.put(id, event);
        this.setEventList(eventList);
        this.setDiscussionList(discussionList);
    }

    /**
     * recreate a party.
     * @param id id of the event
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     *              should only be used by gateway
     */
    public void recreateParty(int id,
                              Pair<LocalDateTime, LocalDateTime> time,
                              String title,
                              int capacity,
                              boolean isVip,
                              int roomId,
                              boolean cancel,
                              ArrayList<Integer> att){
        Party event = new Party(id, time, title, capacity, isVip, roomId);
        if (cancel){
            event.setToCancelled();
        }
        event.setAttendants(att);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Party> partyList = this.getPartyList();
        eventList.put(id, event);
        partyList.put(id, event);
        this.setEventList(eventList);
        this.setPartyList(partyList);
    }

    /**
     * Create a party.
     * @param time pair of start time and end time of this event
     * @param title the title of this event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public void createParty(Pair<LocalDateTime, LocalDateTime> time,
                            String title,
                            int capacity,
                            boolean isVip,
                            int roomId){
        int eventId = eventList.size() + 1;
        Party event = new Party(eventId, time, title, capacity, isVip, roomId);
        HashMap<Integer,Event> eventList = this.getEventList();
        HashMap<Integer,Party> partyList = this.getPartyList();
        eventList.put(eventId, event);
        partyList.put(eventId, event);
        this.setEventList(eventList);
        this.setPartyList(partyList);
    }

    /**
     * Setter of the instance variable eventList.
     * @param eventList list of event as a hashmap
     */
    public void setEventList(HashMap<Integer,Event> eventList) {
        this.eventList = eventList;
    }

    /**
     * Getter of the instance variable eventList.
     * @return the instance variable eventList
     */
    public HashMap<Integer,Event> getEventList(){
        return eventList;
    }

    /**
     * Add an attendee into an event attendants.
     * @param eventId the id of the event that's been sign-up for
     * @param attendeeId the id of the attendee that's sign-up for the event
     */
    public void addAttendee(int eventId, int attendeeId){
        ArrayList<Integer> added = getAttendants(eventId);
        if (!added.contains(attendeeId)) {
            added.add(attendeeId);
            setAttendants(eventId, added);
        }
    }

    /**
     * Remove an attendee from an event attendants.
     * @param eventId the id of the event that's been sign-up for
     * @param attendeeId the id of the attendee that's going to cancel spots of the event
     */
    public void removeAttendee(int eventId, int attendeeId){
        ArrayList<Integer> removed = getAttendants(eventId);
        removed.remove(Integer.valueOf(attendeeId));
        setAttendants(eventId, removed);
    }

    /**
     * @param eventId the id of the event
     * @return the speaker id of that event
     */
    public ArrayList<Integer> getSpeaker(int eventId) {
        return eventList.get(eventId).getSpeaker();
    }

    /**
     * @param eventId the id of the event
     * @return the pair of start time and end time of that event
     */
    public Pair<LocalDateTime, LocalDateTime> getTime(int eventId) {
        return eventList.get(eventId).getTime();
    }

    /**
     * @param eventId the id of the event
     * @return the title of that event
     */
    public String getTitle(int eventId) {
        return eventList.get(eventId).getTitle();
    }

    /**
     * @param eventId the id of the event
     * @return the attendants of that event
     */
    public ArrayList<Integer> getAttendants(int eventId) {
        return eventList.get(eventId).getAttendants();
    }

    /**
     * Getter for the capacity of the event.
     * @param eventId the id of the event
     * @return capacity the capacity of the event.
     */
    public int getCapacity(int eventId) {
        return eventList.get(eventId).getCapacity();
    }

    /**
     * Getter for the isVip of this event.
     * @param eventId the id of the event
     * @return true if and only if this is a vip-only event.
     */
    public boolean getIsVip(int eventId){
        return eventList.get(eventId).getIsVip();
    }

    /**
     * getter for the talk list
     * @return the talk list
     */
    public HashMap<Integer, Talk> getTalkList() {
        return this.talkList;
    }

    /**
     * getter for the discussion list
     * @return the discussion list
     */
    public HashMap<Integer, Discussion> getDiscussionList() {
        return this.discussionList;
    }

    /**
     * getter for the party list
     * @return the party list
     */
    public HashMap<Integer, Party> getPartyList() {
        return this.partyList;
    }

    /**
     * Set the attendants of an event to be <attendants>.
     * @param eventId the id of the event
     * @param attendants an attendantsList
     */
    public void setAttendants(int eventId, ArrayList<Integer> attendants) {
        eventList.get(eventId).setAttendants(attendants);
    }

    /**
     * Set the title of an event to be <title>
     * @param eventId the id of the event
     * @param title a title
     */
    public void setTitle(int eventId, String title){
        eventList.get(eventId).setTitle(title);}

    /**
     * Setter for the capacity of the event.
     * @param eventId the id of the event
     * @param capacity the capacity of the event.
     */
    public void setCapacity(int eventId, int capacity) {
        eventList.get(eventId).setCapacity(capacity);
    }

    /**
     * Setter for the  of this event.
     * @param eventId the id of the event
     * @param isVip true if and only if this is a vip-only event.
     */
    public void setIsVip(int eventId, boolean isVip){
        eventList.get(eventId).setIsVip(isVip);
    }

    /**
     * setter for the discussion list
     * @param discussionList the discussion list
     */
    public void setDiscussionList(HashMap<Integer, Discussion> discussionList) {
        this.discussionList = discussionList;
    }

    /**
     * setter for the talk list
     * @param talkList the talk list
     */
    public void setTalkList(HashMap<Integer, Talk> talkList) {
        this.talkList = talkList;
    }

    /**
     * setter for the party list
     * @param partyList the party list
     */
    public void setPartyList(HashMap<Integer, Party> partyList) {
        this.partyList = partyList;
    }

    /**
     * Check if the event id is valid.
     * @param eventId the id of the event
     * @return if an event id correspond to an event recorded in this program or not
     */
    public boolean validateEventId(int eventId){
        return eventList.containsKey(eventId);
    }

    /**
     * Check if the organizer can change the capacity of the event.
     * @param eventId the id of the event
     * @param capacity the capacity to be set
     * @return true if and only if the capacity to be set is greater or equal to the number of people who have already
     * signed up for the event.
     */
    public boolean canSetCapacity(int eventId, int capacity){
        return capacity >= getAttendants(eventId).size();
    }

    /**
     * getter for the room
     * @param id the event id
     * @return the room
     */
    public int getRoomId(int id){
        return eventList.get(id).getRoomId();
    }

    /**
     * getter for the speaker
     * @param id the id of the event
     * @return the speakers
     */
    public ArrayList<Integer> getDiscussionSpeaker(int id){
        return discussionList.get(id).getSpeakerId();
    }

    /**
     * getter for the speaker
     * @param id the id of the event
     * @return the speakers
     */
    public int getTalkSpeaker(int id){
        return talkList.get(id).getSpeakerId();
    }

    /**
     * cancel the event
     * @param id the id of the event
     */
    public void cancelEvent(int id){
        eventList.get(id).setToCancelled();
    }

    /**
     * @param id the id of the event
     * @return the cancelled status
     */
    public boolean getCancelStatus(int id){
        return eventList.get(id).getCancelStatus();
    }

    /**
     * get the schedule list form the eventList
     * @return the schedule list
     */
    public HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> eventListToScheduleList() {
        HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList = new HashMap<>();
        HashMap<Integer, ArrayList<Event>> roomToEvent = new HashMap<>();
        for (int i = 1; i < 8; i++) {
            roomToEvent.put(i, new ArrayList<>());
        }
        Collection<Event> events = eventList.values();
        for (Event event: events) {
            roomToEvent.get(event.getRoomId()).add(event);
        }
        Set<Integer> rooms = roomToEvent.keySet();
        for (int roomId: rooms) {
            HashMap<Pair<LocalDateTime, LocalDateTime>, Integer> timeToEvent = new HashMap<>();
            for (Event event: roomToEvent.get(roomId)) {
                if (!this.getCancelStatus(event.getEventId())){
                    timeToEvent.put(event.getTime(), event.getEventId());
                }
            }
            scheduleList.put(roomId, timeToEvent);
        }
        int i = 1;
        while (i < 8) {
            if (scheduleList.get(i).isEmpty()) {
                scheduleList.remove(i);
            }
            i++;
        }
        return scheduleList;
    }
}

