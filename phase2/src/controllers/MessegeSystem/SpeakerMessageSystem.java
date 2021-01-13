package controllers.MessegeSystem;

import gateway.AmToDbUpdater;
import gateway.MmToDbUpdater;
import gateway.OmToDbUpdater;
import gateway.SmToDbUpdater;
import presenters.MessagePresenter;
import usecases.*;

import java.util.Scanner;

/**
 * This system allows speakers to announce attendees and individuals.
 */
public class SpeakerMessageSystem {
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final EventManager eventManager;
    private final MessageManager messageManager;
    private final AmToDbUpdater atdu;
    private final SmToDbUpdater stdu;
    private final MmToDbUpdater mtdu;
    private final MessagePresenter messagePresenter;


    public SpeakerMessageSystem(AttendeeManager am, SpeakerManager sm, EventManager em,
                                MessageManager mm, AmToDbUpdater atdu, SmToDbUpdater stdu,
                                MmToDbUpdater mtdu, MessagePresenter mp){
        this.attendeeManager = am;
        this.speakerManager = sm;
        this.eventManager = em;
        this.messageManager = mm;
        this.atdu = atdu;
        this.stdu = stdu;
        this.mtdu = mtdu;
        this.messagePresenter = mp;

    }
    /**
     * For speaker to send messages to all attendees in the event
     * @param SpeakerId the id of given speaker
     * @param content the content of the message
     * @param eventId the event ID of the speaker in
     */
    public void speakerAnnounceAttendees(int SpeakerId, String content, int eventId) {
        if (!eventManager.validateEventId(eventId)) {
            messagePresenter.notValidEvent();
        }
        else if (eventManager.getSpeaker(eventId).contains(SpeakerId)) {
            try {
                for (int attendant : eventManager.getAttendants(eventId)) {
                    messageManager.createMessage(content, SpeakerId, attendant);
                    int messageId = messageManager.getMessageList().size();
                    mtdu.addNewMessage(messageId);
                    speakerManager.addMessageListSent(SpeakerId, messageId);
                    stdu.updateSpeaker(SpeakerId);
                    attendeeManager.addMessageListReceived(attendant, messageId);
                    atdu.updateAttendee(attendant);
                }
            }catch (Exception e){
                messagePresenter.partyNoAttendee();
            }
            messagePresenter.announcedAttendants();
        } else {
            messagePresenter.notYourEvent();
        }
    }

    public void speakerAnnounceAttendant(int SpeakerId, String content, int attendantId, int eventId) {
        if (!eventManager.validateEventId(eventId)) {
            messagePresenter.notValidEvent();
        }
        else if (!eventManager.getAttendants(eventId).contains(attendantId)){
            messagePresenter.notInEvent();
        }
        else if (attendeeManager.getAttendeeList().containsKey(attendantId)){
            messageManager.createMessage(content, SpeakerId, attendantId);
            int messageId = messageManager.getMessageList().size();
            mtdu.addNewMessage(messageId);
            speakerManager.addMessageListSent(SpeakerId, messageId);
            stdu.updateSpeaker(SpeakerId);
            attendeeManager.addMessageListReceived(attendantId, messageId);
            atdu.updateAttendee(attendantId);
            messagePresenter.announcedIndividual();
        }
        else{
            messagePresenter.invalidUsername(); }
    }


    public boolean SpeakerAnnounceAttendeesOverall(int userid, boolean openState, Scanner myObj) {
        messagePresenter.speakerMenu();
        String secondOp = myObj.nextLine();
        switch (secondOp) {
            case "1":/* option: announce attendees*/
                messagePresenter.speakerAnnounceAttendee(userid, speakerManager, eventManager);
                int eventId2 = 0;
                try{ eventId2 = Integer.parseInt(myObj.nextLine()); }
                catch (Exception e){
                    messagePresenter.invalidInput();
                }
                messagePresenter.announceattendee();
                String content2 = myObj.nextLine();
                speakerAnnounceAttendees(userid,content2,eventId2);
                break;

            case "2":/* option: announce individual*/
                messagePresenter.announrceindividual3(userid, speakerManager, eventManager);
                int eventId1 = 0;
                try{eventId1 = Integer.parseInt(myObj.nextLine()); }
                catch (Exception e){
                    messagePresenter.invalidInput();
                }
                messagePresenter.announrceindividual4(eventId1, eventManager, attendeeManager);
                String username = myObj.nextLine();
                messagePresenter.announrceindividual2();
                String content1 = myObj.nextLine();
                int userId = attendeeManager.nameToId(username);
                if (userId == 4){
                    userId = speakerManager.nameToId(username);
                }
                messagePresenter.waitPatiently();
                speakerAnnounceAttendant(userid,content1,userId,eventId1);
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
}
