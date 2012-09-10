import javax.swing.JFrame;

import chat.client.view.Register;


public class Main {

	
	public static void main(String[] args) {
		Register register = new Register();
		register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		register.setSize(350, 600);
		register.setVisible(true);

	}

}
