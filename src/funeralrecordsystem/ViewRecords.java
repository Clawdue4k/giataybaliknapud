/*
package funeralrecordsystem;
 

import java.util.Scanner;

public class ViewRecords {

    private config con; // Declare the config instance

    // Constructor to initialize the config instance
    public ViewRecords() {
        con = new config(); // Initialize the config instance
    }

    // Records menu to allow user to choose what to view
    public void recordsMenu() {
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
                    ViewSpecificRecords(); // Call method for specific records
                    break;
                case 2:
                    ViewGeneralRecords(); // Call method for general payments records
                    break;
                case 3:
                    System.out.println("Exiting Records Panel...");
                    return; // Exit the menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // Ask user if they want to continue
            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

    // Method to view specific records by client or deceased ID
    public void ViewSpecificRecords() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n1. View Client by ID");
        System.out.println("2. View Deceased by ID");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                viewClientRecords();  // Call method to view client records
                break;
            case 2:
                viewDeceasedRecords();  // Call method to view deceased records
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    // Method to view client records by client ID
    private void viewClientRecords() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Client's ID: ");
        int clientId = sc.nextInt(); // Get the Client ID

        // Use the config class to query and display client details by ID
        String qry = "SELECT * FROM tbl_clients WHERE c_id = ?";
        String[] headers = {"ID", "Name", "Contact Number"};
        String[] columns = {"c_id", "c_name", "c_connum"};

        // Call the config class to display specific client details
        con.viewRecords(qry, headers, columns, clientId);
    }

    // Method to view deceased records by deceased ID
    private void viewDeceasedRecords() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Deceased's ID: ");
        int deceasedId = sc.nextInt(); // Get the Deceased ID

        // Use the config class to query and display deceased details by ID
        String qry = "SELECT * FROM tbl_deceased WHERE d_id = ?";
        String[] headers = {"ID", "Name", "Date of Birth", "Date of Death", "Place of Death", "Cause of Death"};
        String[] columns = {"d_id", "d_flname", "d_dob", "d_dod", "d_pod", "d_cod"};

        // Call the config class to display specific deceased details
        con.viewRecords(qry, headers, columns, deceasedId);
    }

    // Method to view general funeral records (i.e., funeral details or payments)
    public void ViewGeneralRecords() {
        // Query and headers for general funeral records
        String qry = "SELECT * FROM tbl_funeral_details"; // Adjust as necessary for general records
        String[] headers = {"ID", "Funeral Type", "Amount", "Payment Status"};
        String[] columns = {"funeral_id", "funeral_type", "amount", "payment_status"};

        // Call the config class to display general funeral details
        con.viewRecords(qry, headers, columns, 0);  // Assuming '0' here for general records
    }
}
*/