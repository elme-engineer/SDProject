import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;

public class Gateway {
    //static Hello_C_I client;

	//private IClient clientCallback;

	public ArrayList<ICliente> clientes;

	public Gateway() throws RemoteException {
		super();
		clientes = new ArrayList<>();

		LocateRegistry.createRegistry(1099);

		try {
			Naming.rebind("XPTO", (IGateway)this);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
		run();
	}

	public void run(){

		String a;
		try (Scanner sc = new Scanner(System.in)) {
			//User user = new User();
			System.out.println("Hello Server ready.");
			while (true) {
				System.out.print("> ");
				a = sc.nextLine();
				this.print_on_all_clients(a);
				//client.print_on_client(a);
			}
		} catch (Exception re) {
			System.out.println("Exception in HelloImpl.main: " + re);
		}
	}

	public void pesquisa(String s) throws RemoteException {
		System.out.println("> " + s);
		print_on_all_clients(s);
	}

	public void print_on_all_clients(String s){
		for (ICliente c:clientes) {
			try {
				c.atualizaAdminPage(s);
			} catch (RemoteException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void subscribe(ICliente c) throws RemoteException {
		//System.out.println("Subscribing " + name);
		System.out.print("client subscribed");
		//client = c;
		clientes.add(c);
	}

	// =======================================================

	public static void main(String args[]) {
		//String a;

		/*
		System.getProperties().put("java.security.policy", "policy.all");
		System.setSecurityManager(new RMISecurityManager());
		*/
		try {
			Gateway h = new Gateway();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}

	}
}
