//package controllers;
//import usecases.*;
//import usecases.AttendeeManager;

//public class LoginSystem {
//    private final AttendeeManager am;
//    private final OrganizerManager om;
//    private final SpeakerManager sm;
//    private boolean loginX = true;

//    public LoginSystem(AttendeeManager am, OrganizerManager om, SpeakerManager sm){
//        this.am = am;
//        this.om = om;
//        this.sm = sm;
//    }

//    /**
//     * @param username the name of this user enter.
//     * @param password the password this user enter.
//     * To log the user in.
//     */

    /*
    * UserID convention:
    * userID = 1xxxxxx -> attendee
    * userID = 2xxxxxx -> speaker
    * userID = 3xxxxxx -> organizer
    * userID = 4 -> invalid username (name error)
    * userID = 5 -> invalid password (password error)
     */
//    public int login(String username, String password) {
//        int userID;
//        if (am.isValidUsername(username)){
//            if (am.attendeeLogin(username, password)){ userID = am.nameToId(username); }
//            else{ userID = 5; }
//        }
//        else if (sm.isValidUsername(username)) {
//            if (sm.attendeeLogin(username, password)){ userID = sm.nameToId(username); }
//            else{ userID = 5; }
//        }
//        else if (om.isValidUsername(username)) {
//            if (om.attendeeLogin(username, password)){ userID = om.nameToId(username); }
//            else{ userID = 5; }
//        }
//        else { userID = 4; }
//        return userID;
//    }



//}
package controllers;
import presenters.LoginPresenter;
import usecases.*;

/**
 * The system for identify account type and check the whether input username and password are valid.
 */
public class LoginSystem {
    private final LoginPresenter lp = new LoginPresenter();
    private final AttendeeManager am;
    private final OrganizerManager om;
    private final SpeakerManager sm;

    /**
     * Constructor for loginSystem.
     * @param am an attendee manager
     * @param om an organizer manager
     * @param sm an speaker manager
     */
    public LoginSystem(AttendeeManager am, OrganizerManager om, SpeakerManager sm){
        this.am = am;
        this.om = om;
        this.sm = sm;
    }

    /**
     * @param username the name of this user enter.
     * @param password the password this user enter.
     * To log the user in.
     */
    public int login(String username, String password) {
        int userID = 0;
        if (am.isValidUsername(username)){
            if (am.attendeeLogin(username, password)){
                lp.attendeeLogin();
                userID = am.nameToId(username);
            }
            else{
                lp.passwordError();
            }
        }
        else if (sm.isValidUsername(username)) {
            if (sm.attendeeLogin(username, password)){
                lp.speakerLogin();
                userID = sm.nameToId(username);
            }
            else{
                lp.passwordError();
            }
        }
        else if (om.isValidUsername(username)) {
            if (om.attendeeLogin(username, password)){
                lp.organizerLogin();
                userID = om.nameToId(username);
            }
            else{
                lp.passwordError();
            }
        }
        else {
            lp.nameError();
            userID = -1;
        }
        return userID;
    }

}
