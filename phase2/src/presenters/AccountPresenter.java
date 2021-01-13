package presenters;

/**
 * Presenter for register system
 */
public class AccountPresenter {
    public AccountPresenter(){}

    /**
     * Instructions to enter the speaker's username.
     */
    public void inputSpeakerName(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the speaker's username.");
    }

    /**
     * Instructions to enter the attendee's username.
     */
    public void inputAttendeeName(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the username.");
    }

    /**
     * Instructions to enter the organizer's username.
     */
    public void inputOrganizerName(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the username.");
    }

    public void vipOrNot(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Do you want to create a vip attendee? Enter 'Y' or 'N'");
    }

    public void repeatedUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This username already exists, please enter a different username.");
    }

    /**
     * Instructions to enter the password.
     */
    public void inputPassword(){System.out.println("---------------------------------------------------------------------------------"+
            "\n"+"Please enter the password.");}

    /**
     * Information that the speaker account is successfully created.
     */
    public void speakerCreateSuccessfully(){System.out.println("---------------------------------------" +
            "------------------------------------------"+
            "\n"+"A speaker account has been created successfully.");}

    /**
     * Information that the attendee account is successfully created.
     */
    public void attendeeCreateSuccessfully(){System.out.println("---------------------------------------------------------------------------------"+
            "\n"+"An attendee account has been created successfully.");}

    /**
     * Show registered speaker account information to the user
     * @param userid the id of the speaker
     * @param password the password of the speaker
     * @param username the username of the speaker
     */
    public void speakerAccountInfo(String userid, String password, String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Username: " + username + "\n"+
                "userid: " + userid + "\n" +
                "password: " + password + "\n" +
                        "This speaker can now log in to the login system");
    }

    public void attendeeAccountInfo(String userid, String password, String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Username: " + username + "\n"+
                        "userid: " + userid + "\n" +
                        "password: " + password + "\n" +
                "This attendee can now log in to the login system");

    }

    public void enterCurrentPassword(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter your current password.");
    }

    public void enterNewPassword(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the new password you want to set.");
    }

    public void updatePasswordSuccess(String password){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully change the password! The new password is: " + password +"." +
                "\n" +" Loading back to main menu, please wait...");
    }

    public void repetitivePassword(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Sorry, your password is the same as the current one, please enter a different password.");
    }

    public void incorrectPassword(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"The password you enter is wrong. Please try again.");
    }

    public void accountInfo(String info){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+info);
    }

    public void waitPatiently(){
        System.out.println("Loading... Please wait patiently.");
    }
}
