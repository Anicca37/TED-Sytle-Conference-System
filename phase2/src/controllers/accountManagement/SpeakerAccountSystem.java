package controllers.accountManagement;
import gateway.SmToDbUpdater;
import presenters.AccountPresenter;
import usecases.AttendeeManager;
import usecases.OrganizerManager;
import usecases.SpeakerManager;

import java.util.Scanner;


/**
 * The system for organizer to create speaker account.
 * The default password for speaker is "111111"
 */
public class SpeakerAccountSystem {
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final SmToDbUpdater speakerUpdater;

    /**
     * Constructor of SpeakerRegisterSystem
     * @param speakerManager The class that stores all speaker's information
     * attendeeManager attendee manager
     * organizerManager organizer manager
     */
    public SpeakerAccountSystem(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                SpeakerManager speakerManager){
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
        this.speakerUpdater = new SmToDbUpdater(speakerManager);
    }

    /**
     * Create a speaker account
     * @param name the name of the speaker
     */
    public void createSpeaker(String name){
        this.speakerManager.createSpeaker("111111", name);
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
     * The main system to create a speaker account by an organizer.
     */
    public void register(){
            Scanner myObj = new Scanner(System.in);
            AccountPresenter rp = new AccountPresenter();
            String userName;
            while (true){
            rp.inputSpeakerName();
            userName = myObj.nextLine();
            if (this.validateNewName(userName)){
            break;}
            rp.repeatedUsername();
            }
            this.createSpeaker(userName);
            rp.speakerCreateSuccessfully();
            String id = '2' + Integer.toString(speakerManager.getNumSpeaker());
            rp.speakerAccountInfo(id,"111111", userName);
            this.speakerUpdater.addNewSpeaker(Integer.parseInt(id));
    }
}
