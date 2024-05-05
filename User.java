/*
Spring 2024 CSCI-UA.0470
Final Project 
Subi Hwang, Rena Wang, and Linkun Wan
May 5, 2024
*/

// User class
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userID;
    private String password;

    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}
