package controllers.accountManagement;

import gateway.AmToDbUpdater;
import presenters.AccountPresenter;
import usecases.AttendeeManager;
import usecases.OrganizerManager;
import usecases.SpeakerManager;

import java.util.Scanner;
/**
 * This system creates an attendee account.
 */
public class AttendeeRegisterSystem {
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final AmToDbUpdater attendeeUpdater;

    /**
     * Constructor of the class
     * @param attendeeManager the Class that stores all attendees information.
     */
    public AttendeeRegisterSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                  SpeakerManager speakerManager){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.attendeeUpdater = new AmToDbUpdater(attendeeManager);
    }

    /**
     * Create an attendee account
     * @param name The name of the attendee
     * @param password The password of the attendee
     */
    public void createAttendee(String name, String password){
        this.attendeeManager.createAttendee(name, password);
    }

    /**
     * Used for register system.
     * @param name the name to be validated
     * @return true iff the username does not exist in speakerManager, AttendeeManager and OrganizerManager
     */
    public boolean validateNewName(String name){
        return (!attendeeManager.isValidUsername(name)) && (!speakerManager.isValidUsername(name) &&
                (!organizerManager.isValidUsername(name)));
    }


    /**
     * The main system of register system
     */
    public void selfRegister(){
        Scanner myObj = new Scanner(System.in);
        AccountPresenter rp = new AccountPresenter();
        rp.inputAttendeeName();
        String userName;
        while (true){
            userName = myObj.nextLine();
            if (this.validateNewName(userName)){
                break;
            }
            rp.repeatedUsername();
        }
        rp.inputPassword();
        String password = myObj.nextLine();
        this.createAttendee(userName, password);
        String id = '1' + Integer.toString(attendeeManager.getNumAttendee());
        attendeeUpdater.addNewAttendee(Integer.parseInt(id));
        rp.attendeeCreateSuccessfully();
        rp.attendeeAccountInfo(id, password, userName);

    }

    public void orgRegister(){
        Scanner myObj = new Scanner(System.in);
        AccountPresenter rp = new AccountPresenter();
        rp.vipOrNot();
        String vipOrNot = myObj.nextLine();
        rp.inputAttendeeName();
        String userName;
        while (true){
            userName = myObj.nextLine();
            if (this.validateNewName(userName)){
                break;
            }
            rp.repeatedUsername();
        }
        rp.inputPassword();
        String password = myObj.nextLine();
        rp.waitPatiently();
        this.createAttendee(userName, password);
        String id = '1' + Integer.toString(attendeeManager.getNumAttendee());
        if (vipOrNot.equals("Y")){
            this.attendeeManager.setVip(Integer.parseInt(id));
        }
        attendeeUpdater.addNewAttendee(Integer.parseInt(id));
        rp.attendeeCreateSuccessfully();
        rp.attendeeAccountInfo(id, password, userName);
    }
}
