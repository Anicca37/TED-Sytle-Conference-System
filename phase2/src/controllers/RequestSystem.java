package controllers;

import javafx.util.Pair;
import presenters.RequestPresenter;
import usecases.AttendeeManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The system for support additional users request and connect them to different events.
 */
public class RequestSystem {

    private final AttendeeManager am;
    private final RequestPresenter rp = new RequestPresenter();

    /**
     * the constructor of RequestSystem
     * @param am an attendee manager
     */
    public RequestSystem(AttendeeManager am) {
        this.am = am;
    }

    /**
     * It is for tag the request.
     * @param userId the id of this user.
     */
    public void runRequest(int userId) {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            rp.inputSpecialRequest();
            int input;
            try {
                input = Integer.parseInt(myObj.nextLine());
            } catch (Exception ignored) {
                rp.inputInvalid();
                break;
            }
            if (input == 1) {
                am.request(userId, "Seafood allergy");
                rp.successfulRequest();
            } else if (input == 2) {
                am.request(userId, "Vegetarian");
                rp.successfulRequest();
            } else if (input == 3) {
                am.request(userId, "Peanut allergy");
                rp.successfulRequest();
            } else if (input == 4) {
                am.request(userId, "Accessibility requirements");
                rp.successfulRequest();
            } else if (input == 5) {
                am.request(userId, "Mental health support");
                rp.successfulRequest();
            } else if (input == 0) {
                rp.successfulQuit();
                break;
            } else {
                rp.invalidInput();
            }
        }
    }

    /**
     * This is for addressed request.
     */
    public void runAddressed() {
        Scanner myObj = new Scanner(System.in);
        while (true) {
            rp.orgMenu();
            int input;
            try {
                input = Integer.parseInt(myObj.nextLine());
            } catch (Exception ignored) {
                rp.inputInvalid();
                break;
            }
            if (input == 1) {
                rp.seeAddressedList();
                System.out.println(am.addressedListToString());
            } else if (input == 2) {
                rp.seePendingList();
                Scanner myObj2 = new Scanner(System.in);
                ArrayList<Pair<String, String>> pendingList = am.getPendingList();
                int i = 0;
                while (i < pendingList.size()) {
                    Pair<String, String> request = pendingList.get(i);
                    System.out.println(am.requestToString(am.nameToId(request.getKey()), request.getValue()));
                    rp.changingState();
                    rp.successfulAddress();
                    int input2 = myObj2.nextInt();
                    if (input2 == 0) {
                        break;
                    } else if (input2 == 1) {
                        am.setAddressed(am.nameToId(request.getKey()), request.getValue());
                    } else if (input2 == 2) {
                        i++;
                    } else {
                        rp.invalidInput2();
                    }
                }
            } else if (input == 0) {
                break;
            } else {
                rp.invalidInput();
            }
        }
    }
}
