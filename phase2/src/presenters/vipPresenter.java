package presenters;

public class vipPresenter {
    public vipPresenter(){}

    public void inputUpgradeUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the username you want to upgrade.");
    }

    public void inputDowngradeUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Please enter the username you want to downgrade.");
    }

    public void invalidUpgradeUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This username is invalid to be upgraded, please try again.");
    }

    public void invalidDowngradeUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This username is invalid to be downgraded, please try again.");
    }

    public void setVipSuccessfully(String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"The user " + username + " is set to VIP successfully");
    }

    public void downgradeSuccessfully(String username){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"The user " + username + " is set to normal attendee successfully");
    }


}
