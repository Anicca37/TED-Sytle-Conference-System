package gateway;

import java.util.ArrayList;

public interface DataAccessOut {//this interface connects the gateway class and database
    // without violating the clean architecture

    public ArrayList<ArrayList<String>> getAttendeeData();

    ArrayList<ArrayList<String>> getOrganizerData();

    ArrayList<ArrayList<String>> getSpeakerData();

    ArrayList<ArrayList<String>> getMessageData();

    ArrayList<ArrayList<String>> getDiscussionData();

    ArrayList<ArrayList<String>> getPartyData();

    ArrayList<ArrayList<String>> getTalkData();
}
