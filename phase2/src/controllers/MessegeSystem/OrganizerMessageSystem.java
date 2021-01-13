package controllers.MessegeSystem;

import gateway.AmToDbUpdater;
import gateway.MmToDbUpdater;
import gateway.OmToDbUpdater;
import gateway.SmToDbUpdater;
import presenters.MessagePresenter;
import usecases.*;

/**
 * This system allows organizers to announce attendees, speakers and individuals.
 */
public class OrganizerMessageSystem {
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;
    private final EventManager eventManager;
    private final MessageManager messageManager;
    private final AmToDbUpdater atdu;
    private final SmToDbUpdater stdu;
    private final OmToDbUpdater otdu;
    private final MmToDbUpdater mtdu;
    private final MessagePresenter messagePresenter;

    public OrganizerMessageSystem(AttendeeManager am, OrganizerManager om, SpeakerManager sm, EventManager em,
                                  MessageManager mm, AmToDbUpdater atdu, SmToDbUpdater stdu, OmToDbUpdater otdu,
                                  MmToDbUpdater mtdu, MessagePresenter mp){
        this.attendeeManager = am;
        this.organizerManager = om;
        this.speakerManager = sm;
        this.eventManager = em;
        this.messageManager = mm;
        this.atdu = atdu;
        this.stdu = stdu;
        this.otdu = otdu;
        this.mtdu = mtdu;
        this.messagePresenter = mp;
    }

    /**
     * For Organizer to send messages to all attendees in a specific event
     * @param OrganizerId the id of given Organizer
     * @param content the content of the message
     * @param eventId the event ID which the organizer what to message
     */
    public void organizerAnnounceAttendees(int OrganizerId, String content, int eventId) {
        if (!organizerManager.getOrganizerList().containsKey(OrganizerId)) {
            messagePresenter.notOrganizer();
        }
        else if (!organizerManager.getEventHostList(OrganizerId).contains(eventId)){
            messagePresenter.notYourEvent();
        }
        else {
            try{
            for (int attendant: eventManager.getAttendants(eventId)) {
                messageManager.createMessage(content, OrganizerId, attendant);
                int messageId = messageManager.getMessageList().size();
                mtdu.addNewMessage(messageId);
                organizerManager.addMessageList(OrganizerId, messageId);
                otdu.updateOrganizer(OrganizerId);
                attendeeManager.addMessageListReceived(attendant, messageId);
                atdu.updateAttendee(attendant);
            }
            messagePresenter.announcedAttendants();
        } catch (Exception e){
            messagePresenter.partyNoAttendee();
        }

    }}


    /**
     * For Organizer to send messages to all speakers he arranged
     * @param OrganizerId the id of given Organizer
     * @param content the content of the message
     * @param eventId the event ID which the organizer what to message
     */
    public void organizerAnnounceSpeakers(int OrganizerId, String content, int eventId) {
        if (!organizerManager.getOrganizerList().containsKey(OrganizerId)) {
            messagePresenter.notOrganizer();
        } else if (!organizerManager.getEventHostList(OrganizerId).contains(eventId)) {
            messagePresenter.notYourEvent();
        } else {
            try {
                for (Integer speakerId : eventManager.getSpeaker(eventId)) {
                    messageManager.createMessage(content, OrganizerId, speakerId);
                    int messageId = messageManager.getMessageList().size();
                    mtdu.addNewMessage(messageId);
                    organizerManager.addMessageList(OrganizerId, messageId);
                    otdu.updateOrganizer(OrganizerId);
                    speakerManager.addMessageListReceived(speakerId, messageId);
                    stdu.updateSpeaker(speakerId);
                }
                messagePresenter.announcedSpeakers();
            } catch (Exception e) {
                messagePresenter.partyNoSpeaker();
            }
        }
    }


    /**
     * For Organizer to announce a message to a specific individual
     * @param OrganizerId the id of given Organizer
     * @param content the content of the message
     * @param UserId the ID of the individual
     */
    public void organizerAnnounceIndividual(int OrganizerId, String content, int UserId, int eventId){
        if (UserId ==4){
            messagePresenter.invalidUserToAnnounce();
        }
        else if (!organizerManager.getEventHostList(OrganizerId).contains(eventId)) {
        messagePresenter.notYourEvent();}
        else if (attendeeManager.getAttendeeList().containsKey(UserId)){
            messageManager.createMessage(content, OrganizerId, UserId);
            int messageId = messageManager.getMessageList().size();
            mtdu.addNewMessage(messageId);
            organizerManager.addMessageList(OrganizerId, messageId);
            otdu.updateOrganizer(OrganizerId);
            attendeeManager.addMessageListReceived(UserId, messageId);
            atdu.updateAttendee(UserId);
            messagePresenter.announcedIndividual();
        }
        else if (speakerManager.getSpeakerList().containsKey(UserId)){
            messageManager.createMessage(content, OrganizerId, UserId);
            int messageId = messageManager.getMessageList().size();
            mtdu.addNewMessage(messageId);
            organizerManager.addMessageList(OrganizerId, messageId);
            otdu.updateOrganizer(OrganizerId);
            speakerManager.addMessageListReceived(UserId, messageId);
            stdu.updateSpeaker(UserId);
            messagePresenter.announcedIndividual();
        }
        else{ messagePresenter.announcedOrganizer(); }
    }


}
