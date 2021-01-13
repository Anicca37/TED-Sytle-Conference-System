package entities.event;

import javafx.util.Pair;

import java.time.LocalDateTime;

/**
 * A no-speaker event.
 */
public class Party extends Event{


    /**
     * Constructor for Party
     * @param eventId the id of the event
     * @param time the time of the event
     * @param title the title of the event
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     */
    public Party(int eventId,
                 Pair<LocalDateTime, LocalDateTime> time,
                 String title,
                 int capacity,
                 boolean isVip,
                 int roomId){
        super(eventId, time, title, capacity, isVip, roomId);
    }
}
