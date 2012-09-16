package chat.client;
import javax.swing.JFrame;

import chat.client.threading.ServerManager;
import chat.client.view.LoginFrame;


public class Main {

	
	public static void main(String[] args) throws Exception {
		ServerManager server = new ServerManager();
		LoginFrame r = new LoginFrame(server, "localhost", 12345);
		r.setSize(600, 480);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setVisible(true);
	}

}
