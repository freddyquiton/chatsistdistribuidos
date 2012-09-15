package chat.client.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register extends JFrame {

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel repasswordLabel;
	private JLabel firstnameLabel;
	private JLabel lastnameLabel;
	private JLabel emailLabel;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JPasswordField repasswordTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;
	private JTextField emailTxt;
	private JButton okButton;
	private JButton cancelButton;
	
	private final int uslimite = 10;
	private final int passlimite = 10;
	private final int repaslimite = 10;
	private final int firslimite = 20;
	private final int laslimite = 20;
	private final int emailimite = 15;

	public Register() {

		super("REGISTRAR USUARIO");
		setLayout(new GridLayout(7, 2));

		usernameLabel = new JLabel("Nombre de Usuario:");
		add(usernameLabel, 0);
		usernameTxt = new JTextField(20);
		add(usernameTxt, 1);
		
		passwordLabel = new JLabel("Contraseña:");
		add(passwordLabel, 2);
		passwordTxt = new JPasswordField(20);
		add(passwordTxt, 3);

		repasswordLabel = new JLabel("Confirme Contraseña:");
		add(repasswordLabel, 4);
		repasswordTxt = new JPasswordField(20);
		add(repasswordTxt, 5);

		firstnameLabel = new JLabel("Nombre(s):");
		add(firstnameLabel, 6);
		firstnameTxt = new JTextField(20);
		add(firstnameTxt, 7);

		lastnameLabel = new JLabel("Apellido(s):");
		add(lastnameLabel, 8);
		lastnameTxt = new JTextField(20);
		add(lastnameTxt, 9);

		emailLabel = new JLabel("E-mail:");
		add(emailLabel, 10);
		emailTxt = new JTextField(20);
		add(emailTxt, 11);

		okButton = new JButton("Registrar");
		add(okButton, 12);

		cancelButton = new JButton("Cancelar");
		add(cancelButton, 13);

		ButtonHandler handler = new ButtonHandler();
		okButton.addActionListener(handler);
		cancelButton.addActionListener(handler);
		
		KeyHandler keyHandler = new KeyHandler();

		usernameTxt.addKeyListener(keyHandler);
		passwordTxt.addKeyListener(keyHandler);
		repasswordTxt.addKeyListener(keyHandler);
		firstnameTxt.addKeyListener(keyHandler);
		lastnameTxt.addKeyListener(keyHandler);
		emailTxt.addKeyListener(keyHandler);
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == okButton) {
			//	RegisterController reController = new RegisterController();

			} else if (event.getSource() == cancelButton) {
				System.exit(0);
			}

		}

	}
	
	private class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			char caracter = e.getKeyChar();
			
			if (e.getSource() == usernameTxt) {
				if (!esValido(caracter)) {
					String texto = "";
					for (int i = 0; i < usernameTxt.getText().length(); i++)
						if (esValido(new Character(usernameTxt.getText()
								.charAt(i))))
							texto += usernameTxt.getText().charAt(i);
					usernameTxt.setText(texto);
					getToolkit().beep();
				}
				if (usernameTxt.getText().length() == uslimite) {
					e.consume();
				}
			} else if (e.getSource() == passwordTxt) {				
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (passwordTxt.getPassword().length == passlimite) {
					e.consume();
				}
			} else if (e.getSource() == repasswordTxt) {
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (repasswordTxt.getPassword().length == repaslimite) {
					e.consume();
				}
			} else if (e.getSource() == firstnameTxt) {
				if (!esValido(caracter)) {
					String texto = "";
					for (int i = 0; i < firstnameTxt.getText().length(); i++)
						if (esValido(new Character(firstnameTxt.getText()
								.charAt(i))))
							texto += firstnameTxt.getText().charAt(i);
					firstnameTxt.setText(texto);
				}
				if (firstnameTxt.getText().length() == firslimite) {
					e.consume();
				}
			} else if (e.getSource() == lastnameTxt) {
				if (!esValido(caracter)) {
					String texto = "";
					for (int i = 0; i < lastnameTxt.getText().length(); i++)
						if (esValido(new Character(lastnameTxt.getText()
								.charAt(i))))
							texto += lastnameTxt.getText().charAt(i);
					lastnameTxt.setText(texto);
				}
				if (lastnameTxt.getText().length() == laslimite) {
					e.consume();
				}
			} else if (e.getSource() == emailTxt) {
				if (emailTxt.getText().length() == emailimite) {
					e.consume();
				}
			}
			
		}
		
		public boolean esValido(char caracter) {
			if (!(Character.isLetter(caracter) || caracter == ' ' || caracter == 8))
				return false;
			else
				return true;
		}

		@Override
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}

}
