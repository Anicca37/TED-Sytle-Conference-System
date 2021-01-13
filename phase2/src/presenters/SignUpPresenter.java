package presenters;

import usecases.AttendeeManager;
import usecases.EventManager;

/**
 * The presenter of sign-up system.
 */
public class SignUpPresenter {

    /**
     * Constructor of SignUpPresenter.
     */
    public SignUpPresenter() {}

    /**
     * Instruction for user to enter input in sign-up system.
     */
    public void inputChoice() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You can choose from following options:\n" +
                "1. View available events.\n2. Sign up for an event.\n" +
                "3. Back to menu.");
    }

    /**
     * Instruction for user to enter input when user want to sign-up for an event.
     */
    public void inputEventId() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the id of the event you want to sign up for. Enter 0 to move back to sign-up menu.");
    }

    public void inputEventIdToCancel(int userId, AttendeeManager am, EventManager em) {
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the id of the event you want to cancel. Enter 0 to move back to attendee menu."
        +"\n"+"(List of the id and title of events that you attend: ");
        for (int eventId: am.getEventList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }

    /**
     * Message presented to the user when this user successfully sign-up for an event.
     */
    public void successfulSignUp() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You've signed up for this event successfully!");
    }

    public void successfulCancel() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You've canceled this event successfully!");
    }

    /**
     * Message presented to the user when this user has already sign-up for an event at this time.
     */
    public void timeNotAvailable() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You've already signed up for an event at this time, please try again!");
    }

    /**
     * Message presented to the user when this user wanted to sign-up for an event that is fulled.
     */
    public void eventFulled() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This event is fulled, please try again!");
    }

    /**
     * Message presented to the user when this user enter an invalid event id.
     */
    public void eventIdInvalid() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This event id is invalid, please enter again.");
    }

    /**
     * Notification when user want to cancel an event that he/she hasn't signed.
     */
    public void eventNotSigned() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You haven't signed up for this event, please enter again.");
    }

    /**
     * Notification when user want to sign up for an event that is cancelled.
     */
    public void eventCancelled() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This event is cancelled, please enter again.");
    }

    /**
     * Notification for system loading.
     */
    public void waitPatiently(){System.out.println("-------------------------------------------" +
            "--------------------------------------"+
            "\n"+"Loading... Please wait patiently, this process will take some time.");
    }

    /**
     * Notification when user type in invalid input.
     */
    public void invalidInput() {
        System.out.println("Your input is invalid.");
    }

    /**
     * Notification when user type in invalid input, and need to type in again.
     */
    public void invalidTryAgain() {
        System.out.println("Your input is invalid, please try again.");
    }
}
