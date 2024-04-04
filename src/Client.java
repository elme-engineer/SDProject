import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.Scanner;
import java.net.*;

public class Client extends UnicastRemoteObject implements ICliente {

	private IGatewayCliente gateway;

	Client(IGatewayCliente gw) throws RemoteException {

		this.gateway = gw;

		run();

	}

	public void run(){

        int choice = -1;

		try (Scanner sc = new Scanner(System.in)) {
			

            System.out.println("Please select one of the following options:");
            System.out.println("1. Index new URL");
            System.out.println("2. Search for URL");
            System.out.println("3. Search for keyword");
            System.out.println("4. Admin");
            System.out.println("9. Exit\n");


			while (choice != 9) {
                
                // Read user input
                try {
                    choice = sc.nextInt();  // Read user input

                } catch (java.util.InputMismatchException e) {

                    System.err.println("Invalid input. Please enter an integer.");
                    sc.nextLine(); // Clear the scanner buffer
                    continue;

                }
               
                    
                    
                String userInput = new String();
                StringBuilder url = new StringBuilder();

                CharSequence invalid = new StringBuilder("\\|;");
        
                switch(choice){

                    case 1:

                        
                        System.out.print("Insert URL to be added: ");
                        userInput = sc.nextLine();

                        if(userInput.contains(invalid)){
                            System.err.println("Invalid url");
                            break;
                        }
                        url = new StringBuilder(userInput);
                        gateway.sendURLIndex(url);
                        //System.out.println("URL " + url + " added to the server!");
                        break;
    
                    case 2:

                        System.out.print("Insert URL/Keyword to be searched: ");

                        userInput = sc.nextLine();

                        if(userInput.contains(invalid)){
                            System.err.println("Invalid input");
                            break;
                        }

                        url = new StringBuilder(userInput);
                        gateway.searchInput(url);
                        //System.out.println("URL " + url + " added to the server!");
                        break;
    
    
                    case 3:

                        //System.out.println("Admin");
                        
                        // Send message to the server
                        // Print server feedback
                        break;

                    case 9:
                        
                        System.out.println("Goodbye");
                        break;
    
                    default:
                        System.out.println("There was an error in the input. Please try again.\n");
                        break;
                }
                
            }
                
            sc.close();

		} catch (Exception e) {
			System.out.println("Exception in main: " + e);
		}
	}

	public static void main(String args[]) {
	
        try {

            IGatewayCliente gateway = (IGatewayCliente)LocateRegistry.getRegistry(9000).lookup("Gateway");
        
		
			Client c = new Client(gateway);

		}catch(RemoteException re){
            System.out.println("Cant connect to Gateway");
        } catch(Exception e){
            System.out.println("Exception in main: " + e);
        }



	}

}
