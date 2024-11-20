package funeralrecordsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Clients {
    private Scanner sc = new Scanner(System.in); 

    public void cTransaction() {
        String response;

        do {
            System.out.println("\n ----------------------");
            System.out.println("|    CLIENTS PANEL     |");
            System.out.println(" ----------------------");
            System.out.println("| 1.   ADD CLIENTS     |");
            System.out.println(" ----------------------");
            System.out.println("| 2.   VIEW CLIENTS    |");
            System.out.println(" ----------------------");
            System.out.println("| 3.  UPDATE CLIENTS   |");
            System.out.println(" ----------------------");
            System.out.println("| 4.  DELETE CLIENTS   |");
            System.out.println(" ----------------------");
            System.out.println("| 5.      EXIT         |");
            System.out.println(" ----------------------");

            System.out.print("Enter selection: ");

            while (true) {
                if (sc.hasNextInt()) {
                    int act = sc.nextInt();
                    sc.nextLine(); 
                    if (act >= 1 && act <= 5) {
                        switch (act) {
                            case 1:
                                addClients();
                                viewClients();
                                break;
                            case 2:
                                viewClients();
                                break;
                            case 3:
                                viewClients();
                                updateClients();
                                viewClients();
                                break;
                            case 4:
                                viewClients();
                                deleteClients();
                                viewClients();
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

    private void addClients() {
        System.out.print("Client's Name: ");
        String cname = sc.nextLine();

        System.out.print("Contact Number: ");
        String conum = sc.nextLine();

        String qry = "INSERT INTO tbl_clients (c_name, c_connum) VALUES (?, ?)";
        config cons = new config();
        cons.addRecord(qry, cname, conum);
    }

    public void viewClients() {
        String qry = "SELECT * FROM tbl_clients";
        String[] headers = {"ID", "Name", "Contact Number"};
        String[] columns = {"c_id", "c_name", "c_connum"};
        config cons = new config();
        cons.viewRecords(qry, headers, columns);
    }

    private void updateClients() {
        System.out.print("Enter ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        config cons = new config();
        while (cons.getSingleValue("SELECT c_id FROM tbl_clients WHERE c_id = ?", id) == 0) {
            System.out.println("Selected ID does not exist.");
            System.out.print("Select Clients ID Again: ");
            id = sc.nextInt();
            sc.nextLine();
        }

        System.out.print("Client's Name: ");
        String cname = sc.nextLine();

        System.out.print("Contact Number: ");
        String conum = sc.nextLine();

        String qry = "UPDATE tbl_clients SET c_name = ?, c_connum = ? WHERE c_id = ?";
        cons.updateRecord(qry, cname, conum, id);
    }

    public void deleteClients() {
        config cons = new config();
        System.out.print("Enter ID to Delete: ");
        int id = sc.nextInt();

        while (cons.getSingleValue("SELECT c_id FROM tbl_clients WHERE c_id = ?", id) == 0) {
            System.out.println("Selected ID does not exist.");
            System.out.print("Select Clients ID Again: ");
            id = sc.nextInt();
            sc.nextLine();
        }

        String qry = "DELETE FROM tbl_clients WHERE c_id = ?";
        cons.deleteRecord(qry, id);
    }

    
    
}
