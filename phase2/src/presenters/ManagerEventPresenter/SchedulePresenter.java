package presenters.ManagerEventPresenter;

import usecases.EventManager;
import usecases.RoomManager;
import usecases.SpeakerManager;

import java.util.ArrayList;

/**
 * The presenter used when scheduling or cancelling an event.
 */
public class SchedulePresenter {

    /**
     * Constructor for the SchedulePresenter.
     */
    public SchedulePresenter(){}

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
     * Print the suggested rooms.
     * @param suggestedRoom the suggested rooms
     */
    public void printSuggestedRoom(ArrayList<Integer> suggestedRoom){
        System.out.println("-------------This is your suggested rooms:------------- \n" +
                "(not considering conflicts between time and room, time and speakers): \n" +
                "Discussions can only be held in Room 1, 3 or 7. \n" +
                "Talks can only be held in Room 2, 4 or 7. \n" +
                "Parties can only be held in Room 5, 6 or 7. \n" +
                suggestedRoom + "\n" +
                "-------------------------------------------------------");
    }

    /**
     * Print all speakers.
     * @param sm a speaker manager
     */
    public void printAllSpeakers(SpeakerManager sm){
        System.out.println("-------------This is the information of all speakers------------- \n" +
                sm.allSpeakersToString());
    }

    /**
     * Asks the user to input time.
     */
    public void inputTime(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the starting time of the event you want to schedule in the format of 'yyyy-MM-dd HH:mm'.");
    }

    /**
     * Asks the user to input duration.
     */
    public void inputDuration(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the duration of the event you want to schedule. \n" +
                "Note: The duration is in hours. That is, your input must be an integer.");
    }

    /**
     * Asks the user to input speaker names.
     */
    public void inputSpeakerName(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the speaker usernames of the event you want to schedule.\n" +
                "If you want to schedule a party, please enter 'null'.\n" +
                "If you want to schedule a talk, please enter the username of a single speaker.\n" +
                "If you want to schedule a discussion, please enter all the usernames of speakers in separate lines. \n" +
                "Note: If you want to stop input value, please add an empty line after your last input.");
    }

    /**
     * Asks the user to input roomId.
     */
    public void inputRoomId(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the roomId of the event you want to schedule. \n" +
                "Your input must be in the suggested room list. \n");
    }

    /**
     * Asks the user to input title.
     */
    public void inputTitle(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the title of the event you want to schedule.");
    }


    /**
     * Asks the user to input capacity.
     */
    public void inputCapacity(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the capacity of the event you want to schedule. \n" +
                "Note: Your input must be a positive integer.");
    }

    /**
     * Asks the user to input vip status.
     */
    public void inputIsVip(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter 'true' if the event you want to schedule is a vip-only event, otherwise enter 'false'.");
    }

    /**
     * Print this if the system successfully schedule the event.
     */
    public void scheduleSuccess(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully scheduled this event!");
    }


    /**
     * Print this if the system fails to schedule the event.
     */
    public void scheduleFailure(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have failed to schedule this event. \n" +
                "Do you want to try again? Please enter 'yes' or 'no'.");
    }


    /**
     * Print this when exiting the schedule.
     */
    public void exit(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have exited 'schedule' and will back to the main menu of organizer.\n" +
                "----------------------------------------------------------------------");
    }


    /**
     * Print this if the input contains invalid usernames.
     */
    public void invalidUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"At least one of your input username of the speakers is invalid. Please try again.");
    }


    /**
     * Print this if the input is invalid.
     */
    public void invalidInput(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Your input is invalid. Please try again.");
    }


    /**
     * Print this if there is no suggestion.
     */
    public void noSuggestion(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Sorry, there is no suggested room for you now. You will exit the 'schedule'.");
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

    /**
     * Show this when the system is running.
     */
    public void printWait(){
        System.out.println("The system is now scheduling, please wait......");
    }

    /**
     * Print this if time is invalid.
     */
    public void invalidTime(){System.out.println("Your input time is before current time. Please try again.");}
}
