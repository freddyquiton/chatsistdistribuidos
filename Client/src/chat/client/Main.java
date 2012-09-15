package chat.client;
import javax.swing.JFrame;

import chat.client.view.Register;


public class Main {

	
	public static void main(String[] args) throws Exception {		
		Register r = new Register();
		r.setSize(600, 480);
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.setVisible(true);
	}

}
