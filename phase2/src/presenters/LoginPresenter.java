package presenters;

/**
 * The presenter for login system.
 */
public class LoginPresenter {
    public LoginPresenter(){}

    /**
     * Print the not valid id information.
     */
    public void nameError(){
        System.out.println("Sorry, your username is not valid.");
    }

    /**
     * Print the not valid password information.
     */
    public void passwordError(){
        System.out.println("Sorry, your password is incorrect.");
    }

    /**
     * Print the attendee successfully logged information and the attendee menu.
     */
    public void attendeeLogin(){
        System.out.print("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully logged in as attendee.Please enter the number of the options.\n" +
                "You can choose from following options:\n" +
                "A1. Goes to Message page.\n" +
                "A2. Sign up for an event.\n" +
                "A3. Send friend request.\n" +
                "A4. Switch to organizer.\n" +
                "A5. Cancel the event that you have signed up for. \n" +
                "A6. Change password.\n" +
                "A7. Make special requests.\n" +
                "A8. View friend requests.\n" +
                "A9. View account information\n" +
                "0.Quit.\n");
    }

    /**
     * Print the organizer successfully logged information and the organizer menu.
     */
    public void organizerLogin(){
        System.out.print("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully logged in as organizer.Please enter the number of the options.\n" +
                "You can choose from following options:\n" +
                "1. Goes to Message page.\n" +
                "2. Schedule an event.\n" +
                "3. Cancel an event.\n"+
                "4. Create a Speaker account.\n" +
                "5. Create an Attendee account.\n" +
                "6. Upgrade an Attendee to vip.\n" +
                "7. Downgrade an Attendee from vip.\n" +
                "8. Upgrade / Downgrade an Event to / from vip. \n" +
                "9. Change the capacity of an event. \n" +
                "10. Switch to attendee.\n" +
                "11. Change password.\n" +
                "12. Check the special request list.\n" +
                        "0.Quit.\n");
    }

    /**
     * Print the speaker successfully logged information and the speaker menu.
     */
    public void speakerLogin(){
        System.out.print("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully logged in as speaker.Please enter the number of the options.\n" +
                "You can choose from following option:\n" +
                "S1. Goes to Message page.\n" +
                "S2. Change password.\n" +
                "0.Quit.\n");
    }

    /**
     * print when changing menu
     */
    public void loading(){
        System.out.println("Loading... Please wait.");
    }

    /**
     * Print the input error information.
     */
    public void inputError(){
        System.out.println("This is an invalid input. please enter again.");
    }



}
