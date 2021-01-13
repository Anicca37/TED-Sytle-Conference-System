package controllers.accountManagement;

import gateway.*;
import presenters.AccountPresenter;
import usecases.AttendeeManager;
import usecases.OrganizerManager;
import usecases.SpeakerManager;

import java.util.Scanner;

/**
 * This class allows user to manage their system, including changing password.
 */
public class AccountManageSystem {
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final AmToDbUpdater amToDbUpdater;
    private final SmToDbUpdater smToDbUpdater;
    private final OmToDbUpdater omToDbUpdater;


    /**
     * The constructor of AccountManageSystem.
     * @param attendeeManager an attendee manager.
     * @param speakerManager a speaker manager.
     * @param organizerManager an organizer manager.
     */
    public AccountManageSystem(AttendeeManager attendeeManager,
                               SpeakerManager speakerManager,
                               OrganizerManager organizerManager){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.omToDbUpdater = new OmToDbUpdater(organizerManager);
        this.amToDbUpdater = new AmToDbUpdater(attendeeManager);
        this.smToDbUpdater = new SmToDbUpdater(speakerManager);
    }


    /**
     * This method allows attendee to change password
     * @param userId the id of the user
     */
    public void attendeeChangePassword(int userId){
        Scanner myObj = new Scanner(System.in);
        AccountPresenter rp = new AccountPresenter();
        String curpassword;
//        Check if the input password is the same as the previous one.
        while(true) {
            rp.enterCurrentPassword();
            curpassword = myObj.nextLine();
            if (this.attendeeManager.validatePassword(userId, curpassword)) {
                break; }
            rp.incorrectPassword();
        }
//      Break the while loop iff the new password is not the same as the previous one
        String newPassword;
        int orgId = -1;
        while(true){
            rp.enterNewPassword();
            newPassword = myObj.nextLine();
            if(!this.attendeeManager.validatePassword(userId, newPassword)){
                break;}
            rp.repetitivePassword();
        }
        this.attendeeManager.setPassword(userId, newPassword);
//        If this attendee is an account of organizer then also change the password of organizer.
//        String username = this.attendeeManager.getUsername(userId);

        if(this.organizerManager.isValidUsername(this.attendeeManager.getUsername(userId) + "_org")){
            orgId = organizerManager.nameToId(this.attendeeManager.getUsername(userId) + "_org");
            this.organizerManager.setPassword(orgId, newPassword);
        }
        rp.updatePasswordSuccess(newPassword);
        amToDbUpdater.updateAttendee(userId);
        if (orgId != -1){
            omToDbUpdater.updateOrganizer(orgId);}
    }
    /**
     * This method allows speaker to change password
     * @param userId the id of the user
     */
    public void speakerChangePassword(int userId){
        Scanner myObj = new Scanner(System.in);
        AccountPresenter rp = new AccountPresenter();
        String curpassword;
//        Check if the input password is the same as the previous one.
        while(true) {
            rp.enterCurrentPassword();
            curpassword = myObj.nextLine();
            if (this.speakerManager.validatePassword(userId, curpassword)) {
                break; }
            rp.incorrectPassword();
        }
//      Break the while loop iff the new password is not the same as the previous one
        String newPassword;
        while(true){
            rp.enterNewPassword();
            newPassword = myObj.nextLine();
            if(!this.speakerManager.validatePassword(userId, newPassword)){
                break;}
            rp.repetitivePassword();
        }
        this.speakerManager.setPassword(userId, newPassword);
        rp.updatePasswordSuccess(newPassword);
        smToDbUpdater.updateSpeaker(userId);
    }

    /**
     * This method allows speaker to change password
     * @param userId the id of the user
     */
    public void organizerChangePassword(int userId){
        Scanner myObj = new Scanner(System.in);
        AccountPresenter rp = new AccountPresenter();
        String curpassword;
//        Check if the input password is the same as the previous one.
        while(true) {
            rp.enterCurrentPassword();
            curpassword = myObj.nextLine();
            if (this.organizerManager.validatePassword(userId, curpassword)) {
                break; }
            rp.incorrectPassword();
        }
//      Break the while loop iff the new password is not the same as the previous one
        String newPassword;
        int attendeeId = -1;
        while(true){
            rp.enterNewPassword();
            newPassword = myObj.nextLine();
            if(!this.organizerManager.validatePassword(userId, newPassword)){
                break;}
            rp.repetitivePassword();
        }
        this.organizerManager.setPassword(userId, newPassword);
        String username = this.organizerManager.getUsername(userId);
        if(username.length() > 4 &&
                this.attendeeManager.isValidUsername(username.substring(0, username.length() - 4))){
            attendeeId = attendeeManager.nameToId(username.substring(0, username.length() - 4));
            this.attendeeManager.setPassword(attendeeId, newPassword);
        }
        rp.updatePasswordSuccess(newPassword);
        omToDbUpdater.updateOrganizer(userId);
        if (attendeeId != -1){
            amToDbUpdater.updateAttendee(attendeeId);
        }
    }

    /**
     * @param userId the id of this user
     * It will call the presenter to print the information of this attendee account.
     */
    public void attendeeAccountInformation(int userId){
        AccountPresenter rp = new AccountPresenter();
        String information = this.attendeeManager.attendeeInfo(userId);
        rp.accountInfo(information);
    }


}
