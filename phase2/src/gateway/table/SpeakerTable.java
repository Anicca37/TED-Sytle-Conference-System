package gateway.table;

import DataAccess.MysqlAccessIn;
import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.SpeakerManager;

import java.sql.*;
import java.util.ArrayList;

public class SpeakerTable implements ManagerUpdater, DBUpdater {
    private final SpeakerManager speakerManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;

    public SpeakerTable(SpeakerManager speakerManager) {
        this.speakerManager = speakerManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }

    /**
     * save all the Speaker data into manager
     */
    @Override
    public void updateToManager() {
        ArrayList<ArrayList<String>> lst = dao.getSpeakerData();
        for (ArrayList<String>l: lst){
            speakerManager.recreateSpeaker(
                    Integer.parseInt(l.get(0)),
                    l.get(1),
                    l.get(2),
                    tc.strToArrayInt(l.get(3)),
                    tc.strToArrayInt(l.get(4)),
                    tc.strToArrayInt(l.get(5)),
                    tc.strToArrayInt(l.get(6)),
                    tc.strToArrayInt(l.get(7)),
                    tc.strToArrayInt(l.get(8)));
        }
    }


    /**
     * Update info from speaker manager to database for speaker with
     * @param id speaker id
     */
    @Override
    public void updateToDB(int id) {
        String pwd = speakerManager.getPassword(id);
        String msgsent = tc.arrayIntToStr(speakerManager.getMessageListSent(id));
        String events = tc.arrayIntToStr(speakerManager.getSpeechList(id));
        String msgrecv = tc.arrayIntToStr(speakerManager.getMessageListReceived(id));
        String msgunread = tc.arrayIntToStr(speakerManager.getMessageListunread(id));
        String msgdelete = tc.arrayIntToStr(speakerManager.getMessageListdeleted(id));
        String msgarch = tc.arrayIntToStr(speakerManager.getMessageListarchived(id));
        dai.updateToDBSpeaker(id, pwd, msgsent, events, msgrecv,
                msgunread, msgdelete, msgarch);
    }


    /**
     * insert info from speaker manager to database for speaker with
     * @param id speaker id
     */
    @Override
    public void insertToDB(int id) {
        String pwd = speakerManager.getPassword(id);
        String username = speakerManager.getUsername(id);
        String msgsent = tc.arrayIntToStr(speakerManager.getMessageListSent(id));
        String events = tc.arrayIntToStr(speakerManager.getSpeechList(id));
        String msgrecv = tc.arrayIntToStr(speakerManager.getMessageListReceived(id));
        String msgunread = tc.arrayIntToStr(speakerManager.getMessageListunread(id));
        String msgdelete = tc.arrayIntToStr(speakerManager.getMessageListdeleted(id));
        String msgarch = tc.arrayIntToStr(speakerManager.getMessageListarchived(id));
        dai.insertToDBSpeaker(id, pwd, username, msgsent, events, msgrecv,
                msgunread, msgdelete, msgarch);
    }

}
