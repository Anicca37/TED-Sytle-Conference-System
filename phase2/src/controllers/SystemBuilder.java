package controllers;

import controllers.ManageEventSystem.*;
import controllers.MessegeSystem.InboxCheckingSystem;
import controllers.MessegeSystem.MessageSystem;
import controllers.accountManagement.AccountManageSystem;
import controllers.accountManagement.AttendeeRegisterSystem;
import controllers.accountManagement.SpeakerAccountSystem;
import gateway.DBtoManagerUpdater;
import presenters.LoginPresenter;
import presenters.SystemPresenter;
import usecases.*;
import usecases.AttendeeManager;

import java.util.Scanner;

public class SystemBuilder {
    private final AttendeeManager am = new AttendeeManager();
    private final OrganizerManager om = new OrganizerManager();
    private final SpeakerManager sm = new SpeakerManager();
    private final SystemPresenter sp = new SystemPresenter();
    private final MessageManager mm = new MessageManager();
    private final EventManager em = new EventManager();
    private final RoomManager rm = new RoomManager();
    private final AttendeeRegisterSystem ars = new AttendeeRegisterSystem(am, om, sm);
    private final SpeakerAccountSystem srs = new SpeakerAccountSystem(am, om, sm);
    private final FriendSystem fs = new FriendSystem(am, mm);
    private final LoginSystem ls = new LoginSystem(am, om, sm);
    private final SignUpSystem sus = new SignUpSystem(rm, em, am, sm);
    private final ScheduleSystem ss = new ScheduleSystem(rm, em, sm, om);
    private final CancelEventSystem ces = new CancelEventSystem(rm, em, sm, am, om);
    private final ChangeCapacitySystem ccs = new ChangeCapacitySystem(em, rm, sm);
    private final UpDowngradeSystem uds = new UpDowngradeSystem(em, rm, sm);
    private final LoginPresenter lp = new LoginPresenter();
    private final MessageSystem ms = new MessageSystem(mm, am, sm, om, em);
    private final VIPSystem vp = new VIPSystem(am, mm);
    private final DBtoManagerUpdater dbmu = new DBtoManagerUpdater(am, om, sm, mm, em);
    private final AccountManageSystem ams = new AccountManageSystem(am, sm, om);
    private final RequestSystem rs = new RequestSystem(am);

    public boolean loginState;
    public int userRole;
    public int userID;
    public boolean switchToO;
    public boolean switchToA;

    /**
     * The system to start the program and take options
     */
    public SystemBuilder(){
        loginState = false;
        userRole = 0;
        userID = 0;
        switchToA = false;
        switchToO = false;
    }


    /**
     * @param userid the id of this user account.
     * @return the type of this given user account.
     */
    public int getType(int userid) {
        String u = String.valueOf(userid);
        return Integer.parseInt(u.substring(0,1));
    }

    /**
     * Helper method to produce a schedule list from the event list.
     */
    public void setScheduleList() {
        rm.setScheduleList(em.eventListToScheduleList());
    }


    /**
     * @param userid the id of this user enter.
     * @return if or not the id is valid.
     */
    public boolean notValid(int userid){
        boolean flag = true;
        if (am.getAttendeeList().containsKey(userid)){
            flag = false;
        }
        if (sm.getSpeakerList().containsKey(userid)){
            flag = false;
        }
        if (om.getOrganizerList().containsKey(userid)){
            flag = false;
        }
        return flag;
    }


    /**
     * This is the menu for attendee to choose their options.
     */
    private void attendeeMenu() {
        boolean loginA = true;
        setScheduleList();
        while (loginA) {
            boolean is_organizer = false;
            for (int oid : om.getOrganizerList().keySet()) {
                if (om.getAttendeeId(oid) == userID) {
                    is_organizer = true;
                    break;
                }
                /* check if attendee has organizer account and assign switch id.*/
            }

            Scanner aObj = new Scanner(System.in);
            LoginPresenter lp = new LoginPresenter();
            String op = aObj.nextLine();
            lp.loading();
            switch (op) {
                case "A1":  /* option: goes to message menu. call messageSystem to run. */
                    ms.runMessage(userID);
                    lp.attendeeLogin();
                    break;
                case "A2":  /* option: Sign up for an event. call signUpSystem to run. */
                    sus.runSignUp(userID);
                    lp.attendeeLogin();
                    break;
                case "A3":  /* option: Add friends. call FriendSystem to run. */
                    fs.request(userID);
                    lp.attendeeLogin();
                    break;
                case "A4":  /* option: Switch account, recall the login. */
                    if (is_organizer) {
                        switchToO = true;
                        loginA = false;
                    } else {
                        sp.doNotHaveO();
                        lp.attendeeLogin();
                        /*print no account */
                    }
                    break;
                case "A5":  /* option: Cancel an event. */
                    sus.runCancel(userID);
                    lp.attendeeLogin();
                    break;
                case "A6": /* option: Change password. */
                    ams.attendeeChangePassword(userID);
                    lp.attendeeLogin();
                    break;
                case "A7": /* option: to make special request.*/
                    rs.runRequest(userID);
                    lp.attendeeLogin();
                    break;
                case "A8": /* option: view friend requests.*/
                    fs.addFriend(userID);
                    lp.attendeeLogin();
                    break;
                case "A9":/* View account information */
                    ams.attendeeAccountInformation(userID);
                    lp.attendeeLogin();
                    break;
                case "0": /* option: Quit the menu. Stop the while loop. */
                    loginA = false;
                    break;
                default:
                    lp.inputError();
                    break;
            }

        }
    }


    /**
     * This is the menu for organizer to choose their options.
     */
    private void organizerMenu(){
        boolean loginO = true;
        setScheduleList();
        while(loginO){
            Scanner oObj = new Scanner(System.in);
            String op = oObj.nextLine();
            switch (op){
                case "1":  /* option:Goes to messageSystem. call messageSystem to run. */
                    ms.runMessage(userID);
                    lp.organizerLogin();
                    break;
                case "2":  /* option: Schedule an event. ScheduleSystem to run. */
                    ss.run(userID);
                    lp.organizerLogin();
                    break;
                case "3": /* option: Cancel an event. CancelEventSystem to run. */
                    ces.run(userID);
                    lp.organizerLogin();
                    break;
                case "4":  /* option: Create a Speaker Account. call SpeakerRegisterSystem to run. */
                    srs.register();
                    lp.organizerLogin();
                    break;
                case "5": /* option: Create an Attendee Account. call AttendeeRegisterSystem to run. */
                    ars.orgRegister();
                    lp.organizerLogin();
                    break;
                case "6": /* option: Upgrade an Attendee Account. call VIPSystem. */
                    vp.upgrade();
                    lp.organizerLogin();
                    break;
                case "7": /* option: Downgrade an Attendee Account. call VIPSystem.*/
                    vp.downgrade();
                    lp.organizerLogin();
                    break;
                case "8":  /* option: Upgrade / Downgrade an Event. call UpDowngradeSystem to run. */
                    uds.run();
                    lp.organizerLogin();
                    break;
                case "9": /* option: Change the capacity of an event. call ManageOrganizerSystem to run. */
                    ccs.runChangeCapacity();
                    lp.organizerLogin();
                    break;
                case "10":  /* option: Switch account, recall the login. */
                    switchToA = true;
                    loginO =false;
                    break;
                case "11": /* option: Change password. */
                    ams.organizerChangePassword(userID);
                    lp.organizerLogin();
                    break;
                case "12": /* see the special request list */
                    rs.runAddressed();
                    lp.organizerLogin();
                    break;
                case "0": /* option: Quit the menu. Stop the while loop. */
                    loginO = false;
                    break;
                default:
                    lp.inputError();
                    break;
            }
        }


    }

    /**
     * This is the menu for speaker to choose their options.
     */
    private void speakerMenu() {
        boolean loginS = true;
        while (loginS) {
            Scanner sObj = new Scanner(System.in);
            String op = sObj.nextLine();
            switch (op) {
                /* option: goes to messageSystem. call messageSystem to run. */
                case "S1":
                    ms.runMessage(userID);
                    lp.speakerLogin();
                    break;
                case "S2": /* option: Change password. */
                    ams.speakerChangePassword(userID);
                    lp.speakerLogin();
                    break;
                case "0":/* option: Quit the menu. Stop the while loop. */
                    loginS = false;
                    break;
                default:
                    lp.inputError();
                    break;
            }
        }


    }


    /**
     * This is the for running the system.
     */
    public void run() {


        /* to start */
        Scanner myObj = new Scanner(System.in);
        boolean openState = true;
        while(openState){
            sp.loadinglogin();
            dbmu.UpdateAll();
            setScheduleList();
            sp.firstMenu();
            String firstOp = myObj.nextLine();
            switch(firstOp){
                case "1": /* option: Register a attendee account. Call the AttendeeRegisterSystem. */
                    ars.selfRegister();
                    break;
                case "2": /* option: Login. */
                    sp.start();
                    sp.showStart1();
                    String userName = myObj.nextLine();
                    sp.showStart2();
                    String passWord = myObj.nextLine();
                    boolean loginX = true;
                    while (loginX) {
                        userID = ls.login(userName, passWord);
                        if (userID == -1) {
                            userRole = -1;
                        }
                        else if (userID == 0) {
                            userRole = 0;
                        }
                        else {
                            loginState = true;
                            userRole = getType(userID);
                        }

                /* check if userid input is correct */
                        while (userRole == -1) {
                            if (validateUsername(myObj)) break;
                        }

                /* check if user password is correct */
                        while (userRole == 0) {
                            if (validatePassword(myObj)) break;
                        }
                /* login as an attendee and choosing from options */
                        if (loginState && userRole == 1) {
                            this.attendeeMenu();
                            if (switchToO) {
                                userName = userName + "_org";
                                passWord = om.getPassword(om.nameToId(userName));
                                switchToO = false;
                            }
                            else{
                                loginX = false;
                            }

                        }

                /* login as an speaker and choosing from options */
                        if (loginState && userRole == 2) {
                            this.speakerMenu();
                            loginX = false;
                        }

                /* login as an organizer and choosing from options */
                        if (loginState && userRole == 3) {
                            this.organizerMenu();
                            if (switchToA) {
                                userName = userName.substring(0, userName.length()-4);
                                passWord = am.getPassword(am.nameToId(userName));
                                switchToA = false;
                            }
                            else{
                                loginX = false;
                            }
                        }
                    }
                    break;
                case "Esc":
                    openState = false;
                    break;
                default:
                    sp.invalidInput();
                    break;
                }
            }
    }

    /**
     * @param myObj the scanner
     * @return true iff the input password is valid.
     */
    private boolean validatePassword(Scanner myObj) {
        sp.showStart1();
        String newUserName = myObj.nextLine();
        sp.showStart2();
        String newPassWord = myObj.nextLine();
        userID = ls.login(newUserName, newPassWord);
        if (userID == -1) {
            userRole = -1;
        }
        else if (userID == 0) {
            return true;
        }
        else {
            loginState = true;
            userRole = getType(userID);
        }
        return false;
    }


    /**
     * @param myObj the scanner
     * @return true if the input username is a valid name to login
     */
    private boolean validateUsername(Scanner myObj) {
        sp.showStart1();
        String newUserName = myObj.nextLine();
        sp.showStart2();
        String newPassWord = myObj.nextLine();
        userID = ls.login(newUserName, newPassWord);
        if (userID == -1) {
            return true;
        }
        else if (userID == 0) {
            userRole = 0;
        }
        else {
            loginState = true;
            userRole = getType(userID);
        }
        return false;
    }
}
