package funeralrecordsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Deceased {
    private Scanner sc = new Scanner(System.in); 

    public void dTransaction() {
        String response;

        do {
            System.out.println("\n ----------------------");
            System.out.println("|    DECEASED PANEL     |");
            System.out.println(" ----------------------");
            System.out.println("| 1.   ADD DECEASED     |");
            System.out.println(" ----------------------");
            System.out.println("| 2.   VIEW DECEASED    |");
            System.out.println(" ----------------------");
            System.out.println("| 3.  UPDATE DECEASED   |");
            System.out.println(" ----------------------");
            System.out.println("| 4.  DELETE DECEASED   |");
            System.out.println(" ----------------------");
            System.out.println("| 5.       EXIT         |");
            System.out.println(" ----------------------");

            System.out.print("Enter selection: ");

           
            while (true) {
                if (sc.hasNextInt()) {
                    int act = sc.nextInt();
                    sc.nextLine(); 
                    if (act >= 1 && act <= 5) {
                        switch (act) {
                            case 1:
                                addDeceased();
                                viewDeceased();
                                break;
                            case 2:
                                viewDeceased();
                                break;
                            case 3:
                                viewDeceased();
                                updateDeceased();
                                viewDeceased();
                                break;
                            case 4:
                                viewDeceased();
                                deleteDeceased();
                                viewDeceased();
                                break;
                            case 5:
                                System.out.println("Exiting...");
                                return;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }
                        break;  
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and 5.");
                        System.out.print("Enter selection: ");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); 
                    System.out.print("Enter selection: ");
                }
            }

            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.nextLine();

        } while (response.equalsIgnoreCase("yes"));
    }

    public void addDeceased() {
        System.out.print("Deceased Full Name: ");
        String dfullname = sc.nextLine();
        System.out.print("Date of Birth: ");
        String dob = sc.nextLine();
        System.out.print("Date of Death: ");
        String dod = sc.nextLine();
        System.out.print("Place of Death: ");
        String pod = sc.nextLine();
        System.out.print("Cause of Death: ");
        String cod = sc.nextLine();

        String qry = "INSERT INTO tbl_deceased (d_flname, d_dob, d_dod, d_pod, d_cod) VALUES (?, ?, ?, ?, ?)";
        config cons = new config();
        cons.addRecord(qry, dfullname, dob, dod, pod, cod);
    }

    public void viewDeceased() {
        String qry = "SELECT * FROM tbl_deceased";
        String[] headers = {"ID", "Name", "Date of Birth", "Date of Death", "Place of Death", "Cause of Death"};
        String[] columns = {"d_id", "d_flname", "d_dob", "d_dod", "d_pod", "d_cod"};
        config cons = new config();
        cons.viewRecords(qry, headers, columns);
    }

    public void updateDeceased() {
        System.out.print("Enter ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); 

        config cons = new config();
        while (cons.getSingleValue("SELECT d_id FROM tbl_deceased WHERE d_id = ?", id) == 0) {
            System.out.println("Selected ID does not exist.");
            System.out.print("Select Deceased ID Again: ");
            id = sc.nextInt();
            sc.nextLine();  
        }

        System.out.print("Update Deceased Name: ");
        String dfullname = sc.nextLine();
        System.out.print("Update Date of Birth: ");
        String dob = sc.nextLine();
        System.out.print("Update Date of Death: ");
        String dod = sc.nextLine();
        System.out.print("Update Place of Death: ");
        String pod = sc.nextLine();
        System.out.print("Update Cause of Death: ");
        String cod = sc.nextLine();

        String qry = "UPDATE tbl_deceased SET d_flname = ?, d_dob = ?, d_dod = ?, d_pod = ?, d_cod = ? WHERE d_id = ?";
        cons.updateRecord(qry, dfullname, dob, dod, pod, cod, id);
    }

    public void deleteDeceased() {
        Scanner sc = new Scanner(System.in);
        config cons = new config();
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();

        while (cons.getSingleValue("SELECT d_id FROM tbl_deceased WHERE d_id = ?", id) == 0) {
            System.out.println("Selected ID does not exist.");
            System.out.print("Select Deceased ID Again: ");
            id = sc.nextInt();
            sc.nextLine();
        }

        String qry = "DELETE FROM tbl_deceased WHERE d_id = ?";
        cons.deleteRecord(qry, id);
    }

    public void viewDeceasedview() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Deceased ID to view: ");
        int deceasedId = sc.nextInt();
        sc.nextLine();  

        String qry = "SELECT * FROM tbl_deceased WHERE d_id = ?";
        config cons = new config();

        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s |\n", "ID", "Name", "Date of Birth", "Date of Death", "Cause of Death");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");

        try (Connection conn = cons.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(qry)) {
            pstmt.setInt(1, deceasedId); 
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("d_id");
                String name = rs.getString("d_flname");
                String dob = rs.getString("d_dob");
                String dod = rs.getString("d_dod");
                String cod = rs.getString("d_cod");
                System.out.printf("| %-20d | %-20s | %-20s | %-20s | %-20s |\n", id, name, dob, dod, cod);
            } else {
                System.out.println("No deceased record found with ID: " + deceasedId);
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------");
        }
    }
}
