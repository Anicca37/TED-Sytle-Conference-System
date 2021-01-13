package entities.event;

import javafx.util.Pair;

import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * An abstract event of the Event.
 */
public abstract class Event {
    private Pair<LocalDateTime, LocalDateTime> time;
    private String title;
    private final int eventId;
    private ArrayList<Integer> attendants;
    private int capacity;
    private boolean isVip;
    private int roomId;
    private boolean cancelled;

    /**
     * Constructor for Event
     * @param eventId the id of the event
     */
    public Event(int eventId){//should only be used by recreateEvent in EventManager
        this.eventId = eventId;
        this.attendants = new ArrayList<>();
    }

    /**
     * Constructor for Event
     * @param eventId the id of the event
     * @param time the time of the event
     * @param title the title of the event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public Event(int eventId, Pair<LocalDateTime, LocalDateTime> time, String title, int capacity,
                 boolean isVip, int roomId){
        this.eventId = eventId;
        this.time = time;
        this.title = title;
        this.attendants = new ArrayList<>();
        this.capacity = capacity;
        this.isVip = isVip;
        this.roomId = roomId;
        this.cancelled = false;
    }

    /**
     * Getter for the time of the event.
     * @return the time of the event.
     */
    public Pair<LocalDateTime, LocalDateTime> getTime() {
        return time;
    }

    /**
     * Getter for the id of the event.
     * @return the id of the event.
     */
    public int getEventId() {return eventId;}

    /**
     * Getter for the title of the event.
     * @return the title of the event.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the Attendants.
     * @return the Attendants.
     */
    public ArrayList<Integer> getAttendants() {
        return attendants;
    }

    /**
     * Getter for the capacity of the event.
     * @return capacity the capacity of the event.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Getter for the speakers of the event.
     * @return speaker the speakers of the event.
     */
    public ArrayList<Integer> getSpeaker() {
        return null;
    }

    /**
     * Getter for the isVip.
     * @return true if and only if this is a vip-only event.
     */
    public boolean getIsVip(){
        return isVip;
    }

    /**
     * Setter for the Attendants.
     * @param attendants the Attendants.
     */
    public void setAttendants(ArrayList<Integer> attendants) {
        this.attendants = attendants;
    }

    /**
     * Setter for the time of the event.
     * @param time the time of the event.
     */
    public void setTime(Pair<LocalDateTime, LocalDateTime> time) {
        this.time = time;
    }

    /**
     * Setter for the title of the event.
     * @param title the title of the event.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the capacity of the event.
     * @param capacity the capacity of the event.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Setter for the speakers of the event.
     * @param speaker the speakers of the event.
     */
    public void setSpeaker(ArrayList<Integer> speaker){}

    /**
     * Setter for the isVip.
     * @param isVip true if and only if this is a vip-only event.
     */
    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

    /**
     * setter for the roomId
     * @param roomId id of the room the event is hosting
     */
    public void setRoomId(int roomId) {this.roomId = roomId;
    }

    /**
     * getter for the roomId
     * @return id of the room
     */
    public int getRoomId() {return roomId;
    }

    /**
     * setter for the cancelled
     */
    public void setToCancelled(){
        this.cancelled = true;
    }

    /**
     * getter for the cancelled
     * @return the status of cancelled
     */
    public Boolean getCancelStatus(){
        return this.cancelled;
    }
}


