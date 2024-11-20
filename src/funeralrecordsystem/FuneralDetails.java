package funeralrecordsystem;

import java.util.Scanner;

public class FuneralDetails {

    private Scanner sc = new Scanner(System.in); 
    private Object cons;

    public void fdTransaction() {
        String response;

        do {
            System.out.println("\n -----------------------------");
            System.out.println("|    FUNERAL DETAILS PANEL    |");
            System.out.println(" -----------------------------");
            System.out.println("| 1.       ADD FUNERAL        |");
            System.out.println(" -----------------------------");
            System.out.println("| 2.      VIEW FUNERAL        |");
            System.out.println("-----------------------------");
            System.out.println("| 3.     UPDATE FUNERAL       |");
            System.out.println(" -----------------------------");
            System.out.println("| 4.     DELETE FUNERAL       |");
            System.out.println(" -----------------------------");
            System.out.println("| 5.          EXIT            |");
            System.out.println(" -----------------------------");

            System.out.print("Enter selection: ");

            while (true) {
                if (sc.hasNextInt()) {
                    int act = sc.nextInt();
                    sc.nextLine();

                    if (act >= 1 && act <= 5) {
                        switch (act) {
                            case 1:
                                addFuneral();
                                viewAllFuneralRecords();  // Renamed method
                                break;
                            case 2:
                                viewAllFuneralRecords();  // Renamed method
                                break;
                            case 3:
                                viewAllFuneralRecords();  // Renamed method
                                updateFuneral();
                                viewAllFuneralRecords();  // Renamed method
                                break;
                            case 4:
                                viewAllFuneralRecords();  // Renamed method
                                deleteFuneral();
                                viewAllFuneralRecords();  // Renamed method
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

    public void addFuneral() {
        config cons = new config();

        Clients cl = new Clients();
        cl.viewClients();
        System.out.print("Enter the selected ID of the Client: ");
        int cid = sc.nextInt();
        sc.nextLine(); 

        String csql = "SELECT c_id FROM tbl_clients WHERE c_id = ?";
        while (cons.getSingleValue(csql, cid) == 0) {
            System.out.print("Client does not exist, select again: ");
            cid = sc.nextInt();
            sc.nextLine(); 
        }

        Deceased de = new Deceased();
        de.viewDeceased();
        System.out.print("Enter the selected ID of the Deceased: ");
        int did = sc.nextInt();
        sc.nextLine(); 

        String dsql = "SELECT d_id FROM tbl_deceased WHERE d_id = ?";
        while (cons.getSingleValue(dsql, did) == 0) {
            System.out.print("Deceased does not exist, select again: ");
            did = sc.nextInt();
            sc.nextLine(); 
        }

        System.out.print("Name of Funeral Service Provider: ");
        String fservice = sc.nextLine();

        String fqry = "INSERT INTO tbl_fd (c_id, d_id, f_fservice) VALUES (?, ?, ?)";
        cons.addRecord(fqry, cid, did, fservice);

        System.out.println("Funeral details added successfully!");
    }

   
    public void viewAllFuneralRecords() {
        String qry = "SELECT f_id, c_name, c_connum, d_flname, d_dob, d_dod, d_cod, f_fservice " +
                     "FROM tbl_fd " +
                     "LEFT JOIN tbl_clients ON tbl_clients.c_id = tbl_fd.c_id " +
                     "LEFT JOIN tbl_deceased ON tbl_deceased.d_id = tbl_fd.d_id";
        String[] headers = {"ID", "Client Name", "Contact Number", "Deceased Full Name", 
                            "Date of Birth", "Date of Death", "Cause of Death", "Funeral Service"};
        String[] columns = {"f_id", "c_name", "c_connum", "d_flname", "d_dob", "d_dod", "d_cod", "f_fservice"};
        config cons = new config();
        cons.viewRecords(qry, headers, columns);
    }

    public void updateFuneral() {
        config cons = new config();

        System.out.print("Enter the Funeral ID to update: ");
        int fid = sc.nextInt();
        sc.nextLine(); 
        
        String fsql = "SELECT f_id FROM tbl_fd WHERE f_id = ?";
        if (cons.getSingleValue(fsql, fid) == 0) {
            System.out.println("Funeral ID not found.");
            return;
        }
        System.out.print("Enter new Funeral Service Provider name: ");
        String newFservice = sc.nextLine();

        String updateQry = "UPDATE tbl_fd SET f_fservice = ? WHERE f_id = ?";
        cons.updateRecord(updateQry, newFservice, fid);

        System.out.println("Funeral details updated successfully!");
    }

    public void deleteFuneral() {
        config cons = new config();

        System.out.print("Enter the Funeral ID to delete: ");
        int fid = sc.nextInt();
        sc.nextLine(); 

        String fsql = "SELECT f_id FROM tbl_fd WHERE f_id = ?";
        if (cons.getSingleValue(fsql, fid) == 0) {
            System.out.println("Funeral ID not found.");
            return;
        }

        String deleteQry = "DELETE FROM tbl_fd WHERE f_id = ?";
        cons.deleteRecord(deleteQry, fid);

        System.out.println("Funeral details deleted successfully!");
    }
    
}
