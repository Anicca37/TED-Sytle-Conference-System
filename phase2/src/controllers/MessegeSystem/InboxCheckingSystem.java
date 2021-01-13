package controllers.MessegeSystem;

import gateway.AmToDbUpdater;
import gateway.SmToDbUpdater;
import presenters.MessagePresenter;
import usecases.AttendeeManager;
import usecases.OrganizerManager;
import usecases.SpeakerManager;

/**
 * This class allows speaker and attendee to check inbox and mark messages as unread or archived, or they can delete
 * the message.
 */
public class InboxCheckingSystem {
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final AmToDbUpdater atdu;
    private final SmToDbUpdater stdu;
    private final MessagePresenter messagePresenter;
    private final OrganizerManager organizerManager;

    public InboxCheckingSystem(AttendeeManager attendeeManager,
                               SpeakerManager speakerManager, OrganizerManager organizerManager,
                               AmToDbUpdater amToDbUpdater, SmToDbUpdater stdu,
                               MessagePresenter messagePresenter){
        this.attendeeManager = attendeeManager;
        this.speakerManager = speakerManager;
        this.organizerManager = organizerManager;
        this.atdu = amToDbUpdater;
        this.stdu = stdu;
        this.messagePresenter = messagePresenter;
    }

    /**
     * @param messageId the id of message which suer want to mark.
     * @param userid the id of this user.
     */
    public void markUnread(int messageId, int userid) {
        if (Integer.toString(userid).charAt(0) == '1') {
            if (attendeeManager.getMessageListReceived(userid).contains(messageId)){
                attendeeManager.addMessageListUnread(userid, messageId);
                atdu.updateAttendee(userid);
                messagePresenter.operationDone();}
            else{
                messagePresenter.invalidMessageId();
            }
        }
        else {
            if(speakerManager.getMessageListReceived(userid).contains(messageId)){
                speakerManager.addMessageListUnread(userid, messageId);
                stdu.updateSpeaker(userid);
                messagePresenter.operationDone();}
            else{
                messagePresenter.invalidMessageId();
            }
        }
    }

    /**
     * @param messageId the id of message which user want to mark.
     * @param userid the id of this user.
     */
    public void markArchived(int messageId, int userid) {
        if (Integer.toString(userid).charAt(0) == '1') {
            if (attendeeManager.getMessageListReceived(userid).contains(messageId)){
                attendeeManager.addMessageListArchived(userid, messageId);
                atdu.updateAttendee(userid);
                messagePresenter.operationDone();
            }
            else{
                messagePresenter.invalidMessageId();
            }
        }
        else {
            if(speakerManager.getMessageListReceived(userid).contains(messageId)){
                speakerManager.addMessageListArchived(userid,messageId);
                stdu.updateSpeaker(userid);
                messagePresenter.operationDone();
            }
            else{
                messagePresenter.invalidMessageId();
            }
        }
    }

    /**
     * @param messageId the id of message which user want to delete.
     * @param userid the id of this user.
     */
    public void deleteMessage(int messageId, int userid) {
        if (Integer.toString(userid).charAt(0) == '1') {
            if (attendeeManager.getMessageListReceived(userid).contains(messageId)){
                attendeeManager.addMessageListDeleted(userid, messageId);
                attendeeManager.removeMessageListReceived(userid, messageId);
                atdu.updateAttendee(userid);
                messagePresenter.operationDone();}

            else{
                messagePresenter.invalidMessageId();
            }
        }
        else {
            if(speakerManager.getMessageListReceived(userid).contains(messageId)){
                speakerManager.addMessageListDeleted(userid, messageId);
                speakerManager.removeMessageListReceived(userid, messageId);
                stdu.updateSpeaker(userid);
                messagePresenter.operationDone();}
            else{
                messagePresenter.invalidMessageId();
            }
        }
    }
    public String useridToName(int userid){
        if (Integer.toString(userid).charAt(0) == '1') {
            return attendeeManager.getUsername(userid);
        }
        else if (Integer.toString(userid).charAt(0) == '2'){
            return speakerManager.getUsername(userid);
        }
        else{
            return organizerManager.getUsername(userid);
        }
    }


}
