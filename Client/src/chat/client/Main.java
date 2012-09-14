package chat.client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

import chat.client.exceptions.NetworkException;
import chat.client.threading.ServerManager;
import chat.client.view.Register;


public class Main {

	
	public static void main(String[] args) throws Exception {
		/*ServerManager server = new ServerManager();
		
		try {
			server.run("localhost", 12345);
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Register register = new Register();
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		register.setSize(350, 600);
		register.setVisible(true); */
		Socket so = new Socket("localhost", 12345);
		ObjectOutputStream output = new ObjectOutputStream(so.getOutputStream());		
		output.flush();
		ObjectInputStream input = new ObjectInputStream(so.getInputStream());
		Scanner s = new Scanner(System.in);
		
		while(true) {
			String line = s.nextLine();
			output.writeObject(line);
			
			if (line.equals("CONTACTLIST")) {
				System.out.println(input.readObject().toString());
				System.out.println(input.readObject().toString());
				System.out.println(input.readObject().toString());
				System.out.println(input.readObject().toString());
			}
		}
	}

}
