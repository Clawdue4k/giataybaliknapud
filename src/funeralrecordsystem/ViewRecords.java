package funeralrecordsystem;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewRecords {

    private config cons; // Configuration object for database operations
    private FuneralDetails funeralDetails; // Instance of FuneralDetails class

    public ViewRecords() {
        cons = new config();
        funeralDetails = new FuneralDetails(); // Initialize FuneralDetails
    }

    public void recordsMenu() throws SQLException {
        Scanner sc = new Scanner(System.in); // Scanner for user input
        String response;

        do {
            System.out.println("\n--------------------------------");
            System.out.println("| Welcome to the Records Panel |");
            System.out.println("--------------------------------");
            System.out.println("1. View Funeral Records");
            System.out.println("2. Exit");

            System.out.print("Enter Selection: ");
            int choice = sc.nextInt(); // Get user choice

            switch (choice) {
                case 1:
                    funeralDetails.viewAllFuneralRecords(); // Call method from FuneralDetails class
                    System.out.print("Enter the Funeral Record ID to view details (or 0 to exit): ");
                    int id = sc.nextInt(); // Get user input for the specific record ID
                    if (id != 0) {
                        viewDetailedFuneralById(id); // Call the method to view the details of the selected ID
                    }
                    break;
                case 2:
                    System.out.println("Exiting Records Panel...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.print("Do you want to continue? (Yes/No): ");
            response = sc.next();
        } while (response.equalsIgnoreCase("Yes"));
    }

    // Method to view detailed record by ID
    private void viewDetailedFuneralById(int id) {
        if (id == 0) {
            System.out.println("No ID entered. Exiting...");
            return;
        }

        // Query to retrieve the detailed information based on the selected ID
        String qry = "SELECT f_id, c_name, c_connum, d_flname, d_dob, d_dod, d_cod, f_fservice " +
                     "FROM tbl_fd " +
                     "LEFT JOIN tbl_clients ON tbl_clients.c_id = tbl_fd.c_id " +
                     "LEFT JOIN tbl_deceased ON tbl_deceased.d_id = tbl_fd.d_id " +
                     "WHERE f_id = ?";
        String[] headers = {"ID", "Client Name", "Contact Number", "Deceased Full Name", 
                            "Date of Birth", "Date of Death", "Cause of Death", "Funeral Service"};
        String[] columns = {"f_id", "c_name", "c_connum", "d_flname", "d_dob", "d_dod", "d_cod", "f_fservice"};

        // Call the method to fetch and display the specific record
        cons.viewSingleRecord(qry, headers, columns, id);
    }
}
