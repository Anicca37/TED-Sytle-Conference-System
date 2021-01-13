package presenters;


import usecases.*;
import controllers.MessegeSystem.InboxCheckingSystem;

public class MessagePresenter {
    public MessagePresenter(){}


    public void waitPatiently(){System.out.println("-------------------------------------------" +
            "--------------------------------------"+
            "\n"+"Loading... Please wait patiently, this process will take some time.");}

    /**
     * tell the user he/she is not organizer.
     */
    public void notOrganizer(){System.out.println("-------------------------------------------" +
            "--------------------------------------"+
            "\n"+"you are not an organizer");}

    /**
     * tell the user he/she is not speaker.
     */
    public void notSpeaker(){System.out.println("-------------------------------------------------" +
            "--------------------------------"+
            "\n"+"you are not a speaker");}

    /**
     * tell the user the event is invalid.
     */
    public void notValidEvent(){System.out.println("-------------------------------------------" +
            "--------------------------------------"+
            "\n"+"this event doesn't exist");}

    /**
     * tell the user has no permission in announcing in this room.
     */
    public void notYourEvent(){System.out.println("-----------------------------------------" +
            "----------------------------------------"+
            "\n"+"you don't have permission to announce in this event");}

    /**
     * remind that you sent an announcement to an individual.
     */
    public void announcedIndividual(){System.out.println("---------------------" +
            "------------------------------------------------------------"+
            "\n"+"you have sent an announcement to the user");}

    /**
     * remind to an organizer that he/she sent a message to all speakers.
     */
    public void announcedSpeakers(){System.out.println("------------------------" +
            "---------------------------------------------------------"+
            "\n"+"you have sent an announcement to all speakers");}

    /**
     * remind to an organizer that he/she sent a message to all attendees.
     */
    public void announcedAttendants(){System.out.println("-----------------------------" +
            "----------------------------------------------------"+
            "\n"+"you have sent an announcement to all attendants");}

    /**
     * remind the user that the message has sent.
     */
    public void messageSent(){System.out.println("--------------------------" +
            "-------------------------------------------------------"+
            "\n"+"message sent");}

    /**
     * remind the user that the message has replied.
     */
    public void messageReplied(){System.out.println("------------------------------" +
            "---------------------------------------------------"+
            "\n"+"message replied");}

    /**
     * deny the request to announce an organizer.
     */
    public void announcedOrganizer(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"you can't announce an organizer");
    }

    /**
     * deny the request to send message to a user not in your friend list.
     */
    public void notFriend(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"the person you are trying to message is not your friend");
    }

    /**
     * invalid sender
     */
    public void senderDne(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"sender is not a valid attendee");
    }

    /**
     * invalid receiver.
     */
    public void receiverDne(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"receiver is not a valid attendee");
    }

    /**
     * tell the user that the attendee is not in the event you hosted.
     */
    public void notInEvent(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"the attendee is not in event your hosted");
    }

    /**
     * Invalid message id.
     */
    public void notValidMessageId(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"message id is not valid");
    }

    /**
     * the first level message menu for organizers and speakers.
     */
    public void firstMenu(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Hello, please choose from the following option\n" +
                "1.send announcement to attendees or speakers of your event.\n" +
                "2.check or modify inbox.\n" +
                "3.quit");
    }

    /**
     * print message menu for attendee
     */
    public void attendeeMenu(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"Hello! Please select to message or reply.\n" +
                "1.message your friend.\n" +
                "2.check inbox.\n" +
                "3.quit.");
    }

    /**
     * print message content option for the user
     */
    public void announceattendee(){
        System.out.println(
                "---------------------------------------------------------------------------------"+
                        "\n"+"please enter content you want to announce");
    }

    /**
     * Notify an invalid input.
     */
    public void invalidInput1(){
        System.out.println(
                "---------------------------------------------------------------------------------"+
                "\n"+"This is an invalid input.");
    }


    /**
     * print id event option for the user
     */
    public void organizerAnnounceAttendee(int userId, OrganizerManager om, EventManager em){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter id of event you want to announce"+"\n"+
                "(List of the id and title of events that you host: ");
        for (int eventId: om.getEventHostList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }

    /**
     * print id event option for the user
     */
    public void speakerAnnounceAttendee(int userId, SpeakerManager sm, EventManager em){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter id of event you want to announce"+"\n"+
                "(List of the id and title of events that you will give speech: ");
        for (int eventId: sm.getSpeechList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }

    /**
     * print message content option for the user
     */
    public void announcespeaker(){
        System.out.println(
                "---------------------------------------------------------------------------------"+
                        "\n"+"please enter content you want \n" +
                        "to announce");
    }

    /**
     * print id event option for the user
     */
    public void announcespeaker2(int userId, OrganizerManager om, EventManager em){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter id of event you want to announce"+"\n"+
                "(List of the id and title of events that you host: ");
        for (int eventId: om.getEventHostList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }


    /**
     * print message content, event option for the user
     */
    public void announceindividual(int userId, OrganizerManager om, EventManager em){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter id of event you want to announce"+"\n"+
                "(List of the id and title of events that you host: ");
        for (int eventId: om.getEventHostList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }


    /**
     * print message content, event option for the user
     */
    public void announrceindividual2(){
        System.out.println(
                "---------------------------------------------------------------------------------"+
                        "\n"+"please enter content you want to announce");
    }

    /**
     * print event option for the user
     */
    public void announrceindividual3(int userId, SpeakerManager sm, EventManager em){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter id of event the person is in\n"+
                "(List of the id and title of events that you will give speech: ");
        for (int eventId: sm.getSpeechList(userId)) {
            res.append(eventId).append(". ").append(em.getTitle(eventId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }

    /**
     * print reveiver option for the user
     */
    public void announrceindividual4(int eventId, EventManager em, AttendeeManager am){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter username of user you want to announce\n"+
                "(List of the username of attendees of this event: ");
        for (int attendeeId: em.getAttendants(eventId)) {
            res.append(am.getUsername(attendeeId)).append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }


    /**
     * the second level message menu.
     */
    public void speakerMenu(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"select who you would like to announce.\n"+
                "1.Every Attendee\n"+
                "2.a specific Attendee\n"+
                "3.check message you received\n"+
                "4.quit");
    }
    public void organizerMenu(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"This is the message menu, you can send announcement to attendees or speakers of your event.\n" +
                "Select who would you like to announce.\n"+
                "1.Every attendee\n"+
                "2.Every speaker\n"+
                "3.an individual\n"+
                "4.quit");
    }

    public void talkingToYourself(){
        System.out.println("you are messaging yourself!");
    }

    public void attendeeCheckMessageListReceived(int userid, AttendeeManager am, MessageManager mm,
                                                 OrganizerManager om, SpeakerManager sm){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have received following messages: \n note that as a " +
                "attendee, you are not allowed to send message to organizer and speaker\n");
        for (Integer messageId : am.getMessageListReceived(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println("Message Id: "+ messageId + "\nContent: " + mm.getContent(messageId) + "\nSent from: " +
                    senderName+ "\nAt: " + mm.getTime(messageId) + "\n");
        }
    }
    public void speakerCheckMessageListReceived(int userid, SpeakerManager sm, MessageManager mm,
                                                AttendeeManager am, OrganizerManager om){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have received following messages: \n note that as a " +
                "speaker, you only receive announcement from the organzier, and you don't have the permission to" +
                " reply to their message\n");
        for (Integer messageId : sm.getMessageListReceived(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println("messageid: "+ messageId + "content: " + mm.getContent(messageId) + "sent from: " +
                    senderName+ "at: " + mm.getTime(messageId) + "\n");
            }
        }
    public void invalidUsername(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have inputted an invalid attendee's username to announce, note that you" +
                "can't message a organizer or speaker");
    }
    public void attendeeMessage(int userId, AttendeeManager am){
        StringBuilder res = new StringBuilder();
        res.append("---------------------------------------------------------------------------------"+
                "\n"+"please enter content, and username of the person you want to message in two separate lines"+"\n"+
                "(List of your friends username: ");
        for (int friendId: am.getFriendList(userId)) {
            res.append(am.getUsername(friendId));
            res.append(", ");
        }
        res.delete(res.length()-2, res.length());
        res.append(")");
        System.out.println(res.toString());
    }
    public void invalidUserToAnnounce(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"the individual you are trying to announce is not valid");
    }
    public void speakerCheckMessageListDeleted(int userid, SpeakerManager sm, MessageManager mm,
                                               AttendeeManager am, OrganizerManager om){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have deleted following messages: \n");
        for (Integer messageId : sm.getMessageListdeleted(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println("messageid: "+ messageId + "content: " + mm.getContent(messageId) + "sent from: " +
                    senderName+ "at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void speakerCheckMessageListUnread(int userid, SpeakerManager sm, MessageManager mm,
                                              AttendeeManager am, OrganizerManager om){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have marked as unread the following messages: \n");
        for (Integer messageId : sm.getMessageListunread(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println(" messageid: "+ messageId + " content: " + mm.getContent(messageId) + " sent from: " +
                    senderName+ "at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void speakerCheckMessageListArchived(int userid, SpeakerManager sm, MessageManager mm,
                                                AttendeeManager am, OrganizerManager om){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have Archived following messages:\n");
        for (Integer messageId : sm.getMessageListarchived(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println(" messageid: "+ messageId + " content: " + mm.getContent(messageId) + " sent from: " +
                    senderName+ " at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void attendeeCheckMessageListDeleted(int userid, AttendeeManager am, MessageManager mm,
                                                OrganizerManager om, SpeakerManager sm){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have deleted following messages: \n");
        for (Integer messageId : am.getMessageListdeleted(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println(" messageid: "+ messageId + " content: " + mm.getContent(messageId) + " sent from: " +
                    senderName+ " at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void attendeeCheckMessageListUnread(int userid, AttendeeManager am, MessageManager mm,
                                               OrganizerManager om, SpeakerManager sm){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have marked as unread the following messages: \n");
        for (Integer messageId : am.getMessageListunread(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println("messageid: "+ messageId + " content: " + mm.getContent(messageId) + " sent from: " +
                    senderName+ " at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void attendeeCheckMessageListArchived(int userid, AttendeeManager am, MessageManager mm,
                                                 OrganizerManager om, SpeakerManager sm){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"You have Archived following messages:\n");
        for (Integer messageId : am.getMessageListarchived(userid)){
            int senderId = mm.getSender(messageId);
            int type = Integer.parseInt(Integer.toString(senderId).substring(0, 1));
            String senderName;
            if (type == 1) {
                senderName = am.getUsername(senderId);
            } else if (type == 3) {
                senderName = om.getUsername(senderId);
            } else {
                senderName = sm.getUsername(senderId);
            }
            System.out.println("messageid: "+ messageId + "content: " + mm.getContent(messageId) + "sent from: " +
                    senderName+ "at: " + mm.getTime(messageId) + "\n");
        }
    }
    public void operationDone(){
        System.out.println("you have done the operation successfully");
    }
    public void markUnread(){
        System.out.println("please input the message id to mark as unread");
    }
    public void markArchived(){
        System.out.println("please input the message id to mark as archived");
    }
    public void markDeleted(){
        System.out.println("please input the message id to mark as deleted ");
    }
    public void attendeeReply(){
        System.out.println("Please input the message id you want to reply.");
    }
    public void attendeeReply2() {
        System.out.println("Please input the content you want to reply.");
    }
    public void attendeemenu1(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"hello, please choose to\n" +
                "1.check inbox and modify\n" +
                "2.check unread message\n" +
                "3.check archived message\n" +
                "4.check deleted message\n" +
                "5.quit");
    }
    public void attendeemenu2(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"hello, please choose to\n" +
                "1.mark a message as unread\n" +
                "2.mark a message as archived\n" +
                "3.mark a message as deleted\n" +
                "4.reply to a message\n" +
                "5.quit");
    }
    public void speakermenu2(){
        System.out.println("---------------------------------------------------------------------------------"+
                "\n"+"hello, please choose to\n" +
                "1.mark a message as unread\n" +
                "2.mark a message as archived\n" +
                "3.mark a message as deleted\n" +
                "4.quit");
    }
    public void invalidInput(){
        System.out.println("Your input was invalid.");
    }
    public void invalidMessageId(){
        System.out.println("this message is not in your inbox");
    }

    public void partyNoSpeaker(){
        System.out.println("This is a party, so it does not have a speaker.");
    }
    public void partyNoAttendee(){
        System.out.println("The even has no attendee yet.");
    }
}