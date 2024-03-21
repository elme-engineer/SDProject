import java.util.Scanner;  // Import the Scanner class

public class Client {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        int choice = -1;
        System.out.println("Hello client! What do you want to do?");

        do{
            System.out.println("Please select one of the following options:");
            System.out.println("1. Index new URL");
            System.out.println("2. Search for URL");
            System.out.println("3. Admin");
            System.out.println("9. Exit\n");

            // Read user input
            try {
                choice = input.nextInt();  // Read user input
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.nextInt(); // Clear the scanner buffer
            }
            
            String message = "";
            String[] invalid = {"\\", "|", ";"};

            switch(choice){
                case 1:
                    System.out.println("Index new URL");
                    message += "type|" + Integer.toString(choice);
                    System.out.println("Insert URL to be added: ");
                    String url = input.nextLine();
                    while (url.contains(invalid)){ // TO FIX

                    }
                    message +=  url + '\n';
                    // !!!  URL verification will be made on server side, no need for input handle !!!
                    // Send message to the server
                    System.out.println("URL " + url + " added to the server!");
                    break;

                case 2:
                    System.out.println("Search for URL/Keywords");
                    message = message + "type|" + Integer.toString(choice) + "\n";
                    // Send message to the server
                    // Print server feedback
                    break;

                case 3:
                    System.out.println("Admin");
                    message = message + "type|" + Integer.toString(choice) + "\n";
                    // Send message to the server
                    // Print server feedback
                    break;

                case 9:
                    int option = -1;
                    System.out.println("Are you sure you want to exit?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    try {
                        System.out.println("Please select a valid option:");
                        option = input.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        option = input.nextInt();                            
                    }
                    if(option != 1 && option != 2){
                        do{
                            try {
                                System.out.println("Please select a valid option:");
                                option = input.nextInt();
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Invalid input. Please enter an integer.");
                                option = input.nextInt();                            
                            }
                        }while(!(option == 1 || option == 2));
                    }
                    // Change choice to a non terminal value
                    if(option == 2){
                        choice = -1;
                    }
                    break;

                default:
                    System.out.println("There was an error in the input. Please try again.\n");
                    break;
            }
        }
        while (choice != 9);
        input.close();
    } 

    
}
