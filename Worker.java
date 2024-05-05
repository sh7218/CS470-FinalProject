/*
Spring 2024 CSCI-UA.0470
Final Project 
Subi Hwang, Rena Wang, and Linkun Wan
May 5, 2024
*/

// Worker Class
import java.io.Serializable;

public class Worker implements Serializable {
    private static final long serialVersionUID = 1L;
    private int workerID;
    private String password;

    public Worker(int workerID, String password) {
        this.workerID = workerID;
        this.password = password;
    }

    public int getWorkerID() {
        return workerID;
    }

    public String getPassword() {
        return password;
    }

    // Update the status of a package
    public void updatePackageStatus(Package pkg, String newStatus) {
        pkg.setStatus(newStatus);
        System.out.println("Updated Package ID: " + pkg.getId() + " to Status: " + newStatus);
    }
}
