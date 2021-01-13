package controllers.MessegeSystem;
import gateway.*;
import presenters.MessagePresenter;
import usecases.*;
import usecases.AttendeeManager;

import java.util.Scanner;

/**
 * This class represents a message system.
 */
public class MessageSystem {
    private final MessageManager messageManager;
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final EventManager eventManager;
    private final MessagePresenter messagePresenter;
    private final AttendeeMessageSystem attendeeMessageSystem;
    private final SpeakerMessageSystem speakerMessageSystem;
    private final OrganizerMessageSystem organizerMessageSystem;
    private final InboxCheckingSystem inboxCheckingSystem;
    /**
     * Constructor of SignUpSystem
     * @param mm A message manager
     * @param am An attendee Manager
     * @param sm An speaker manage
     * @param om A organizer manager
     * @param em A event manager
     */
    public MessageSystem(MessageManager mm, AttendeeManager am,
                         SpeakerManager sm,OrganizerManager om,
                         EventManager em) {
        this.messageManager = mm;
        this.attendeeManager = am;
        this.speakerManager = sm;
        this.organizerManager = om;
        this.eventManager = em;
        AmToDbUpdater atdu = new AmToDbUpdater(am);
        SmToDbUpdater stdu = new SmToDbUpdater(sm);
        OmToDbUpdater otdu = new OmToDbUpdater(om);
        MmToDbUpdater mtdu = new MmToDbUpdater(mm);
        this.messagePresenter = new MessagePresenter();
        this.attendeeMessageSystem = new AttendeeMessageSystem(am, mm,
                atdu, mtdu, messagePresenter);
        this.speakerMessageSystem = new SpeakerMessageSystem(am, sm, em, mm,
                atdu, stdu, mtdu, messagePresenter);
        this.organizerMessageSystem = new OrganizerMessageSystem(am, om, sm, em, mm,
                atdu, stdu, otdu, mtdu, messagePresenter);
        this.inboxCheckingSystem = new InboxCheckingSystem(am, sm, om, atdu, stdu, messagePresenter);
    }



    /**
     * This is the outlayer of the message system.
     * This method execute when this system is called by an user.
     * @param userid the user id of the user
     */
    public void runMessage(int userid){
        boolean openState = true;
        while (openState) {
            if (Integer.toString(userid).charAt(0) == '3'){
                openState = organizerMenu(userid, openState);
            }
            else if (Integer.toString(userid).charAt(0) == '2'){
                openState = speakerMenu(userid, openState);
            }
            else{
                openState = AttendeeMenu(userid, openState);
            }
}}

    /**
     * This is the attendee message menu.
     * @param userid the id of the user
     * @param openState indicator of whether or not to quit the system
     * @return false iff the user want to quit the system
     */
    private boolean AttendeeMenu(int userid, boolean openState) {
        this.messagePresenter.attendeeMenu();
        Scanner myObj = new Scanner(System.in);
        String Op = myObj.nextLine();
        switch (Op){
            case "1": /* option: message*/
                messagePresenter.attendeeMessage(userid, attendeeManager);
                String content = myObj.nextLine();
                String username = myObj.nextLine();
                messagePresenter.waitPatiently();
                int receiverId = attendeeManager.nameToId(username);
                attendeeMessageSystem.message(userid, content, receiverId);
                break;
            case "2":/* option: check inbox*/
                attendeeCheckInbox(userid, myObj);
                messagePresenter.waitPatiently();
                break;
            case "3":
                openState = false;
                break;
            default:
                messagePresenter.invalidInput1();
                break;
        }
        return openState;
    }


    /**
     * This is the speaker message menu.
     * @param userid the id of the user
     * @param openState indicator of whether or not to quit the system
     * @return false iff the user want to quit the system
     */
    private boolean speakerMenu(int userid, boolean openState) {
        Scanner myObj = new Scanner(System.in);
        messagePresenter.firstMenu();
        String firstOp = myObj.nextLine();
        messagePresenter.waitPatiently();
        switch (firstOp) {
            case "1": /* option: announce*/
                openState = speakerMessageSystem.SpeakerAnnounceAttendeesOverall(userid, openState, myObj);
                break;
            case "2":/* option: check inbox*/
                openState = SpeakerCheckInbox(userid, openState, myObj);
                break;
            case "3":/* quit*/
                openState = false;
                break;
            default:
                messagePresenter.invalidInput1();
                break;
        }
        return openState;
    }



    /**
     * This is the organizer message menu.
     * @param userid the id of the user
     * @param openState indicator of whether or not to quit the system
     * @return false iff the user want to quit the system
     */
    private boolean organizerMenu(int userid, boolean openState) {
        Scanner myObj = new Scanner(System.in);
        messagePresenter.organizerMenu();
        String firstOp = myObj.nextLine();
        switch (firstOp) {
            case "1":/* option: announce attendees*/
                messagePresenter.organizerAnnounceAttendee(userid, organizerManager, eventManager);
                int eventId;
                try {
                    eventId = Integer.parseInt(myObj.nextLine());
                }
                catch (Exception e){
                    messagePresenter.invalidInput();
                    break;
                }
                messagePresenter.announceattendee();
                String content = myObj.nextLine();
                messagePresenter.waitPatiently();
                organizerMessageSystem.organizerAnnounceAttendees(userid,content,eventId);
                break;
            case "2":/* option: announce speakers*/
                messagePresenter.announcespeaker2(userid, organizerManager, eventManager);
                int eventId1;
                try{eventId1 = Integer.parseInt(myObj.nextLine()); }
                catch (Exception e){
                    messagePresenter.invalidInput();
                    break;
                }
                messagePresenter.announcespeaker();
                String content1 = myObj.nextLine();
                messagePresenter.waitPatiently();
                organizerMessageSystem.organizerAnnounceSpeakers(userid,content1,eventId1);
                break;
            case "3":/* option: announce individual*/
                messagePresenter.announceindividual(userid, organizerManager, eventManager);
                int eventId2;
                try{ eventId2 = myObj.nextInt(); }
                catch (Exception e){
                    messagePresenter.invalidInput();
                    break;
                }
                messagePresenter.announrceindividual4(eventId2, eventManager, attendeeManager);
                String username = myObj.nextLine();
                messagePresenter.announceattendee();
                String content2 = myObj.nextLine();
                messagePresenter.waitPatiently();
                int attendeeId = attendeeManager.nameToId(username);
                organizerMessageSystem.organizerAnnounceIndividual(userid, content2, attendeeId, eventId2);
                break;
            case "4":
                openState = false;
                break;
            default:
                messagePresenter.invalidInput1();
                break;
        }
        return openState;
    }

    /**
     * Allows speaker to check ibox
     * @param userid the id of the speaker
     * @param openState the state now
     * @param myObj the scanner
     * @return false if the user wants to quit the system.
     */
    private boolean SpeakerCheckInbox(int userid, boolean openState, Scanner myObj) {
        messagePresenter.attendeemenu1();
        String thirdOp = myObj.nextLine();
        switch (thirdOp) {
            case "1":/* option: choose to modify*/
                messagePresenter.speakerCheckMessageListReceived(userid, speakerManager, messageManager,
                        attendeeManager, organizerManager);
                messagePresenter.speakermenu2();
                String forthOp = myObj.nextLine();
                switch (forthOp) {
                    case "1": /* option: mark unread*/
                        messagePresenter.markUnread();
                        int messageId = 0;
                        try {
                            messageId = myObj.nextInt();
                        } catch (Exception e) {
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.markUnread(messageId, userid);
                        break;
                    case "2": /* option: mark archived*/
                        messagePresenter.markArchived();
                        int messageId1 = 0;
                        try {
                            messageId1 = myObj.nextInt();
                        } catch (Exception e) {
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.markArchived(messageId1, userid);
                        break;
                    case "3": /* option: mark deleted*/
                        messagePresenter.markDeleted();
                        int messageId2 = 0;
                        try {
                            messageId2 = myObj.nextInt();
                        } catch (Exception e) {
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.deleteMessage(messageId2, userid);
                        break;
                    case "4": /* quit*/
                        openState = false;
                        break;
                    default:
                        messagePresenter.invalidInput1();
                        break;

                }
                break;
            case "2": /* option: check unread*/
                messagePresenter.speakerCheckMessageListUnread(userid, speakerManager, messageManager,
                        attendeeManager, organizerManager);
                break;
            case "3":  /* option: check archived*/
                messagePresenter.speakerCheckMessageListArchived(userid, speakerManager, messageManager,
                        attendeeManager, organizerManager);
                break;
            case "4":  /* option: check deleted*/
                messagePresenter.speakerCheckMessageListDeleted(userid, speakerManager, messageManager,
                        attendeeManager, organizerManager);
                break;
            case "5":    /* quit*/
                openState = false;
                break;
            default:
                messagePresenter.invalidInput1();
                break;
        }
        return openState;

    }
    /**
     * Allows attendee to check ibox
     * @param userid the id of the speaker
     * @param myObj the scanner
     */
    private void attendeeCheckInbox(int userid, Scanner myObj) {
        messagePresenter.attendeemenu1();
        String thirdOp = myObj.nextLine();
        switch(thirdOp){
            case "1":/* option: choose to modify*/
                messagePresenter.attendeeCheckMessageListReceived(userid, attendeeManager, messageManager,
                        organizerManager, speakerManager);
                messagePresenter.attendeemenu2();
                String forthOp= myObj.nextLine();
                switch (forthOp){
                    case "1": /* option: mark unread*/
                        messagePresenter.markUnread();
                        int messageId = 0;
                        try{messageId = Integer.parseInt(myObj.nextLine()); }
                        catch (Exception e){
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.markUnread(messageId, userid);
                        break;
                    case "2": /* option: mark archived*/
                        messagePresenter.markArchived();
                        int messageId1 = 0;
                        try{messageId1 = Integer.parseInt(myObj.nextLine()); }
                        catch (Exception e){
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.markArchived(messageId1, userid);
                        break;
                    case "3": /* option: mark deleted*/
                        messagePresenter.markDeleted();
                        int messageId2 = 0;
                        try{messageId2 = Integer.parseInt(myObj.nextLine()); }
                        catch (Exception e){
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.waitPatiently();
                        inboxCheckingSystem.deleteMessage(messageId2, userid);
                        break;
                    case "4": /* option: reply to a message*/
                        messagePresenter.attendeeReply();
                        int messageId3 = 0;
                        try{messageId3 = Integer.parseInt(myObj.nextLine()); }
                        catch (Exception e){
                            messagePresenter.invalidInput();
                        }
                        messagePresenter.attendeeReply2();
                        String content1 = myObj.nextLine();
                        int receiverId1 = messageManager.getSender(messageId3);
                        messagePresenter.waitPatiently();
                        attendeeMessageSystem.reply(messageId3,content1, userid, receiverId1);
                        break;
                    case "5": /* quit*/
                        break;
                    default:
                        messagePresenter.invalidInput1();
                        break;

                }
                break;
            case "2": /* option: check unread*/
                messagePresenter.attendeeCheckMessageListUnread(userid, attendeeManager, messageManager,
                        organizerManager, speakerManager);
                break;
            case "3":  /* option: check archived*/
                messagePresenter.attendeeCheckMessageListArchived(userid, attendeeManager, messageManager,
                        organizerManager, speakerManager);
                break;
            case "4":  /* option: check deleted*/
                messagePresenter.attendeeCheckMessageListDeleted(userid, attendeeManager, messageManager,
                        organizerManager, speakerManager);
                break;
            default:
                messagePresenter.invalidInput1();
                break;
        }
    }
}
