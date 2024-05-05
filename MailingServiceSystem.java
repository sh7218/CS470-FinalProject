/*
Spring 2024 CSCI-UA.0470
Final Project 
Subi Hwang, Rena Wang, and Linkun Wan
May 5, 2024
*/

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

// Mailing Service System Class (the system)
@SuppressWarnings("unchecked") // to handle with the unchecked warning
public class MailingServiceSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<User> userList = new ArrayList<>();
    private static ArrayList<Package> pkg_list = new ArrayList<>();
    private static ArrayList<Worker> workerList = new ArrayList<>();

    // Registration for a new user
    private static void registerUser() {
        System.out.print("Enter userID (numeric only): ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // userID validation
        boolean exists = false;
        for (User user : userList) {
            if (user.getUserID()== userID) {
                exists = true;
                break;
            }
    }

    if (exists) {
        System.out.println("UserID already exists. Please try creating a different userID.");
    } else {
        User newUser = new User(userID, password);
        userList.add(newUser);
        System.out.println("User registered successfully!");
    }
}

    // Registration for a new worker
    private static void registerWorker() {
        System.out.print("Enter workerID (numeric only): ");
        int workerID = scanner.nextInt(); 
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // workerID validation
        boolean exists = false;
        for (Worker worker : workerList) {
            if (worker.getWorkerID() == workerID) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("WorkerID already exists. Please try creating a different workerID.");
        } else {
            Worker newWorker = new Worker(workerID, password); 
            workerList.add(newWorker);
            System.out.println("Worker registered successfully!");
        }
    }

    // User login function -> returning boolean
    private static boolean loginUser() {
        System.out.print("Enter userID (numeric only): ");
        int userID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : userList) {
            if (user.getUserID() == userID && user.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome:)");
                return true;
            }
        }
        System.out.println("Try entering userID or password again!");
        return false;
    }

    // Worker login function -> returning boolean
    private static boolean loginWorker() {
        System.out.print("Enter workerID (numeric only): ");
        int workerID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Worker worker : workerList) {
            if (worker.getWorkerID() == workerID && worker.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome:)");
                return true;
            }
        }
        System.out.println("Try entering workerID or password again!");
        return false;
    }

    private static Package searchByID(int packageId) {
        for (Package pkg : pkg_list) {
            if (pkg.getId() == packageId) {
                return pkg;
            }
        }
        return null;
    }

    private static void sendPackage() {
        System.out.println("Enter sender ID:");
        int senderId = scanner.nextInt();
        System.out.println("Enter receiver ID:");
        int receiverId = scanner.nextInt();
        Package newPackage = new Package(senderId, receiverId);
        pkg_list.add(newPackage);
        System.out.println("Package sent successfully!");
    }

    private static void trackSendingPackages() {
        System.out.println("Enter your sender ID to track sending packages:");
        int senderID = scanner.nextInt();
        boolean found = false;
        for (Package pkg : pkg_list) {
            if (pkg.getSenderId() == senderID) {
                System.out.println("Package ID: " + pkg.getId() + " Status: " + pkg.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("None");
        }
    }

    private static void viewSendingPackages() {
        System.out.println("Enter your user ID to view sending packages:");
        int senderID = scanner.nextInt();
        boolean found = false;
        for (Package pkg : pkg_list) {
            if (pkg.getSenderId() == senderID) {
                System.out.println("Package ID: " + pkg.getId());
                found = true;
            }
        }
        if (!found) {
            System.out.println("None");
        }
    }

    private static void trackReceivingPackages() {
        System.out.println("Enter your receiver ID to track receiving packages:");
        int receiverID = scanner.nextInt();
        boolean found = false;
        for (Package pkg : pkg_list) {
            if (pkg.getReceiverId() == receiverID) {
                System.out.println("Package ID: " + pkg.getId() + " Status: " + pkg.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("None");
        }
    }

    private static void viewReceivingPackages() {
        System.out.println("Enter your receiver ID to view receiving packages:");
        int receiverID = scanner.nextInt();
        boolean found = false;
        for (Package pkg : pkg_list) {
            if (pkg.getReceiverId() == receiverID) {
                System.out.println("Package ID: " + pkg.getId());
                found = true;
            }
        }
        if (!found) {
            System.out.println("None");
        }
    }

    private static void updatePackageStatus() {
        System.out.println("Enter package ID:");
        int packageId = scanner.nextInt();
        scanner.nextLine();
        Package pkg = searchByID(packageId);


        if (pkg != null) {
            System.out.println("Enter new status (Label Created, On the Way, Delivered):");
            String newStatus = scanner.nextLine();
            Worker worker = new Worker(0, "PasswordPlaceholder");
            worker.updatePackageStatus(pkg, newStatus);
        } else {
            System.out.println("No package found!");
        }
    }

    private static void handleUserActions() {
        boolean running = true;
        while (running) {
            System.out.println("User Menu:");
            System.out.println("1. I am a Sender");
            System.out.println("2. I am Receiver");
            System.out.println("3. Log Out");
            System.out.print("Choose your role: ");
            int roleChoice = scanner.nextInt();
            scanner.nextLine();

            switch (roleChoice) {
                case 1:
                    handleSenderActions();
                    break;
                case 2:
                    handleReceiverActions();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Please try again (choose between 1-3).");
                    break;
            }
        }
    }

    private static void handleSenderActions() {
        System.out.println("Sender Menu:");
        System.out.println("(1) Send Package");
        System.out.println("(2) Track Sending Packages");
        System.out.println("(3) View Sending Packages");
        System.out.println("(4) Log Out");
        System.out.print("Choose an action: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {
            case 1:
                sendPackage();
                break;
            case 2:
                trackSendingPackages();
                break;
            case 3:
                viewSendingPackages();
                break;
            case 4:
                System.out.println("Logging out the system. Have a great day:)");
                return;
            default:
                System.out.println("Try again (choose between 1-4).");
                break;
        }
    }

    private static void handleReceiverActions() {
        System.out.println("Receiver Menu:");
        System.out.println("(1) Track Receiving Packages");
        System.out.println("(2) View Receiving Packages");
        System.out.println("(3) Log Out");
        System.out.print("Choose an action: ");
        int action = scanner.nextInt();
        scanner.nextLine();

        switch (action) {
            case 1:
                trackReceivingPackages();
                break;
            case 2:
                viewReceivingPackages();
                break;
            case 3:
                System.out.println("Logging out the system. Have a great day:)");
                return;
            default:
                System.out.println("Try again (choose between 1-3).");
                break;
        }
    }

    private static void handleWorkerActions() {
    boolean running = true;
        while (running) {
            System.out.println("Worker Menu:");
            System.out.println("1. Update Package Status");
            System.out.println("2. Log Out");
            System.out.print("Choose an action: ");
            int action = scanner.nextInt();
            scanner.nextLine(); 

            switch (action) {
                case 1:
                    updatePackageStatus();
                    break;
                case 2:
                    System.out.println("Logging out the system. Thank you for your work:)");
                    running = false;
                    break;
                default:
                    System.out.println("Please try again (choose between 1-2).");
                    break;
            }
        }
    }

    private static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("mailingData.ser"))) {
            out.writeObject(userList);
            out.writeObject(workerList);
            out.writeObject(pkg_list);
        } catch (IOException e) {
            System.out.println("Error occurred while saving data: " + e.getMessage());
        }
    }

    private static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("mailingData.ser"))) {
            userList = (ArrayList<User>) in.readObject();
            workerList = (ArrayList<Worker>) in.readObject();
            pkg_list = (ArrayList<Package>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data: " + e.getMessage());
            // If the data file does not exist => Initialize empty lists 
            userList = new ArrayList<>();
            workerList = new ArrayList<>();
            pkg_list = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        loadData(); 
        while (true) {
            System.out.println("Mailing Service System");
            System.out.println("============");
            System.out.println("1. Register as User");
            System.out.println("2. Register as Worker");
            System.out.println("3. User Login");
            System.out.println("4. Worker Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    registerWorker();
                    break;
                case 3:
                    if (loginUser()) {
                        handleUserActions();
                    }
                    break;
                case 4:
                    if (loginWorker()) {
                        handleWorkerActions();
                    }
                    break;
                case 5:
                    saveData();
                    System.exit(0);
                default:
                    System.out.println("Please try again (choose between 1-5).");
                    break;
            }
        }
    }
}
