package gateway;

import gateway.table.DiscussionTable;
import gateway.table.PartyTable;
import gateway.table.TalkTable;
import usecases.EventManager;

public class EmToDbUpdater {
    private final DiscussionTable dt;
    private final PartyTable pt;
    private final TalkTable tt;
    private final EventManager em;

    /**
     * constructor method
     * @param em event manager
     * this class update and add event to database
     */
    public EmToDbUpdater(EventManager em){
        TypeConverter tc = new TypeConverter();
        dt = new DiscussionTable(em);
        pt = new PartyTable(em);
        tt = new TalkTable(em);
        this.em = em;
    }

    /**
     * update an event to database when it's changed in the system
     * @param id id of event
     */
    public void updateEvent(int id){
        if (em.getDiscussionList().containsKey(id)){
            dt.updateToDB(id);
        }
        if (em.getPartyList().containsKey(id)){
            pt.updateToDB(id);
        }
        if (em.getTalkList().containsKey(id)){
            tt.updateToDB(id);
        }
    }

    /**
     * add a new Discussion into database when it's created in the system
     * @param id event id
     */
    public void addNewDiscussion(int id){
        dt.insertToDB(id);
    }

    /**
     * add a new party into database when it's created in the system
     * @param id event id
     */
    public void addNewParty(int id){
        pt.insertToDB(id);
    }

    /**
     * add a new talk into database when it's created in the system
     * @param id event id
     */
    public void addNewTalk(int id){
        tt.insertToDB(id);
    }

}
