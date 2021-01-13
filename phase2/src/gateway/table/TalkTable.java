package gateway.table;

import DataAccess.MysqlAccessIn;
import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.EventManager;

import java.sql.*;
import java.util.ArrayList;

public class TalkTable implements ManagerUpdater, DBUpdater{
    private final EventManager eventManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;

    public TalkTable(EventManager eventManager){
        this.eventManager = eventManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }

    /**
     * save all the Talk data into manager
     */
    @Override
    public void updateToManager() {
        ArrayList<ArrayList<String>> lst = dao.getTalkData();
        for (ArrayList<String>l: lst){
            eventManager.recreateTalk(
                    Integer.parseInt(l.get(0)),
                    tc.strToTimePair(l.get(1)),
                    l.get(2),
                    Integer.parseInt(l.get(3)),
                    Boolean.parseBoolean(l.get(4)),
                    Integer.parseInt(l.get(5)),
                    Integer.parseInt(l.get(6)),
                    Boolean.parseBoolean(l.get(7)),
                    tc.strToArrayInt(l.get(8)));
        }
    }

    /**
     * Update info from event manager to database for talk with
     * @param id event id
     */
    @Override
    public void updateToDB(int id) {
        String time = tc.timePairToStr(eventManager.getTime(id));
        String title = eventManager.getTitle(id);
        int capacity = eventManager.getCapacity(id);
        boolean status = eventManager.getIsVip(id);
        int room = eventManager.getRoomId(id);
        int speaker = eventManager.getTalkSpeaker(id);
        boolean cancel = eventManager.getCancelStatus(id);
        String att = tc.arrayIntToStr(eventManager.getAttendants(id));
        dai.updateToDBTalk(id, time, title, capacity, status, room, speaker, cancel, att);
    }


    /**
     * Update info from event manager to database for talk with
     * @param id event id
     */
    @Override
    public void insertToDB(int id) {
        String time = tc.timePairToStr(eventManager.getTime(id));
        String title = eventManager.getTitle(id);
        int capacity = eventManager.getCapacity(id);
        boolean status = eventManager.getIsVip(id);
        int room = eventManager.getRoomId(id);
        int speaker = eventManager.getTalkSpeaker(id);
        boolean cancel = eventManager.getCancelStatus(id);
        String att = tc.arrayIntToStr(eventManager.getAttendants(id));
        dai.insertToDBTalk(id, time, title, capacity, status, room, speaker, cancel, att);
    }




}
