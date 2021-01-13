package presenters.ManagerEventPresenter;

import usecases.EventManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

/**
 * The presenter used when change the capacity of an event.
 */
public class ChangeCapacityPresenter {

    /**
     * Constructor.
     */
    public ChangeCapacityPresenter(){}

    /**
     * Asks the user to input the id of the event.
     */
    public void inputEventId(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the id of the event that you want to change capacity.");
    }

    /**
     * Asks the user to input the capacity.
     */
    public void inputCapacity(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the capacity that you want to set for this event.\n" +
                "Note: If you want to reduce capacity, " +
                "you can only reduce it to number of people who have already signed up for this event.\n" +
                "Also, event capacity must be in the range of the room capacity and it is a positive integer.");
    }

    /**
     * Print this if the input is invalid.
     */
    public void invalidInput(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Your input is invalid. Please try again.");
    }

    /**
     * Print this if the system successfully change the capacity.
     */
    public void changeSuccess(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully changed the capacity.");
    }

    /**
     * Print this if the system fails to change the capacity.
     */
    public void changeFailure(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have failed to change the capacity.\n" + "Do you want to try again? Please enter 'yes' or 'no'.");
    }

    /**
     * Print this when exiting the change capacity.
     */
    public void exitChangeCapacity(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have exited 'change the capacity' and will back to the main menu of organizer.\n" +
                "----------------------------------------------------------------------");
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
        System.out.println("The system is now processing, please wait......");
    }

    /**
     * Print all rooms.
     */
    public void printAllRooms(){
        System.out.println("-------------This is the description for all rooms:------------- \n" +
                "Room 1 -- maximum capacity: 10 , have tables and rows of chairs (discussions); \n" +
                "Room 2 -- maximum capacity: 10 , can hold presentations (talks); \n" +
                "Room 3 -- maximum capacity: 30 , have tables and rows of chairs (discussions); \n" +
                "Room 4 -- maximum capacity: 30 , can hold presentations (talks); \n" +
                "Room 5 -- maximum capacity: 50 , can hold parties; \n" +
                "Room 6 -- maximum capacity: 50 , can hold parties; \n" +
                "Room 7 -- maximum capacity: 100, have all functionalities and can hold any event. \n");
    }
}
