package entities.event;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An one-speaker event.
 */
public class Talk extends Event{
    private int speakerId;


    /**
     * Constructor for Talk
     * @param eventId the id of the event
     * @param time the time of the event
     * @param title the title of the event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public Talk(int eventId,
                Pair<LocalDateTime, LocalDateTime> time,
                String title,
                int speakerId,
                int capacity,
                boolean isVip,
                int roomId){
        super(eventId, time, title, capacity, isVip, roomId);
        this.speakerId = speakerId;
    }

    /**
     * Getter for the speaker of the event.
     * @return the speaker of the event.
     */
    @Override
    public ArrayList<Integer> getSpeaker() {
        ArrayList<Integer> speaker = new ArrayList<>(1);
        speaker.add(speakerId);
        return speaker;
    }

    /**
     * Setter for the speaker of the event.
     * @param speaker the speaker of the event.
     *
     * Precondition: speaker.size() == 1
     */
    @Override
    public void setSpeaker(ArrayList<Integer> speaker) {
        this.speakerId = speaker.get(0);
    }

    /**
     * Getter for the speaker of the event.
     * @return the speaker of the event.
     */
    public int getSpeakerId(){
        return speakerId;
    }
}
