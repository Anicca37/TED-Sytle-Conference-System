package usecases;

/**
 * This class is a separate class that is intended for managing VIP functionality for Attendee.
 */
public class VipManager {
    public AttendeeManager attendeeManager;

    /**
     * Constructor
     * @param attendeeManager the attendee manager
     */
    public VipManager(AttendeeManager attendeeManager){
        this.attendeeManager = attendeeManager;
    }

    /**
     * Check if the attendee is validate to upgrade to a vip.
     * @param username the username to be updated
     * @return true iff the username is a name in AttendeeList and the user is not a vip
     */
    public boolean validateUpgrade(String username){
        if (attendeeManager.isValidUsername(username)){
            int userid = attendeeManager.nameToId(username);
            return !attendeeManager.getStatus(userid);
        }
        return false;
    }

    /**
     * Check if the attendee is validate to upgrade to a normal attendee.
     * @param username the username to be updated
     * @return true iff the username is a name in AttendeeList and the user is a vip
     */
    public boolean validateDowngrade(String username){
        if (attendeeManager.isValidUsername(username)){
            int userid = attendeeManager.nameToId(username);
            return attendeeManager.getStatus(userid);
        }
        return false;
    }

    /**
     * downgrade status
     * @param userid the id of user
     */
    public void downgrade(int userid){
        this.attendeeManager.getAttendeeList().get(userid).setStatus(false);
    }

    /**
     * upgrade status
     * @param userid the id of user
     */
    public void upgrade(int userid){
        this.attendeeManager.getAttendeeList().get(userid).setStatus(true);
    }
}
