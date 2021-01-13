package gateway.table;

import DataAccess.MysqlAccessIn;
import DataAccess.MysqlAccessOut;
import gateway.DataAccessIn;
import gateway.DataAccessOut;
import gateway.TypeConverter;
import usecases.MessageManager;

import java.sql.*;
import java.util.ArrayList;

public class MessageTable implements ManagerUpdater, DBUpdater {
    private final MessageManager messageManager;
    private final TypeConverter tc;
    private final DataAccessIn dai;
    private final DataAccessOut dao;

    public MessageTable(MessageManager messageManager){
        this.messageManager = messageManager;
        this.tc = new TypeConverter();
        this.dai = new MysqlAccessIn();
        this.dao = new MysqlAccessOut();
    }

    /**
     * save all the Message data into manager
     */
    @Override
    public void updateToManager() {
        ArrayList<ArrayList<String>> lst = dao.getMessageData();
        for (ArrayList<String>l: lst){
            messageManager.recreateMessage(
                    Integer.parseInt(l.get(0)),
                    l.get(1),
                    Integer.parseInt(l.get(2)),
                    Integer.parseInt(l.get(3)),
                    tc.strToTime(l.get(4)),
                    tc.strToArrayInt(l.get(5)));
        }
    }


    /**
     * update info from message manager to database for message with
     * @param id message id
     */
    @Override
    public void updateToDB(int id) {
        String content = messageManager.getContent(id);
        int sender = messageManager.getSender(id);
        int receiver = messageManager.getReceiver(id);
        String time = tc.timeToStr(messageManager.getTime(id));
        String reply = tc.arrayIntToStr(messageManager.getReplyList(id));
        dai.updateToDBMessage(id, content, sender, receiver, time, reply);
    }


    /**
     * insert info from message manager to database for message with
     * @param id message id
     */
    @Override
    public void insertToDB(int id) {
        String content = messageManager.getContent(id);
        int sender = messageManager.getSender(id);
        int receiver = messageManager.getReceiver(id);
        String time = tc.timeToStr(messageManager.getTime(id));
        String reply = tc.arrayIntToStr(messageManager.getReplyList(id));
        dai.insertToDBMessage(id, content, sender, receiver, time, reply);
    }
}
