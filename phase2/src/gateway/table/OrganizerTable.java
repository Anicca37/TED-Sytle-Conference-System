package gateway.table;

import DataAccess.MysqlAccessIn;
import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.OrganizerManager;

import java.sql.*;
import java.util.ArrayList;

public class OrganizerTable implements ManagerUpdater, DBUpdater{
    private final OrganizerManager organizerManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;



    public OrganizerTable(OrganizerManager organizerManager) {
        this.organizerManager = organizerManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }



    /**
     * save all the Attendee data into manager
     */
    @Override
    public void updateToManager() {
        ArrayList<ArrayList<String>> lst = dao.getOrganizerData();
        String sql = "SELECT * FROM Organizers";
        for (ArrayList<String>l: lst) {
            organizerManager.recreateOrganizer(Integer.parseInt(l.get(0)),
                    l.get(1),
                    l.get(2),
                    Integer.parseInt(l.get(3)),
                    tc.strToArrayInt(l.get(4)),
                    tc.strToArrayInt(l.get(5)));
        }
    }

    /**
     * update info from organizer manager to database for speaker with
     * @param id organizer id
     */
    @Override
    public void updateToDB(int id) {
        String pwd = organizerManager.getPassword(id);
        String msgsent = tc.arrayIntToStr(organizerManager.getMessageListSent(id));
        String events = tc.arrayIntToStr(organizerManager.getEventHostList(id));
        dai.updateToDBOrganizer(id, pwd, msgsent, events);
    }


    /**
     * insert info from organizer manager to database for organizer with
     * @param id organizer id
     */
    @Override
    public void insertToDB(int id) {
        String pwd = organizerManager.getPassword(id);
        String username = organizerManager.getUsername(id);
        int attendeeId = organizerManager.getAttendeeId(id);
        String msgsent = tc.arrayIntToStr(organizerManager.getMessageListSent(id));
        String events = tc.arrayIntToStr(organizerManager.getEventHostList(id));
        dai.insertToDBOrganizer(id, pwd, username, attendeeId, msgsent, events);
    }
}
