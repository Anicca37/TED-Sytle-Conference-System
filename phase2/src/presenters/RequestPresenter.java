package presenters;


public class RequestPresenter {

    /**
     * Constructor for RequestPresenter.
     */
    public RequestPresenter() {}

    /**
     * Instruction on the choosing which special request.
     */
    public void inputSpecialRequest() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You can choose from following options(please type the option number):\n" +
                "1. Sea food allergy.\n" +
                "2. Vegetarian.\n" +
                "3. Peanut allergy.\n" +
                "4. Accessibility requirements.\n" +
                "5. Mental health support.\n" +
                "0. Quit.\n");
    }

    /**
     * Notification that tells user that he/she quit successfully.
     */
    public void successfulQuit(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Quit successfully.");
    }

    /**
     * Notification that the user made a special request.
     */
    public void successfulRequest() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have successfully sent a special request.");
    }

    /**
     * Warning that the user input an invalid information.
     */
    public void invalidInput() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Invalid input, please choose again in this system.");
    }

    /**
     * Warning that the user input an invalid information in state changing page.
     */
    public void invalidInput2() {
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Invalid input, please choose again for the same request.");
    }

    /**
     * submenu to let organizer choose the list he/she wants to check.
     */
    public void orgMenu(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You can now choose from the following options: \n" +
                "1. To see the addressed request list. \n" +
                "2. To see the pending request list. \n" +
                "0. Quit.");
    }

    /**
     * let organizer to change the state in
     */
    public void changingState(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Do you want to address this special request?\n" +
                "1. Yes.\n" + "2. Skip.(goes to the next request)\n" + "0. quit.");
    }

    /**
     * Notification on checking the Addressed list.
     */
    public void seeAddressedList(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This is the Addressed list.");
    }

    /**
     * Notification on check the Pending list.
     */
    public void seePendingList(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This is the Pending list.");
    }

    /**
     * Notification on successfully address an special request.
     */
    public void successfulAddress(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You addressed a special request successfully.");
    }

    /**
     * Notification on invalid input.
     */
    public void inputInvalid() {
        System.out.println("Your input is invalid.");
    }
}
