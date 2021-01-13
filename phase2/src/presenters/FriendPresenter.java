package presenters;

public class FriendPresenter {
    public FriendPresenter(){
    }

    public void enterFriendName(){
    System.out.println("---------------------------------------------------------------------------------"+
            "\n"+"Please enter requesting friend username, or input 'Quit': ");}

    public void nextStep(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Do you still want to add friend, enter 'Y' if you want, and 'N' if you don't.");
    }

    public void notValidFriendName(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This is not a valid attendee name, please try again.");
    }

    public void alreadySentRequest(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have already sent this request before, please don't send again.");
    }

    public void successSendRequest(String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Your have successfully send the friend request to " + username + " .");}

    public void showRequestName(String requestName){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Do you want to accept the friend request sent by " + requestName +
                " ? Type 'Y' to accept, 'N' to reject, and 'S' to skip");
    }

    public void successAddFriend(String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You are friend with "+ username +" now!");
    }

    public void successfullyDecline(String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have declined the friend request of "+ username +" .");
    }

    public void completeViewing(){
        System.out.println("You have viewed all the friend request!");
    }

    public void waitPatiently(){System.out.println("-------------------------------------------" +
            "--------------------------------------"+
            "\n"+"Loading... Please wait patiently, this process will take some time.");}

}
