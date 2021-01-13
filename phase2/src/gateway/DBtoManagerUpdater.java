package gateway;

import entities.Message;
import entities.event.Discussion;
import entities.event.Event;
import entities.event.Party;
import entities.event.Talk;
import entities.user.Attendee;
import entities.user.Organizer;
import entities.user.Speaker;
import gateway.table.*;
import usecases.*;
import usecases.AttendeeManager;

import java.util.HashMap;

public class DBtoManagerUpdater {
    private final AttendeeTable at;
    private final OrganizerTable ot;
    private final SpeakerTable st;
    private final DiscussionTable dt;
    private final TalkTable tt;
    private final PartyTable pt;
    private final MessageTable mt;
    private final AttendeeManager am;
    private final OrganizerManager om;
    private final SpeakerManager sm;
    private final MessageManager mm;
    private final EventManager em;


    /**
     * updater from database to Manager in usecase
     * @param am attendee Manager
     * @param om organizer manager
     * @param sm speaker manager
     * @param mm message manager
     * @param em event manager
     */
    public DBtoManagerUpdater(AttendeeManager am,
                              OrganizerManager om,
                              SpeakerManager sm,
                              MessageManager mm,
                              EventManager em){
        TypeConverter tc = new TypeConverter();
        this.am = am;
        this.om = om;
        this.sm = sm;
        this.mm = mm;
        this.em = em;
        at = new AttendeeTable(am);
        ot = new OrganizerTable(om);
        st = new SpeakerTable(sm);
        dt = new DiscussionTable(em);
        tt = new TalkTable(em);
        pt = new PartyTable(em);
        mt = new MessageTable(mm);

    }



    /**
     * update from database to all the managers at once
     */
    public void UpdateAll(){
        UpdateToAm();
        UpdateToOm();
        UpdateToEm();
        UpdateToSm();
        UpdateToMM();
    }

    /**
     * update from database to attendee Manager
     */
    private void UpdateToAm(){
        am.setAttendeeList(new HashMap<Integer, Attendee>());
        at.updateToManager();
    }

    /**
     * update from database to organizer Manager
     */
    private void UpdateToOm(){
        om.setOrganizerList(new HashMap<Integer, Organizer>());
        ot.updateToManager();
    }

    /**
     * update from database to Speaker manager
     */
    private void UpdateToSm(){
        sm.setSpeakerList(new HashMap<Integer, Speaker>());
        st.updateToManager();
    }

    /**
     * update from database to event manager
     */
    private void UpdateToEm(){
        em.setDiscussionList(new HashMap<Integer, Discussion>());
        em.setPartyList(new HashMap<Integer, Party>());
        em.setTalkList(new HashMap<Integer, Talk>());
        em.setEventList(new HashMap<Integer, Event>());
        dt.updateToManager();
        tt.updateToManager();
        pt.updateToManager();
    }

    /**
     * update from database to message manager
     */
    private void UpdateToMM(){
        mm.setMessageList(new HashMap<Integer, Message>());
        mt.updateToManager();
    }
}
