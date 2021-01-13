package gateway.table;

import DataAccess.MysqlAccessIn;
import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.EventManager;

import java.sql.*;
import java.util.ArrayList;

public class DiscussionTable implements ManagerUpdater, DBUpdater {
    private final EventManager eventManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;

    public DiscussionTable(EventManager eventManager){
        this.eventManager = eventManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }

    @Override
    public void updateToManager() {
        ArrayList<ArrayList<String>> lst = dao.getDiscussionData();
        for (ArrayList<String>l: lst){
            eventManager.recreateDiscussion(
                    Integer.parseInt(l.get(0)),
                    tc.strToTimePair(l.get(1)),
                    l.get(2),
                    Integer.parseInt(l.get(3)),
                    Boolean.parseBoolean(l.get(4)),
                    Integer.parseInt(l.get(5)),
                    tc.strToArrayInt(l.get(6)),
                    Boolean.parseBoolean(l.get(7)),
                    tc.strToArrayInt(l.get(8)));
        }
    }


    /**
     * Update info from event manager to database for discussion with
     * @param id event id
     */
    @Override
    public void updateToDB(int id) {
        String time = tc.timePairToStr(eventManager.getTime(id));
        String title = eventManager.getTitle(id);
        int capacity = eventManager.getCapacity(id);
        boolean status = eventManager.getIsVip(id);
        int room = eventManager.getRoomId(id);
        String speaker = tc.arrayIntToStr(eventManager.getDiscussionSpeaker(id));
        boolean cancel = eventManager.getCancelStatus(id);
        String att = tc.arrayIntToStr(eventManager.getAttendants(id));
        dai.updateToDBDiscussion(id, time, title, capacity, status, room, speaker, cancel, att);
    }


    /**
     * insert info from event manager to database for discussion with
     * @param id event id
     */
    @Override
    public void insertToDB(int id) {
        String time = tc.timePairToStr(eventManager.getTime(id));
        String title = eventManager.getTitle(id);
        int capacity = eventManager.getCapacity(id);
        boolean status = eventManager.getIsVip(id);
        int room = eventManager.getRoomId(id);
        String speaker = tc.arrayIntToStr(eventManager.getDiscussionSpeaker(id));
        boolean cancel = eventManager.getCancelStatus(id);
        String att = tc.arrayIntToStr(eventManager.getAttendants(id));
        dai.insertToDBDiscussion(id, time, title, capacity, status, room, speaker, cancel, att);
    }
}
