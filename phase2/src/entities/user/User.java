package entities.user;

import java.util.ArrayList;

/**
 * Stores information about user.
 */
public abstract class User {
    private int userId;
    private String password;
    private String username;
    private ArrayList<Integer> messageListSent;


    /**
     * Constructor for user.
     * @param userId the userid
     */
    //should only be used in user usecase to recreate entities
    public User(int userId){
        this.userId = userId;
    }

    /**
     * Constructor for user.
     * @param userId the userid
     * @param password the password of user
     * @param username the username of user
     */
    public User(int userId, String password, String username){
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.messageListSent = new ArrayList<>();
    }

    /**
     * Getter for the userId.
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for the userId.
     * @param userId the userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for the password of User
     * @return the password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of User
     * @param password the password of User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the username of User
     * @return the username of User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of User
     * @param username the username of User
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the messageListSent.
     * @return the messageListSent of Speaker.
     */
    public ArrayList<Integer> getMessageListSent() {
        return messageListSent;
    }

    /**
     * Setter for the messageListSent.
     * @param messageListSent the messageListSent of Speaker.
     */
    public void setMessageListSent(ArrayList<Integer> messageListSent) {
        this.messageListSent = messageListSent;
    }
}


