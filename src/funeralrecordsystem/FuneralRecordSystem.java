package funeralrecordsystem;

import java.sql.SQLException;
import java.util.Scanner;

public class FuneralRecordSystem {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        do {
            System.out.println("\n-----------------------------------------");
            System.out.println("|   WELCOME TO FUNERAL TRACKING SYSTEM  |");
            System.out.println("-----------------------------------------");
            System.out.println("1. CLIENTS");
            System.out.println("2. DECEASED");
            System.out.println("3. FUNERAL DETAILS");
            System.out.println("4. VIEW RECORDS");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");

           
            while (true) {
                
                if (sc.hasNextInt()) {
                    int action = sc.nextInt();
                    sc.nextLine(); 

                    if (action >= 1 && action <= 5) {
                        
                        switch (action) {
                            case 1:
                                Clients cl = new Clients();
                                cl.cTransaction();
                                break;
                            case 2:
                                Deceased de = new Deceased();
                                de.dTransaction();
                                break;
                            case 3:
                                FuneralDetails fd = new FuneralDetails();
                                fd.fdTransaction();
                                break;
                            case 4:
                                ViewRecords vr = new ViewRecords();
                                vr.recordsMenu();
                                break;
                            case 5:
                                System.out.print("Exit Selected... Type 'yes' to confirm: ");
                                String resp = sc.nextLine();
                                if (resp.equalsIgnoreCase("yes")) {
                                    running = false;
                                }
                                break;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }
                        break; 
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and 5.");
                        System.out.print("Enter Action: ");
                    }
                } else {
                    
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); // Clear the invalid input
                    System.out.print("Enter Action: ");
                }
            }

        } while (running);

        sc.close();
        System.out.println("Exiting Funeral Record Tracker. Goodbye!");
    }
}
