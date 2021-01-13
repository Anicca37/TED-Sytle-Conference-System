package controllers;

import entities.user.Attendee;
import gateway.*;
import javafx.util.Pair;
import presenters.SignUpPresenter;
import usecases.AttendeeManager;
import usecases.EventManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This class represents a sign-up system.
 */
public class SignUpSystem {

    private final RoomManager roomManager;
    private final EventManager eventManager;
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final SignUpPresenter sup = new SignUpPresenter();
    private final EmToDbUpdater etdu;
    private final AmToDbUpdater atdu;

    /**
     * Constructor of SignUpSystem.
     * @param rm A room manager
     * @param em An event manager
     * @param am An attendee manager
     * @param sm A speaker manager
     */
    public SignUpSystem(RoomManager rm, EventManager em, AttendeeManager am, SpeakerManager sm) {
        this.roomManager = rm;
        this.eventManager = em;
        this.attendeeManager = am;
        this.speakerManager = sm;
        this.atdu = new AmToDbUpdater(am);
        this.etdu = new EmToDbUpdater(em);
    }

    /**
     * This method check if the given event id correspond to a valid event.
     * @param eventId the id of the given event
     * @return whether the event id is valid or not
     */
    public boolean validateEvent(int eventId) {
        return eventManager.validateEventId(eventId);
    }

    /**
     * This method check if the given event id correspond to a cancelled event.
     * @param eventId the id of the given event
     * @return whether the event is cancelled or not
     */
    public boolean cancelledEvent(int eventId) {
        return eventManager.getEventList().get(eventId).getCancelStatus();
    }

    /**
     * This method check if the given attendee has signed up to the given event.
     * @param attendeeId the id of the given attendee
     * @param eventId the id of the given event
     * @return whether the attendee has signed up to the given event or not
     */
    public boolean containEvent(int attendeeId, int eventId) {
        Attendee user = attendeeManager.getAttendeeList().get(attendeeId);
        return user.getEventSignedList().contains(eventId);
    }

    /**
     * This method check if the given event is full.
     * @param eventId the id of the given event
     * @return whether the event can be sign up or not
     */
    public boolean capacityCheck(int eventId) {
        int rmCapacity = roomManager.getRoomCapacity().get(eventManager.getRoomId(eventId));
        ArrayList<Integer> eventAttendeeList = eventManager.getAttendants(eventId);
        return (eventAttendeeList.size() < rmCapacity && eventAttendeeList.size() < eventManager.getCapacity(eventId));
    }

    /**
     * This method check if the given attendee is available to sign up for an event at given time.
     * @param attendeeId the id of the given attendee
     * @param time the given time
     * @return whether the attendee can sign up for an event at this time or not
     */
    public boolean validateAttendee(int attendeeId ,Pair<LocalDateTime, LocalDateTime> time, int eventId) {
        ArrayList<Integer> eventSignedList = attendeeManager.getEventList(attendeeId);
        for (Integer signedEventId: eventSignedList) {
            Pair<LocalDateTime, LocalDateTime> timePair = eventManager.getTime(signedEventId);
            if (((timePair.getKey().compareTo(time.getKey()) >= 0)
                    && (timePair.getKey().compareTo(time.getValue()) < 0)) ||
                    ((timePair.getValue().compareTo(time.getKey()) > 0)
                    && (timePair.getValue().compareTo(time.getValue()) <= 0)) ||
                    ((time.getKey().compareTo(timePair.getKey()) >= 0)
                            && (time.getKey().compareTo(timePair.getValue()) < 0)) ||
                    ((time.getValue().compareTo(timePair.getKey()) > 0)
                            && (time.getValue().compareTo(timePair.getValue()) <= 0)) ||
                    (eventManager.getIsVip(eventId) && !attendeeManager.getStatus(attendeeId))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sign-up an attendee to an event.
     * @param attendeeId the id of given attendee
     * @param eventId the id of given event
     * @return whether the sign-up is successful or not
     */
    public boolean signUp(int attendeeId, int eventId) {
        Pair<LocalDateTime, LocalDateTime> time = eventManager.getTime(eventId);
        if (validateAttendee(attendeeId, time, eventId) && validateEvent(eventId)) {
            eventManager.addAttendee(eventId, attendeeId);
            etdu.updateEvent(eventId);
            attendeeManager.addEvent(attendeeId, eventId);
            atdu.updateAttendee(attendeeId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cancel a signed event of the given attendee.
     * @param attendeeId the id of the given attendee
     * @param eventId the id of the given event
     * @return whether the cancellation is successful or not
     */
    public boolean cancel(int attendeeId, int eventId) {
        Attendee user = attendeeManager.getAttendeeList().get(attendeeId);
        if (user.getEventSignedList().contains(eventId)) {
            attendeeManager.removeEvent(attendeeId, eventId);
            atdu.updateAttendee(attendeeId);
            eventManager.removeAttendee(eventId, attendeeId);
            etdu.updateEvent(eventId);
            return true;
        }
        return false;
    }

    /**
     * This method execute when this system is called by an user.
     * @param userId The id of the user whom called this system
     */
    public void runSignUp(int userId) {
        Scanner myObj = new Scanner(System.in);
        sup.waitPatiently();
        while (true) {
            sup.inputChoice();
            int input;
            try {
                input = Integer.parseInt(myObj.nextLine());
            } catch (Exception ignored) {
                sup.invalidInput();
                break;
            }
            if (input == 1) {
                System.out.println(roomManager.scheduleListToString(eventManager, speakerManager));
            } else if (input == 2) {
                while (true) {
                    sup.inputEventId();
                    int inputEventId;
                    try {
                        inputEventId = Integer.parseInt(myObj.nextLine());
                    } catch (Exception e) {
                        sup.eventIdInvalid();
                        break;
                    }
                    sup.waitPatiently();
                    if (inputEventId == 0) {
                        break;
                    } else if (!validateEvent(inputEventId)) {
                        sup.eventIdInvalid();
                    } else if (cancelledEvent(inputEventId)) {
                        sup.eventCancelled();
                    } else if (!capacityCheck(inputEventId)) {
                        sup.eventFulled();
                    } else if (!validateAttendee(userId, eventManager.getTime(inputEventId), inputEventId)) {
                        sup.timeNotAvailable();
                    } else if (signUp(userId, inputEventId)) {
                        sup.successfulSignUp();
                        break;
                    }
                }
            } else if (input == 3) {
                break;
            } else {
                sup.invalidTryAgain();
            }
        }
    }




    /**
     * This method execute when a user want to cancel a signed event.
     * @param userId the id of the user whom called this action
     */
    public void runCancel(int userId) {
        Scanner myObj = new Scanner(System.in);
        sup.waitPatiently();
        while (true) {
            sup.inputEventIdToCancel(userId, attendeeManager, eventManager);
            int inputEventId;
            try {
                inputEventId = Integer.parseInt(myObj.nextLine());
            } catch (Exception e) {
                sup.invalidInput();
                break;
            }
            if (inputEventId == 0) {
                break;
            } else if (!validateEvent(inputEventId)) {
                sup.eventIdInvalid();
            } else if (cancelledEvent(inputEventId)) {
                sup.eventCancelled();
            } else if (!containEvent(userId, inputEventId)) {
                sup.eventNotSigned();
            } else if (cancel(userId, inputEventId)) {
                sup.successfulCancel();
                break;
            }
        }
    }
}
