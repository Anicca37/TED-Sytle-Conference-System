package entities.event;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A multi-speaker event.
 */
public class Discussion extends Event{
    private ArrayList<Integer> speakerId;


    /**
     * Constructor for Discussion
     * @param eventId the id of the event
     * @param time the time of the event
     * @param title the title of the event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public Discussion(int eventId,
                      Pair<LocalDateTime, LocalDateTime> time,
                      String title,
                      ArrayList<Integer> speakerId,
                      int capacity,
                      boolean isVip,
                      int roomId){
        super(eventId, time, title, capacity, isVip, roomId);
        this.speakerId = speakerId;
    }

    /**
     * Getter for the speakers of the event.
     * @return the speakers of the event.
     */
    @Override
    public ArrayList<Integer> getSpeaker() {
        return speakerId;
    }

    /**
     * Setter for the speakers of the event.
     * @param speaker the speakers of the event.
     */
    @Override
    public void setSpeaker(ArrayList<Integer> speaker) {
        this.speakerId = speaker;
    }

    /**
     * Getter for the speakers of the event.
     * @return the speakers of the event.
     */
    public ArrayList<Integer> getSpeakerId(){
        return speakerId;
    }
}