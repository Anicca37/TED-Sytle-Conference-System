package usecases;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores general information about the event that has already been scheduled (schedule list) and provides
 * an occupancy list for organizers to view available rooms in specific date and time.
 */
public class RoomManager {


//  scheduleList: Hashmap<roomId: Hashmap<Time period, eventID>>

    private HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList;
    private final HashMap<Integer, Integer> roomCapacity;


    /**
     * Constructor for the RoomManager.
     */
    public RoomManager(){
        this.scheduleList = new HashMap<>();
        this.roomCapacity = new HashMap<>();
        this.roomCapacity.put(1, 10);
        this.roomCapacity.put(2, 10);
        this.roomCapacity.put(3, 30);
        this.roomCapacity.put(4, 30);
        this.roomCapacity.put(5, 50);
        this.roomCapacity.put(6, 50);
        this.roomCapacity.put(7, 100);
    }


    /**
     * Update scheduleList when a new event is scheduled.
     *
     * @param time starting time and end time of the event
     * @param roomId the roomId that the event is held
     * @param eventId the eventId to be scheduled(added)
     */
    public void setEvent(Pair<LocalDateTime, LocalDateTime> time, int roomId, int eventId){
        if (scheduleList.containsKey(roomId)){
            scheduleList.get(roomId).put(time, eventId);
        }else {
            HashMap<Pair<LocalDateTime, LocalDateTime>, Integer> event = new HashMap<>();
            event.put(time, eventId);
            scheduleList.put(roomId, event);
        }
    }


    /**
     * Remove an event from scheduleList when an event is cancelled.
     * @param eventId the id of the event to be cancelled
     */
    public void removeEvent(int eventId){
        HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> tempMap = new HashMap<>();
        for (int roomId : scheduleList.keySet()){
            for (Pair<LocalDateTime, LocalDateTime> time : scheduleList.get(roomId).keySet()){
                if (scheduleList.get(roomId).get(time) == eventId){
                    HashMap<Pair<LocalDateTime, LocalDateTime>, Integer> temp = new HashMap<>();
                    temp.put(time, eventId);
                    tempMap.put(roomId, temp);
                }
            }
        }

        if (!tempMap.isEmpty()){
            for (int roomId : tempMap.keySet()){
                scheduleList.remove(roomId, tempMap.get(roomId));
            }
        }
    }


    /**
     * Getter for the room of an event.
     * @param eventId the id of the event.
     * @return the room that this event is held.
     */
    public int getRoom(int eventId){
        for (int roomId : scheduleList.keySet()){
            for (Pair<LocalDateTime, LocalDateTime> time : scheduleList.get(roomId).keySet()){
                if (scheduleList.get(roomId).get(time) == eventId){
                    return roomId;
                }
            }
        }
        return 0;
    }


    /**
     * Getter for the schedule list.
     *
     * @return scheduleList
     */
    public HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> getScheduleList(){
        return scheduleList;
    }


    /**
     * Setter for the scheduleList
     *
     * @param scheduleList the scheduleList to be set
     */
    public void setScheduleList(HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public HashMap<Integer, Integer> getRoomCapacity() {
        return roomCapacity;
    }


    /**
     * Provide a string representation of scheduleList.
     *
     * @param em an event manager
     * @param sp a speaker manager
     * @return the string representation of scheduleList
     */
    public String scheduleListToString(EventManager em, SpeakerManager sp){
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> entry1: scheduleList.entrySet()){
            int roomId = entry1.getKey();
            res.append("The events below are in room ").append(roomId).append(": \n");
            for (Map.Entry<Pair<LocalDateTime, LocalDateTime>, Integer> entry2 : scheduleList.get(roomId).entrySet()){

//          the time period
                Pair<LocalDateTime, LocalDateTime> time = entry2.getKey();
//          the starting time
                LocalDateTime start = time.getKey();
//          the end time
                LocalDateTime end = time.getValue();
                int eventId = entry2.getValue();
                int capacity = em.getCapacity(eventId);
                boolean isVip = em.getIsVip(eventId);
                String Vip = " ";
                if(isVip){
                    Vip = " This event is vip-only. ";
                }
                res.append("The event id is ").append(eventId).append(", and it is from ").
                        append(start).append(" to ").append(end).append(".").append(Vip).
                        append("The capacity of this event is ").append(capacity).append(". ");

                int speakerId;
                if (em.getSpeaker(eventId) == null){
//                  party
                    res.append("The title of this event is ").append(em.getTitle(eventId)).append(". \n");
                }else if (em.getSpeaker(eventId).size() == 1){
//                  talk
                    speakerId = em.getSpeaker(eventId).get(0);
                    res.append("The title of this event is ").append(em.getTitle(eventId)).
                            append(", and the speaker is ").append(sp.getUsername(speakerId)).append(". \n");
                }else{
//                  discussion
                    res.append("The title of this event is ").append(em.getTitle(eventId)).
                            append(", and the speakers are ");
                    for (int speaker : em.getSpeaker(eventId)){
                        res.append(sp.getUsername(speaker)).append(", ");
                    }
                    res.delete(res.length()-2, res.length());
                    res.append(". \n");
                }
            }
        }
        return res.toString();
    }
}
