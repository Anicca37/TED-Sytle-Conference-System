package controllers.MessegeSystem;

import gateway.AmToDbUpdater;
import gateway.MmToDbUpdater;
import presenters.MessagePresenter;
import usecases.*;

/**
 * This system allows attendees to manage message system, including message and reply.
 */
public class AttendeeMessageSystem {
    private final AttendeeManager attendeeManager;
    private final MessageManager messageManager;
    private final AmToDbUpdater atdu;
    private final MmToDbUpdater mtdu;
    private final MessagePresenter messagePresenter;

    public AttendeeMessageSystem(AttendeeManager am, MessageManager mm, AmToDbUpdater atdu,
                                  MmToDbUpdater mtdu, MessagePresenter mp){
        this.attendeeManager = am;
        this.messageManager = mm;
        this.atdu = atdu;
        this.mtdu = mtdu;
        this.messagePresenter = mp;
    }


    /**
     * For a user to message another individual user.
     * @param senderId the id of sender
     * @param content the content of the message
     * @param receiverId the ID of the receiver
     */
    public void message(int senderId, String content, int receiverId){
        if (!attendeeManager.getAttendeeList().containsKey(senderId)){
            messagePresenter.senderDne();
        }
        else if (!attendeeManager.getAttendeeList().containsKey(receiverId)){
            messagePresenter.receiverDne();
        }
        else if (!attendeeManager.getFriendList(senderId).contains(receiverId)){
            messagePresenter.notFriend();
        }
        else if (senderId == receiverId){
            messagePresenter.talkingToYourself();
        }
        else{messageManager.createMessage(content,senderId,receiverId);
            int messageId = messageManager.getMessageList().size();
            mtdu.addNewMessage(messageId);
            attendeeManager.addMessageListSent(senderId, messageId);
            atdu.updateAttendee(senderId);
            attendeeManager.addMessageListReceived(receiverId, messageId);
            atdu.updateAttendee(receiverId);
            messagePresenter.messageSent();}
    }


    /**
     * For a user to reply a message.
     * @param messageId1 the id of message being replied
     * @param content the content of the message
     * @param senderId the ID of the sender
     * @param receiverId the ID of the receiver
     */
    public void reply(int messageId1, String content, int senderId, int receiverId) {
        if (!attendeeManager.getAttendeeList().containsKey(senderId)) {
            messagePresenter.senderDne();
        }
        else if (!attendeeManager.getAttendeeList().containsKey(receiverId)) {
            messagePresenter.receiverDne();
        }
        else if (!attendeeManager.getFriendList(senderId).contains(receiverId)) {
            messagePresenter.notFriend();
        }
        else if (!messageManager.validateMessageId(messageId1)){
            messagePresenter.notValidMessageId();
        }
        else if (senderId == receiverId){
            messagePresenter.talkingToYourself();
        }
        else{messageManager.createMessage(content, senderId, receiverId);
            int messageId = messageManager.getMessageList().size();
            mtdu.addNewMessage(messageId);
            attendeeManager.addMessageListSent(senderId, messageId);
            atdu.updateAttendee(senderId);
            attendeeManager.addMessageListReceived(receiverId, messageId);
            atdu.updateAttendee(receiverId);
            messageManager.addReplyList(messageId1, messageId);
            mtdu.updateMessage(messageId1);
            messagePresenter.messageReplied();}
    }
}
