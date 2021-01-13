package entities.user;

import java.util.ArrayList;

/**
 * Stores information about organizer.
 */
public class Organizer extends User {
    private int attendeeId;
    private ArrayList<Integer> eventHostList;


    /**
     * Constructor for organizer.
     * @param userId the userId of Organizer
     */
    //should only be used in user usecase to recreate entities
    public Organizer(int userId){
        super(userId);
        this.eventHostList = new ArrayList<>();
    }

    /**
     * Constructor for organizer.
     * @param userId the userId of Organizer
     * @param password the password of Organizer
     * @param username the username of Organizer
     * @param attendeeId the attendeeId
     */
    public Organizer(int userId, String password, String username, int attendeeId){
        super(userId, password, username);
        this.attendeeId = attendeeId;
        this.eventHostList = new ArrayList<>();
    }

    /**
     * Getter for the eventHostList
     * @return the eventHostList of Organizer
     */
    public ArrayList<Integer> getEventHostList() {
        return eventHostList;
    }

    /**
     * Setter for the eventHostList
     * @param eventHostList the eventHostList of Organizer
     */
    public void setEventHostList(ArrayList<Integer> eventHostList) {
        this.eventHostList = eventHostList;
    }

    /**
     * Getter for the attendee id
     * @return the attendee id
     */
    public int getAttendeeId() {
        return attendeeId;
    }

    /**
     * Setter for the attendee id
     * @param attendeeId the attendee id
     */
    public void setAttendeeId(int attendeeId) {
        this.attendeeId = attendeeId;
    }

    /**
     * String representation of organizer
     * @return the string
     */
    public String toString() {
        return "Organizer{" +
                "id=" + getUserId() +
                ", pwd=" + getPassword() +
                ", username=" + getUsername() +
                ", msgsent=" + getMessageListSent() +
                ", attendid=" + attendeeId +
                ", eventList=" + eventHostList +
                '}';
    }
}
