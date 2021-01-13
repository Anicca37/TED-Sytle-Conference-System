package controllers;

import gateway.AmToDbUpdater;
import gateway.MmToDbUpdater;
import presenters.FriendPresenter;
import usecases.AttendeeManager;
import usecases.MessageManager;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a friend system. It allows attendee to send friend request and view friend request.
 */
public class FriendSystem {
    private final AttendeeManager attendeeManager;
    private final MessageManager messageManager;
    private final AmToDbUpdater attendeeUpdater;
    private final MmToDbUpdater messageUpdater;

    /**
     * Constructor for the FriendSystem,
     * @param attendeeManager an attendee manager
     * @param messageManager messageManager
     */
    public FriendSystem(AttendeeManager attendeeManager,
                        MessageManager messageManager) {
        this.attendeeManager = attendeeManager;
        this.messageManager = messageManager;
        this.attendeeUpdater = new AmToDbUpdater(attendeeManager);
        this.messageUpdater = new MmToDbUpdater(messageManager);
    }



    /**
     * Notify the both people that they are friend and they can message each other.
     * @param requestId the person who send friend request, he/she should be notified
     * @param userId the person who accept the request.
     */
    public void notifyUser(int requestId, int userId, String userName){
        String message = "You are friends with " + userName +" now! Feel free to chat!";
//        message: content, sender, receiver
        messageManager.createMessage(message,userId,requestId);
        int messageId = messageManager.getMessageList().size();
        this.messageUpdater.addNewMessage(messageId);
        attendeeManager.addMessageListSent(userId, messageId);
        attendeeManager.addMessageListReceived(requestId, messageId);
    }

    /**
     * Add a request to the friend
     * @param friendId the id to add request
     * @param userid the id that sent request
     */
    public void sendRequest(int friendId, int userid){
        this.attendeeManager.addRequestList(friendId, userid);
        this.attendeeUpdater.updateAttendee(friendId);
    }

    /**
     * Validate if a user can send friend request.
     * @param username the username to send request to
     * @param userid the id of user who wants to send the request
     * @return true if and only if
     * 1) The username is not the name of userid
     * 2) The username does not exist in this user's friend list
     * 3) The username is a valid attendee
     * 4) The userid does not exit in that potential friend's request list
     */
    public boolean validateRequest(String username, int userid){
        FriendPresenter friendpresenter = new FriendPresenter();
        if (!this.attendeeManager.isValidUsername(username)){
            friendpresenter.notValidFriendName();
            return false;
        }
        if(username.equals(this.attendeeManager.getUsername(userid))){
            friendpresenter.notValidFriendName();
            return false;
        }
        int friendId = this.attendeeManager.nameToId(username);
        if(this.attendeeManager.getFriendList(userid).contains(friendId)){
            friendpresenter.notValidFriendName();
            return false;
        }
        if(this.attendeeManager.getRequestList(friendId).contains(userid)){
            friendpresenter.alreadySentRequest();
            return false;
        }
        return true;
    }

    /**
     * Request to add a friend
     * A request will only be sent if:
     * 1) The name to request is a valid attendee username
     * 2) This attendee is not yet in the friend list
     * 3) This attendee is not this user.
     * @param userid: the id that send request
     */
    public void request(int userid){
        FriendPresenter friendpresenter = new FriendPresenter();
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String username;
            while (true) {
                friendpresenter.enterFriendName();
                username = myObj.nextLine();
                friendpresenter.waitPatiently();
                if (username.equals("Quit")){
                    break;
                }
                if (this.validateRequest(username, userid)){
                    break;
                }
            }
            if (username.equals("Quit")){
                break;
            }
            int friendId = this.attendeeManager.nameToId(username);
            this.sendRequest(friendId, userid);
            friendpresenter.successSendRequest(username);
            friendpresenter.nextStep();
            String answer = myObj.nextLine();
            if (answer.equals("N")) {
                break;
            }
        }
    }


    /**
     * This method allows user to view friend request and decide whether or not to add friend.
     * The method is called when the user want to view the friend request, and the user must view all requests once
     * they are in this system.
     * @param userid : the id of the user
     */
    public void addFriend(int userid){
        FriendPresenter friendpresenter = new FriendPresenter();
        Scanner myObj = new Scanner(System.in);
        String userName = this.attendeeManager.getUsername(userid);
        ArrayList<Integer> requestList = this.attendeeManager.getRequestList(userid);
        ArrayList<Integer> deleteList = new ArrayList<>();
        for (int requestId : requestList) {
            String requestName = this.attendeeManager.getUsername(requestId);
            friendpresenter.showRequestName(requestName);
            String answer = myObj.nextLine();
            friendpresenter.waitPatiently();
            if (answer.equals("Y")) {
                attendeeManager.attendeeAddFriend(userid, requestId);
                this.notifyUser(requestId, userid, userName);
                deleteList.add(requestId);
                this.attendeeUpdater.updateAttendee(userid);
                this.attendeeUpdater.updateAttendee(requestId);
                friendpresenter.successAddFriend(requestName);
            } else if (answer.equals("N")) {
                deleteList.add(requestId);
                this.attendeeUpdater.updateAttendee(userid);
                friendpresenter.successfullyDecline(requestName);
            }
        }
        for (int deleteId: deleteList){
            this.attendeeManager.deleteRequestList(deleteId, userid);
        }
        friendpresenter.completeViewing();

    }



}
