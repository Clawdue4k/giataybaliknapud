package funeralrecordsystem;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewRecords {

    // Declare config instance (already in your code)
    private config cons;

    // Constructor
    public ViewRecords() {
        cons = new config();
    }

    public void recordsMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String response;

        do {
            System.out.println("\n--------------------------------");
            System.out.println("| Welcome to the Records Panel |");
            System.out.println("--------------------------------");
            System.out.println("1. View Specific Records");
            System.out.println("2. View General Payments Records");
            System.out.println("3. Exit");

            System.out.print("Enter Selection: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ViewSpecificRecords(); 
                    break;
                case 2:
                    System.out.println("View General Records is not yet implemented.");
                    break;
                case 3:
                    System.out.println("Exiting Records Panel...");
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

    public void ViewSpecificRecords() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n1. View Client by ID");
        System.out.println("2. View Deceased by ID");

        System.out.print("Enter the option number: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                viewClientRecords(); 
                break;
            case 2:
                viewDeceasedRecords(); 
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // View client records by ID
    public void viewClientRecords() throws SQLException {
        Clients clients = new Clients(); // Create an instance of the Clients class
        clients.viewClientsview(); // Call the method to display client records
    }

    // View deceased records by ID (you can implement the logic similarly as viewClientsview)
    public void viewDeceasedRecords() throws SQLException {
        Deceased deceased = new Deceased(); // Create an instance of the Deceased class
        deceased.viewDeceasedview(); // Call the method to display deceased records
    }
}
