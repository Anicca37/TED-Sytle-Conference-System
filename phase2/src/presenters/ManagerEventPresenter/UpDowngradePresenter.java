package presenters.ManagerEventPresenter;

import usecases.EventManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

/**
 * The presenter used when upgrade / downgrade an event.
 */
public class UpDowngradePresenter {

    /**
     * Constructor for the presenter.
     */
    public UpDowngradePresenter(){}

    /**
     * Print the scheduleList of all events.
     * @param rm an room manager
     * @param em an event manager
     * @param sm a speaker manager
     */
    public void printScheduleList(RoomManager rm, EventManager em, SpeakerManager sm){
        System.out.println("-------------This is the schedule list of all the events:------------- \n" +
                rm.scheduleListToString(em, sm) +
                "----------------------------------------------------------------------");
    }

    /**
     * Asks rhe user to input the eventId.
     */
    public void inputEventId(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the id of the event that you want to upgrade / downgrade.");
    }

    /**
     * Asks the user to input the option.
     */
    public void inputOption(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter 'upgrade', 'downgrade' or 'exit'.");
    }

    /**
     * Print this if the input is invalid.
     */
    public void invalidInput(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Your input is invalid. Please try again.");
    }

    /**
     * Print this if upgrade successfully.
     */
    public void upgradeSuccess(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully upgraded this event to vip.");
    }

    /**
     * Print this if fail to upgrade.
     */
    public void upgradeFailure(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have failed to upgrade this event to vip. \n" +
                "Do you want to try again? Please enter 'yes' or 'no'.");
    }

    /**
     * Print this if downgrade successfully.
     */
    public void downgradeSuccess(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully downgraded this event to vip.");
    }

    /**
     * Print this if fail to downgrade.
     */
    public void downgradeFailure(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have failed to downgrade this event to vip. \n" +
                "Do you want to try again? Please enter 'yes' or 'no'.");
    }

    /**
     * Print this when exiting the cancel.
     */
    public void exit(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have exited 'upgrade / downgrade an event' and will back to the main menu of organizer.\n" +
                "----------------------------------------------------------------------");
    }

    /**
     * Show this when the system is running.
     */
    public void printWait(){
        System.out.println("The system is now processing, please wait......");
    }
}