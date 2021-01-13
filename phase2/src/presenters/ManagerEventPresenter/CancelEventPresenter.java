package presenters.ManagerEventPresenter;

import usecases.EventManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

/**
 * The presenter used when cancelling an event.
 */
public class CancelEventPresenter {

    /**
     * Constructor for CancelEventPresenter.
     */
    public CancelEventPresenter(){}

    /**
     * Asks rhe user to input the eventId.
     */
    public void inputEventId(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the id of the event that you want to cancel.");
    }

    /**
     * Print this if the system successfully cancel the event.
     */
    public void cancelSuccess(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully cancelled this event.");
    }

    /**
     * Print this if the system fails to cancel the event.
     */
    public void cancelFailure(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have failed to cancel this event.\n" +
                "Do you want to try again? Please enter 'yes' or 'no'.");
    }

    /**
     * Print this when exiting the cancel.
     */
    public void exit(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have exited 'cancel' and will back to the main menu of organizer.\n" +
                "----------------------------------------------------------------------");
    }

    /**
     * Print this if the input is invalid.
     */
    public void invalidInput(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Your input is invalid. Please try again.");
    }

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
     * Show this when the system is running.
     */
    public void printWait(){
        System.out.println("The system is now cancelling, please wait......");
    }
}