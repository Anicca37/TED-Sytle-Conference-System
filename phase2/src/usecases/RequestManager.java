package usecases;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is a separate class that is intended for managing special request functionality for Attendee.
 */
public class RequestManager {
    public AttendeeManager attendeeManager;
    public ArrayList<Pair<String, String>> pendingList;
    public ArrayList<Pair<String, String>> addressedList;

    /**
     * Constructor
     * @param attendeeManager the attendee manager
     */
    public RequestManager(AttendeeManager attendeeManager) {
        this.attendeeManager = attendeeManager;
        this.pendingList = new ArrayList<>();
        this.addressedList = new ArrayList<>();
    }

    /**
     * Getter for the addressedList.
     * @return the list of addressed requests
     */
    public ArrayList<Pair<String, String>> getAddressedList() {
        return addressedList;
    }

    /**
     * Getter for the pendingList.
     * @return the list of pending requests
     */
    public ArrayList<Pair<String, String>> getPendingList() {
        return pendingList;
    }

    /**
     * Setter for the pendingList.
     * @param pendingList the list of pending requests
     */
    public void setPendingList(ArrayList<Pair<String, String>> pendingList) {
        this.pendingList = pendingList;
    }

    /**
     * Setter for the pendingList.
     * @param addressedList the list of addressed requests
     */
    public void setAddressedList(ArrayList<Pair<String, String>> addressedList) {
        this.addressedList = addressedList;
    }

    /**
     * Send a special request.
     * @param userId the id of the given attendee
     * @param request the name of the request
     */
    public void setRequest(int userId, String request) {
        HashMap<String, String> srList = attendeeManager.getSRequestList(userId);
        srList.replace(request, "Pending");
        String username = attendeeManager.getUsername(userId);
        ArrayList<Pair<String, String>> pendingList = getPendingList();
        pendingList.add(new Pair<>(username, request));
        setPendingList(pendingList);
        attendeeManager.setSRequestList(userId, srList);
    }

    /**
     * Tag a request as addressed.
     * @param userId the id of the given attendee
     * @param request the name of the request
     */
    public void setAddressed(int userId, String request) {
        HashMap<String, String> srList = attendeeManager.getSRequestList(userId);
        srList.replace(request, "Addressed");
        String username = attendeeManager.getUsername(userId);
        ArrayList<Pair<String, String>> pendingList = getPendingList();
        ArrayList<Pair<String, String>> addressedList = getAddressedList();
        pendingList.remove(new Pair<>(username, request));
        setPendingList(pendingList);
        addressedList.add(new Pair<>(username, request));
        setAddressedList(addressedList);
        attendeeManager.setSRequestList(userId, srList);
    }

    /**
     * ToString method of a single request.
     * @param username the username of the user whom sent this request
     * @param request the name of the request
     * @return the string representation of the request
     */
    public String requestToString(String username, String request) {
        return username + " has requested for " + request + ".\n";
    }

    /**
     * ToString method for addressedList.
     * @return the string representation of addressedList
     */
    public String addressedListToString() {
        StringBuilder res = new StringBuilder();
        for (Pair<String, String> request :addressedList) {
            res.append(requestToString(request.getKey(), request.getValue()));
        }
        return res.toString();
    }
}
