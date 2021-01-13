package gateway;

import gateway.table.OrganizerTable;
import usecases.OrganizerManager;

public class OmToDbUpdater {
    private final OrganizerTable ot;

    public OmToDbUpdater(OrganizerManager om){
        ot = new OrganizerTable(om);
    }

    /**
     * update an organizer to database when it's changed in the system
     * @param id id of organizer
     */
    public void updateOrganizer(int id){
        ot.updateToDB(id);
    }

    /**
     * add a new attendee into database when it's created in the system
     * @param id attendee id
     */
    public void addNewOrganizer(int id){
        ot.insertToDB(id);
    }
}
