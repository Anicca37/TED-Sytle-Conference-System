package gateway;

import gateway.table.MessageTable;
import usecases.MessageManager;

public class MmToDbUpdater {
    private final MessageTable mt;

    /**
     * constructor method
     * @param mm message manager
     *           this class add new message to database
     */
    public MmToDbUpdater(MessageManager mm){
        mt = new MessageTable(mm);
    }

    /**
     * add a new message into database when it's created in the system
     * @param id message id
     */
    public void addNewMessage(int id){
        mt.insertToDB(id);
    }

    /**
     * update message into database when it's created in the system
     * @param id message id
     */
    public void updateMessage(int id){
        mt.updateToDB(id);
    }
}
