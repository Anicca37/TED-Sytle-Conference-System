package controllers.ManageEventSystem;

import entities.event.Event;
import gateway.*;
import presenters.ManagerEventPresenter.CancelEventPresenter;
import usecases.AttendeeManager;
import usecases.EventManager;
import usecases.OrganizerManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * CancelEventSystem, used only by an organizer
 */
public class CancelEventSystem {
    private final RoomManager roomManager;
    private final EventManager eventManager;
    private final SpeakerManager speakerManager;
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final EmToDbUpdater etdu;
    private final OmToDbUpdater otdu;
    private final SmToDbUpdater stdu;
    private final AmToDbUpdater atdu;


    /**
     * Constructor for the CancelEventSystem.
     *
     * @param rm a room manager
     * @param em an event manager
     * @param sm a speaker manager
     * @param am an attendee manager
     * @param om an organizer manager
     */
    public CancelEventSystem(RoomManager rm, EventManager em, SpeakerManager sm, AttendeeManager am, OrganizerManager om){
        this.roomManager = rm;
        this.eventManager = em;
        this.speakerManager = sm;
        this.attendeeManager = am;
        this.organizerManager = om;
        this.atdu = new AmToDbUpdater(am);
        this.etdu = new EmToDbUpdater(em);
        this.otdu = new OmToDbUpdater(om);
        this.stdu = new SmToDbUpdater(sm);
    }


    /**
     * Cancel an event.
     *
     * @param userId the id of the organizer
     * @param eventId the id of the event that the organizer wants to cancel
     * @return true if and only if the event is cancelled
     */
    public boolean cancel(int userId, int eventId){
        HashMap<Integer, Event> eventList = eventManager.getEventList();
//      event list does not have this event
        if (!eventList.containsKey(eventId)){
            return false;

//      event list has this event
        }else {
            if (!eventManager.getCancelStatus(eventId)){

//      remove event from eventList and one of talkList, discussionList and partyList
                eventManager.cancelEvent(eventId);
                etdu.updateEvent(eventId);

//      remove this event from scheduleList
                roomManager.removeEvent(eventId);

//      remove this event for all attendees who have signed up
                ArrayList<Integer> attendants = eventManager.getAttendants(eventId);
                if (attendants != null){
                    for (int attendeeId : attendants){
                        attendeeManager.removeEvent(attendeeId, eventId);
                        atdu.updateAttendee(attendeeId);
                    }
                }

//      remove this event for organizer
                organizerManager.removeEvent(userId, eventId);
                otdu.updateOrganizer(userId);

//      remove this event for all speakers
                ArrayList<Integer> speakers = eventManager.getSpeaker(eventId);
                if (speakers != null){
                    for (int speakerId : speakers){
                        speakerManager.removeEvent(speakerId, eventId);
                        stdu.updateSpeaker(speakerId);
                    }
                }

                return true;

            }else {

                return false;
            }
        }
    }


    /**
     * This method executes when an organizer wants to cancel an event.
     * @param userId the id of the organizer
     */
    public void run(int userId){
        while(true){
            CancelEventPresenter cep = new CancelEventPresenter();
//            LoginPresenter lp = new LoginPresenter();

            Scanner myObj = new Scanner(System.in);

//      Provide a string representation of scheduleList to an organizer.

            cep.printScheduleList(this.roomManager, this.eventManager, this.speakerManager);

//          get eventId
            int eventId;
            while(true){
                cep.inputEventId();
                try{
                    eventId = Integer.parseInt(myObj.nextLine());
                    break;
                } catch (Exception e){
                    cep.invalidInput();
                }
            }

            cep.printWait();

//          cancel event
            if(this.cancel(userId, eventId)){
                cep.cancelSuccess();
                cep.exit();
//                lp.organizerLogin();
                break;

            }else if(!this.cancel(userId, eventId)){
                cep.cancelFailure();
                String reply = myObj.nextLine();

//          exit if reply is no
                if(reply.equals("no")){
                    cep.exit();
//                    lp.organizerLogin();
                    break;
                }
            }
        }
    }
}
