package gateway;

import gateway.table.SpeakerTable;
import usecases.SpeakerManager;

public class SmToDbUpdater {
    private final SpeakerTable st;

    public SmToDbUpdater(SpeakerManager sm){
        st = new SpeakerTable(sm);
    }

    /**
     * update an speaker to database when it's changed in the system
     * @param id id of speaker
     */
    public void updateSpeaker(int id){
        st.updateToDB(id);
    }

    /**
     * add a new speaker into database when it's created in the system
     * @param id speaker id
     */
    public void addNewSpeaker(int id){
        st.insertToDB(id);
    }
}
