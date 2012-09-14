package chat.client;
import javax.swing.JFrame;

import chat.client.exceptions.NetworkException;
import chat.client.threading.ServerManager;
import chat.client.view.Register;


public class Main {

	
	public static void main(String[] args) {
		/*Register register = new Register();
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		register.setSize(350, 600);
		register.setVisible(true);*/
		ServerManager server = new ServerManager();
		
		try {
			server.run("localhost", 12345);
			server.registerUser("tailmon2", "12345", "12345", "Pablo", "Sanabria", "pablo@mail.com");
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
