package gateway;

import gateway.table.AttendeeTable;
import gateway.table.DBUpdater;
import usecases.AttendeeManager;

public class AmToDbUpdater {
    private final AttendeeTable at;

    public AmToDbUpdater(AttendeeManager am){
        at = new AttendeeTable(am);
    }

    /**
     * update an attendee to database when it's changed in the system
     * @param id id of attendee
     */
    public void updateAttendee(int id){
        at.updateToDB(id);
    }

    /**
     * add a new attendee into database when it's created in the system
     * @param id attendee id
     */
    public void addNewAttendee(int id){
        at.insertToDB(id);
    }
}
