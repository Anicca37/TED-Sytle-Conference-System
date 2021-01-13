package gateway.table;

import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.AttendeeManager;
import DataAccess.MysqlAccessIn;

import java.util.ArrayList;

public class AttendeeTable implements ManagerUpdater, DBUpdater{
    private final AttendeeManager attendeeManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;


    public AttendeeTable(AttendeeManager attendeeManager){
        this.attendeeManager = attendeeManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }



    /**
     * save all the attendee data into attendee manager
     */
    @Override
    public void updateToManager(){
        ArrayList<ArrayList<String>> lst = dao.getAttendeeData();
        for (ArrayList<String>l: lst){
            attendeeManager.recreateAttendee(
                    Integer.parseInt(l.get(0)),
                    l.get(1),
                    l.get(2),
                    Boolean.parseBoolean(l.get(3)),
                    tc.strToArrayInt(l.get(4)),
                    tc.strToArrayInt(l.get(5)),
                    tc.strToArrayInt(l.get(6)),
                    tc.strToArrayInt(l.get(7)),
                    tc.strToArrayInt(l.get(8)),
                    tc.strToMap(l.get(9)),
                    tc.strToArrayInt(l.get(10)),
                    tc.strToArrayInt(l.get(11)),
                    tc.strToArrayInt(l.get(12)));
        }

    }


    /**
     * Update info from attendee manager to database for attendee with
     * @param id attendee id
     */
    @Override
    public void updateToDB(int id) {
        String pwd = attendeeManager.getPassword(id);
        boolean status = attendeeManager.getStatus(id);
        String msgsent = tc.arrayIntToStr(attendeeManager.getMessageListSent(id));
        String msgrecv = tc.arrayIntToStr(attendeeManager.getMessageListReceived(id));
        String friends = tc.arrayIntToStr(attendeeManager.getFriendList(id));
        String events = tc.arrayIntToStr(attendeeManager.getEventList(id));
        String requests = tc.arrayIntToStr(attendeeManager.getRequestList(id));
        String add_request = tc.mapToStr(attendeeManager.getSRequestList(id));
        String msgunread = tc.arrayIntToStr(attendeeManager.getMessageListunread(id));
        String msgdelete = tc.arrayIntToStr(attendeeManager.getMessageListdeleted(id));
        String msgarch = tc.arrayIntToStr(attendeeManager.getMessageListarchived(id));
        dai.updateToDBAttendee(id, pwd, status, msgsent, msgrecv, friends, events, requests, add_request,
                msgunread, msgdelete, msgarch);
    }


    /**
     * insert info from attendee manager to database for attendee with
     * @param id attendee id
     */
    @Override
    public void insertToDB ( int id){
        String pwd = attendeeManager.getPassword(id);
        String username = attendeeManager.getUsername(id);
        boolean status = attendeeManager.getStatus(id);
        String msgsent = tc.arrayIntToStr(attendeeManager.getMessageListSent(id));
        String msgrecv = tc.arrayIntToStr(attendeeManager.getMessageListReceived(id));
        String friends = tc.arrayIntToStr(attendeeManager.getFriendList(id));
        String events = tc.arrayIntToStr(attendeeManager.getEventList(id));
        String requests = tc.arrayIntToStr(attendeeManager.getRequestList(id));
        String add_request = tc.mapToStr(attendeeManager.getSRequestList(id));
        String msgunread = tc.arrayIntToStr(attendeeManager.getMessageListunread(id));
        String msgdelete = tc.arrayIntToStr(attendeeManager.getMessageListdeleted(id));
        String msgarch = tc.arrayIntToStr(attendeeManager.getMessageListarchived(id));
        dai.insertToDBAttendee(id, pwd, username, status, msgsent, msgrecv, friends, events, requests, add_request,
                msgunread, msgdelete, msgarch);
    }

}
