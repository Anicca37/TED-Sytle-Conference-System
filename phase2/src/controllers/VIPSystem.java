package controllers;

import gateway.AmToDbUpdater;
import gateway.MmToDbUpdater;
import usecases.AttendeeManager;

import java.util.Scanner;
import presenters.vipPresenter;
import usecases.MessageManager;

/**
 * The system for upgrading and downgrading attendee users. Only attendee can be upgraded as a vip.
 */
public class VIPSystem {
    private final AttendeeManager attendeeManager;
    private final MessageManager messageManager;
    private final AmToDbUpdater attendeeUpdater;
    private final MmToDbUpdater messageUpdater;

    public VIPSystem(AttendeeManager attendeeManager, MessageManager messageManager){
        this.messageManager = messageManager;
        this.attendeeManager = attendeeManager;
        this.attendeeUpdater = new AmToDbUpdater(attendeeManager);
        this.messageUpdater = new MmToDbUpdater(messageManager);

    }


    /**
     * Upgrade an attendee to VIP
     */
    public void upgrade(){
        Scanner scanner = new Scanner(System.in);
        vipPresenter vipPresenter = new vipPresenter();
//  Input username and check if it is valid.
        String username;
        while (true){
            vipPresenter.inputUpgradeUsername();
            username = scanner.nextLine();
            if (this.attendeeManager.canUpgrade(username)){
                break;
            }else{vipPresenter.invalidUpgradeUsername();}
        }
//  Upgrade the user
        int userid = this.attendeeManager.nameToId(username);
        attendeeManager.setVip(userid);
        vipPresenter.setVipSuccessfully(username);

//      Notify the user.
//      The id of the sender is the system: 00
        String content = "You have been successfully upgraded as a VIP";
        messageManager.createMessage(content, 00,userid);
        int messageId = messageManager.getMessageList().size();
        attendeeManager.addMessageListReceived(userid, messageId);

//      Upgrade database
        this.attendeeUpdater.updateAttendee(userid);
        this.messageUpdater.addNewMessage(messageId);


    }

    /**
     * Downgrade vip to a normal attendee
     */
    public void downgrade(){
//  Input username and check if it is valid.
        Scanner scanner = new Scanner(System.in);
        vipPresenter vipPresenter = new vipPresenter();
        String username;
        while (true){
            vipPresenter.inputDowngradeUsername();
            username = scanner.nextLine();
            if (this.attendeeManager.canDowngrade(username)){
                break;
            }else{vipPresenter.invalidDowngradeUsername();}
        }
//  Downgrade the user
        int userid = this.attendeeManager.nameToId(username);
        attendeeManager.downgrade(userid);
        vipPresenter.downgradeSuccessfully(username);


//      Notify the user.
//      The id of the sender is the system: 00
        String content = "You have been downgraded to a normal attendee.";
        messageManager.createMessage(content,00,userid);
        int messageId = messageManager.getMessageList().size();
        attendeeManager.addMessageListReceived(userid, messageId);

        //      Upgrade database
        this.attendeeUpdater.updateAttendee(userid);
        this.messageUpdater.addNewMessage(messageId);


    }
}
