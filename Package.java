/*
Spring 2024 CSCI-UA.0470
Final Project 
Subi Hwang, Rena Wang, and Linkun Wan
May 5, 2024
*/

// Package Class
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Package implements Serializable {
    private static final long serialVersionUID = 1L;
    private static AtomicInteger nextId = new AtomicInteger(1);  // for thread-safe increment

    private int id;
    private int senderId;
    private int receiverId;
    private String status;

    public Package(int senderId, int receiverId) {
        this.id = nextId.getAndIncrement(); 
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = "Label Created";  
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void track() {
        System.out.println("Tracking Package ID: " + id + ", Status: " + status);
    }
}
