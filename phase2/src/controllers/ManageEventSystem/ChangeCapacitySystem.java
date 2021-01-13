package controllers.ManageEventSystem;

import entities.event.Event;
import gateway.EmToDbUpdater;
import presenters.ManagerEventPresenter.ChangeCapacityPresenter;
import usecases.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The System for changing the capacity of an event and upgrading an Attendee.
 */
public class ChangeCapacitySystem {
    private final EventManager eventManager;
    private final RoomManager roomManager;
    private final SpeakerManager speakerManager;
    private final EmToDbUpdater etdu;


    /**
     * Constructor for ManagerOrganizerSystem.
     * @param em an event manager
     * @param rm a room manager
     */
    public ChangeCapacitySystem(EventManager em, RoomManager rm, SpeakerManager sm){
        this.eventManager = em;
        this.roomManager = rm;
        this.speakerManager = sm;
        this.etdu = new EmToDbUpdater(em);
    }


    /**
     * Validation for the capacity.
     * @param roomId the id of the room
     * @param capacity the capacity to set
     * @return true if and only if the new capacity <= room capacity.
     */
    public boolean validateRoomCapacity(int roomId, int capacity){
        if (roomId == 1 || roomId == 2){
            return capacity <= 10;
        }else if(roomId == 3 || roomId == 4){
            return capacity <= 30;
        }else if (roomId == 5 || roomId == 6){
            return capacity <= 50;
        }else if(roomId == 7){
            return capacity <= 100;
        }else {
            return false;
        }
    }


    /**
     * Change the capacity of the event.
     * @param eventId the id of the event
     * @param capacity the capacity of the event
     * @return true if and only if the system successfully change the capacity
     */
    public boolean changeCapacity(int eventId, int capacity){
        HashMap<Integer, Event> eventList = eventManager.getEventList();
        if (!eventList.containsKey(eventId)){
            return false;

//      event list has this event
        }else {
            int roomId = roomManager.getRoom(eventId);
            if (eventManager.canSetCapacity(eventId, capacity) && validateRoomCapacity(roomId, capacity)){
                eventManager.setCapacity(eventId, capacity);
                etdu.updateEvent(eventId);
                return true;
            }else {
                return false;
            }
        }
    }


    /**
     * This method executes when an organizer wants to change the capacity of an event.
     */
    public void runChangeCapacity(){
        while(true){
            ChangeCapacityPresenter ccp = new ChangeCapacityPresenter();
//            LoginPresenter lp = new LoginPresenter();

            Scanner myObj = new Scanner(System.in);

//      Provide a string representation of scheduleList to an organizer.
            ccp.printScheduleList(this.roomManager, this.eventManager, this.speakerManager);

            ccp.printAllRooms();

//      get eventId
            int eventId;
            while(true){
                ccp.inputEventId();
                try{
                    eventId = Integer.parseInt(myObj.nextLine());
                    break;
                } catch (Exception e){
                    ccp.invalidInput();
                }
            }

//      get capacity
            int capacity;
            while(true){
                ccp.inputCapacity();
                try{
                    capacity = Integer.parseInt(myObj.nextLine());
                    if (capacity > 0){
                        break;
                    } else{
                        ccp.invalidInput();
                    }
                } catch (Exception e){
                    ccp.invalidInput();
                }
            }

            ccp.printWait();

//      change capacity
            if(this.changeCapacity(eventId, capacity)){
                ccp.changeSuccess();
                ccp.exitChangeCapacity();
//                lp.organizerLogin();
                break;

            }else if(!this.changeCapacity(eventId, capacity)){
                ccp.changeFailure();
                String reply = myObj.nextLine();

//          exit if reply is no
                if(reply.equals("no")){
                    ccp.exitChangeCapacity();
//                    lp.organizerLogin();
                    break;
                }
            }
        }
    }
}
