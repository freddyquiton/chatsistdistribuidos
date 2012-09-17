package chat.client;
import javax.swing.JFrame;

import chat.client.threading.ServerManager;
import chat.client.view.LoginFrame;


public class Main {

	
	public static void main(String[] args) throws Exception {
		ServerManager server = new ServerManager();
		String url = "";
		
		if (args.length >= 1) {
			System.out.println("Conectandose a : " + args[0]);
			url = args[0];
			
			LoginFrame r = new LoginFrame(server, url, 12345, 12346);
			r.setSize(600, 480);
			r.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			r.setVisible(true);
		} else {
			System.out.println("Usage: java -jar Client.jar IPSERVER");
		}
	}

}
