package controllers.ManageEventSystem;

import entities.event.Event;
import gateway.*;
import presenters.ManagerEventPresenter.UpDowngradePresenter;
import usecases.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * UpDowngradeSystem, which allows organizer to upgrade an event to vip-only event or downgrade an event to normal
 * event.
 */
public class UpDowngradeSystem {
    private final EventManager eventManager;
    private final RoomManager roomManager;
    private final SpeakerManager speakerManager;
    private final EmToDbUpdater etdu;


    /**
     * Constructor for the UpDowngradeSystem.
     * @param em an event manager
     * @param rm a room manager
     * @param sm a speaker manager
     */
    public UpDowngradeSystem(EventManager em, RoomManager rm, SpeakerManager sm){
        this.eventManager = em;
        this.roomManager = rm;
        this.speakerManager = sm;

        this.etdu = new EmToDbUpdater(em);
    }


    /**
     * Checks if the organizer can upgrade this event.
     * @param eventId the id of the event
     * @return true if and only if the organizer successfully upgraded the event
     */
    public boolean upgrade(int eventId){
        HashMap<Integer, Event> eventList = eventManager.getEventList();
        if (!eventList.containsKey(eventId)){
            return false;

//      event list has this event
        }else {
            if (!eventManager.getIsVip(eventId)){
                eventManager.setIsVip(eventId, true);
                etdu.updateEvent(eventId);
                return true;
            }else {
                return false;
            }
        }
    }


    /**
     * Checks if the organizer can downgrade this event.
     * @param eventId the id of the event
     * @return true if and only if the organizer successfully downgraded the event
     */
    public boolean downgrade(int eventId){
        HashMap<Integer, Event> eventList = eventManager.getEventList();
        if (!eventList.containsKey(eventId)){
            return false;

//      event list has this event
        }else {
            if (eventManager.getIsVip(eventId)){
                eventManager.setIsVip(eventId, false);
                etdu.updateEvent(eventId);
                return true;
            }else {
                return false;
            }
        }
    }


    /**
     * This method executes when an organizer wants to upgrade/downgrade an event.
     */
    public void run(){
        while(true){
            UpDowngradePresenter udp = new UpDowngradePresenter();
//            LoginPresenter lp = new LoginPresenter();

            Scanner myObj = new Scanner(System.in);

//      Provide a string representation of scheduleList to an organizer.
            udp.printScheduleList(this.roomManager, this.eventManager, this.speakerManager);

//      get eventId
            int eventId;
            while (true) {
                udp.inputEventId();
                try {
                    eventId = Integer.parseInt(myObj.nextLine());
                    break;
                } catch (Exception e) {
                    udp.invalidInput();
                }
            }

//      get upgrade / downgrade
            String option;
            while(true){
                udp.inputOption();
                option = myObj.nextLine();
                if (option.equals("upgrade") || option.equals("downgrade")){
                    break;
                }else if(option.equals("exit")){
                    udp.exit();
//                    lp.organizerLogin();
                    break;
                }else{
                    udp.invalidInput();
                }
            }

            if (option.equals("upgrade")){
//          upgrade
                udp.printWait();

                if (this.upgrade(eventId)) {
                    udp.upgradeSuccess();
                    udp.exit();
//                    lp.organizerLogin();
                    break;

                } else if (!this.upgrade(eventId)) {
                    udp.upgradeFailure();
                    String reply = myObj.nextLine();

//          exit if reply is no
                    if (reply.equals("no")) {
                        udp.exit();
//                        lp.organizerLogin();
                        break;
                    }
                }

            }else if(option.equals("downgrade")){

//          downgrade
                udp.printWait();

                if (this.downgrade(eventId)) {
                    udp.downgradeSuccess();
                    udp.exit();
//                    lp.organizerLogin();
                    break;

                } else if (!this.downgrade(eventId)) {
                    udp.downgradeFailure();
                    String reply = myObj.nextLine();

//          exit if reply is no
                    if (reply.equals("no")) {
                        udp.exit();
//                        lp.organizerLogin();
                        break;
                    }
                }

//          exit
            } else {
                break;
            }
        }
    }
}
