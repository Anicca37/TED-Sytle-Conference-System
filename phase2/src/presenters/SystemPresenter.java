package presenters;


/**
 * The presenter for system.
 */
public class SystemPresenter {
    public SystemPresenter(){}

    /**
     * Print the start announcement.
     */
    public void start() {System.out.print("Welcome!\n"); }


    /**
     * Instructor for user to type their username.
     */
    public void showStart1(){
        System.out.print("USERNAME:");
    }

    /**
     * Instructor for user to type their password.
     */
    public void showStart2() { System.out.print("PASSWORD:");}




    /**
     * Print the first menu.
     */
    public void firstMenu(){
        System.out.print("---------------------------------------------------------------------------------"+
                "\n"+"Hello! Please select to register or login.\n" +
                "1.Register an attendee account.\n" +
                "2.Login.\n" +
                "Esc.\n");
    }


    /**
     * Print the error information for the users who doer not have organizer account.
     */
    public void doNotHaveO(){
        System.out.print("---------------------------------------------------------------------------------"+
                "\n"+"Sorry, you do not have organizer account.");
    }

    public void invalidInput(){System.out.println("---------------------------------------------------------------------------------"+
            "\n"+"Your input is invalid, please try again.");}

    public void loading(){
        System.out.println("Loading... Please wait.");
    }
    public void loadinglogin(){
        System.out.println("Loading login menu... Please wait.");
    }
}
