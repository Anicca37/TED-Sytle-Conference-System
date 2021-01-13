package controllers.ManageEventSystem;

import gateway.EmToDbUpdater;
import gateway.OmToDbUpdater;
import gateway.SmToDbUpdater;
import javafx.util.Pair;
import presenters.ManagerEventPresenter.SchedulePresenter;
import usecases.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ScheduleSystem, used only by organizer.
 */
public class ScheduleSystem {
    private final RoomManager roomManager;
    private final EventManager eventManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final EmToDbUpdater etdu;
    private final OmToDbUpdater otdu;
    private final SmToDbUpdater stdu;


    /**
     * Constructor for the ScheduleSystem.
     *
     * @param rm a room manager
     * @param em an event manager
     * @param sm a speaker manager
     * @param om an organizer manager
     */
    public ScheduleSystem(RoomManager rm, EventManager em, SpeakerManager sm, OrganizerManager om){
        this.roomManager = rm;
        this.eventManager = em;
        this.speakerManager = sm;
        this.organizerManager = om;
        this.etdu = new EmToDbUpdater(em);
        this.otdu = new OmToDbUpdater(om);
        this.stdu = new SmToDbUpdater(sm);
    }


    /**
     * Validation of event given time and roomId.
     * precondition: input time is between 9am and 5pm
     *
     * @param scheduleList the scheduleList of all events
     * @param time the time needs to be verified
     * @param roomId the room needs to be verified
     * @return true if and only if the room is not occupied at specific time.
     */
    public boolean validateRoom(HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList,
                                 Pair<LocalDateTime, LocalDateTime> time,
                                 int roomId){
        if (scheduleList.containsKey(roomId)) {
            for (Pair<LocalDateTime, LocalDateTime> time1 : scheduleList.get(roomId).keySet()) {
                if (containsTime(time1, time)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Validation of event given time and speakerId.
     * Check if there exists double-booking of a speaker, i.e, schedule a speaker to speak two places at the same time.
     * precondition: input time is between 9am and 5pm
     *
     * @param time the time to be verified.
     * @param speakerId the id of the speaker to be verified.
     * @return true if and only if the speaker is not scheduled for some other event at this time.
     */
    public boolean validateSpeaker(Pair<LocalDateTime, LocalDateTime> time, int speakerId) {
        ArrayList<Integer> speechList = this.speakerManager.getSpeechList(speakerId);
        for (int eventId: speechList){
            Pair<LocalDateTime, LocalDateTime> tempTime = this.eventManager.getTime(eventId);
            if (this.eventManager.getSpeaker(eventId).contains(speakerId) && containsTime(tempTime,time)){
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if two time periods are overlapping.
     * @param time1 time period 1
     * @param time2 time period 2
     * @return true if and only if two time periods are overlapping.
     */
    public boolean containsTime(Pair<LocalDateTime, LocalDateTime> time1, Pair<LocalDateTime, LocalDateTime> time2){
        return ((time1.getKey().compareTo(time2.getKey()) >= 0)
                && (time1.getKey().compareTo(time2.getValue()) < 0)) ||
                ((time1.getValue().compareTo(time2.getKey()) > 0)
                        && (time1.getValue().compareTo(time2.getValue()) <= 0)) ||
                ((time2.getKey().compareTo(time1.getKey()) >= 0)
                        && (time2.getKey().compareTo(time1.getValue()) < 0)) ||
                ((time2.getValue().compareTo(time1.getKey()) > 0)
                        && (time2.getValue().compareTo(time1.getValue()) <= 0));
    }


    /**
     * Return a list of suggested rooms given speaker, capacity and isVip.
     * @param speaker the speakers of the event to be scheduled
     * @param capacity the capacity of the event to be scheduled
     * @return a list of suggested rooms
     */
    public ArrayList<Integer> suggestedRooms(ArrayList<Integer> speaker, int capacity){
        ArrayList<Integer> suggestedRooms = new ArrayList<>();
        if (speaker == null){
            if (capacity <= 50){
                suggestedRooms.add(5);
                suggestedRooms.add(6);
            }
            if (capacity <= 100){
                suggestedRooms.add(7);
            }
        }else{
            if (speaker.size() > 1){
                if (capacity <= 10){
                    suggestedRooms.add(1);
                }
                if (capacity <= 30){
                    suggestedRooms.add(3);
                }
                if (capacity <= 100){
                    suggestedRooms.add(7);
                }
            }else if (speaker.size() == 1){
                if (capacity <= 10){
                    suggestedRooms.add(2);
                }
                if (capacity <= 30){
                    suggestedRooms.add(4);
                }
                if (capacity <= 100){
                    suggestedRooms.add(7);
                }
            }
        }
        return suggestedRooms;
    }


    /**
     * Schedule an event.
     *
     * @param scheduleList  the scheduleList of all events
     * @param userId the userId of the organizer
     * @param time the time that the organizer wants to schedule
     * @param speaker the speakers of the event that to be scheduled
     * @param roomId the room of the event that to be scheduled
     * @param title the title of the event that to be scheduled
     * @param capacity the capacity of the event
     * @param isVip true if and only if this is a vip-only event
     * @return true if and only if the event is scheduled
     */
    public boolean schedule(HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList,
                            int userId,
                            Pair<LocalDateTime, LocalDateTime> time,
                            ArrayList<Integer> speaker,
                            int roomId,
                            String title,
                            int capacity,
                            boolean isVip){
        boolean init = true;

//      check if all the speakers are valid
        if (speaker != null) {
            for (int speakerId : speaker) {
                if (!validateSpeaker(time, speakerId)) {
                    init = false;
                }
            }
        }

//      schedule if all the speakers are valid and room is valid
        if (validateRoom(scheduleList, time, roomId) && init){
//          create the event, and update

//          create a party
            if (speaker == null) {
                eventManager.createParty(time, title, capacity, isVip, roomId);
                etdu.addNewParty(eventManager.getEventList().size());

//          create a talk
            } else if(speaker.size() == 1){
                eventManager.createTalk(speaker.get(0), time, title, capacity, isVip, roomId);
                etdu.addNewTalk(eventManager.getEventList().size());

//          create a discussion
            } else{
                eventManager.createDiscussion(speaker, time, title, capacity, isVip, roomId);
                etdu.addNewDiscussion(eventManager.getEventList().size());
            }

//          eventId is the size of the new eventList

//          update the schedule list
            int eventId = eventManager.getEventList().size();
            roomManager.setEvent(time, roomId, eventId);
            etdu.updateEvent(eventId);

//          update the speech list of all speakers
            if (speaker != null){
                for (int speakerId : speaker){
                    speakerManager.addEvent(speakerId, eventId);
                    stdu.updateSpeaker(speakerId);
                }
            }

//          update the organizer host event list
            organizerManager.addEvent(userId, eventId);
            otdu.updateOrganizer(userId);

            return true;

        }else {
            return false;
        }
    }


    /**
     * This method executes when an organizer wants to schedule an event.
     *
     * @param userId the userId of the organizer
     */
    public void run(int userId){
        boolean init = true;

        while(init){
            SchedulePresenter sp = new SchedulePresenter();
//            LoginPresenter lp = new LoginPresenter();

            Scanner myObj = new Scanner(System.in);

//      get scheduleList
//      Provide a string representation of scheduleList to an organizer.
            HashMap<Integer, HashMap<Pair<LocalDateTime, LocalDateTime>, Integer>> scheduleList = roomManager.getScheduleList();
            sp.printScheduleList(this.roomManager, this.eventManager, this.speakerManager);

//      get room description
            sp.printAllRooms();

//      schedule an event

//      get startTime
            LocalDateTime startTime;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
            while(true){
                sp.inputTime();
                String tempTime = myObj.nextLine();
                try{
                    startTime = LocalDateTime.parse(tempTime, dateTimeFormatter);
                    if (startTime.compareTo(LocalDateTime.now()) >= 0){
                        break;
                    } else{
                        sp.invalidTime();
                    }
                } catch (Exception e){
                    sp.invalidInput();
                }
            }

//      get duration
            int duration;
            while (true){
                sp.inputDuration();
                try{
                    duration = Integer.parseInt(myObj.nextLine());
                    break;
                } catch (Exception e){
                    sp.invalidInput();
                }
            }

//      get time
            Pair<LocalDateTime, LocalDateTime> time = new Pair<>(startTime, startTime.plusHours(duration));

//      print all speakers
            sp.printAllSpeakers(speakerManager);

//      get speakerId
            ArrayList<Integer> speaker = new ArrayList<>();
            while(true){
                sp.inputSpeakerName();
                ArrayList<String> speakerName = new ArrayList<>();

                while (myObj.hasNextLine()) {
                    String readName = myObj.nextLine();
                    speakerName.add(readName);
                    if (readName.isEmpty()){
                        break;
                    }
                }
                speakerName.remove("");

                if (speakerName.get(0).equals("null") && speakerName.size() == 1){
                    speaker = null;
                    break;
                }else {
                    boolean validSpeakerInput = true;
                    for (String username : speakerName) {
                        if (!speakerManager.isValidUsername(username)) {
                            validSpeakerInput = false;
                        }
                    }

                    if (validSpeakerInput){
                        for (String username : speakerName){
                            speaker.add(speakerManager.nameToId(username));
                        }
                        break;

                    }else {
                        sp.invalidUsername();
                    }
                }
            }

//      get title
            sp.inputTitle();
            String title = myObj.nextLine();

//      get capacity
            int capacity;
            while(true){
                sp.inputCapacity();
                try{
                    capacity = Integer.parseInt(myObj.nextLine());
                    if (capacity > 0){
                        break;
                    } else{
                        sp.invalidInput();
                    }
                } catch (Exception e){
                    sp.invalidInput();
                }
            }

//      get isVip
            boolean isVip;
            while(true){
                sp.inputIsVip();
                String tempIsVip = myObj.nextLine();
                if (tempIsVip.equals("true")){
                    isVip = true;
                    break;
                } else if(tempIsVip.equals("false")){
                    isVip = false;
                    break;
                } else{
                    sp.invalidInput();
                }
            }

//      provide suggested rooms
            ArrayList<Integer> suggestedRooms = this.suggestedRooms(speaker, capacity);
            sp.printSuggestedRoom(suggestedRooms);

            if (suggestedRooms.size() == 0) {
                sp.noSuggestion();
                sp.exit();
                init = false;
            }else {

//              get roomId
                int roomId;
                while(true){
                    sp.inputRoomId();
                    try{
                        roomId = Integer.parseInt(myObj.nextLine());

//                    if (suggestedRooms.size() == 0){
//                        sp.noSuggestion();
//                        sp.exit();
//                        init = false;
//                        lp.organizerLogin();
//                        break;
                        if (suggestedRooms.contains(roomId)){
                            break;
                        } else{
                            sp.invalidInput();
                        }
                    } catch (Exception e){
                        sp.invalidInput();
                    }
                }

                sp.printWait();

//      schedule
                if (this.schedule(scheduleList, userId, time, speaker, roomId, title, capacity, isVip)){
                    sp.scheduleSuccess();
                    sp.exit();
                    init = false;
//                lp.organizerLogin();
                }

                else if (!this.schedule(scheduleList, userId, time, speaker, roomId, title, capacity, isVip)){
                    sp.scheduleFailure();
                    String reply = myObj.nextLine();

//              exit
                    if(reply.equals("no")){
                        sp.exit();
                        init = false;
                    }
                }
            }
        }
    }
}
