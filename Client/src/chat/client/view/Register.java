package chat.client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chat.client.controller.RegisterController;

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

	public Register() {

		super("REGISTRAR USUARIO");
		setLayout(new FlowLayout());

		usernameLabel = new JLabel("Nombre de Usuario:");
		add(usernameLabel);
		usernameTxt = new JTextField(20);
		add(usernameTxt);
		final int uslimite = 10;

		passwordLabel = new JLabel("Contrase�a:");
		add(passwordLabel);
		passwordTxt = new JPasswordField(20);
		add(passwordTxt);
		final int passlimite = 10;

		repasswordLabel = new JLabel("Confirme Contrase�a:");
		add(repasswordLabel);
		repasswordTxt = new JPasswordField(20);
		add(repasswordTxt);
		final int repaslimite = 10;

		firstnameLabel = new JLabel("Nombre(s):");
		add(firstnameLabel);
		firstnameTxt = new JTextField(20);
		add(firstnameTxt);
		final int firslimite = 20;

		lastnameLabel = new JLabel("Apellido(s):");
		add(lastnameLabel);
		lastnameTxt = new JTextField(20);
		add(lastnameTxt);
		final int laslimite = 20;

		emailLabel = new JLabel("E-mail:");
		add(emailLabel);
		emailTxt = new JTextField(20);
		add(emailTxt);
		final int emailimite = 15;

		okButton = new JButton("Registrar");
		add(okButton);

		cancelButton = new JButton("Cancelar");
		add(cancelButton);

		ButtonHandler handler = new ButtonHandler();
		okButton.addActionListener(handler);
		cancelButton.addActionListener(handler);

		// INTRODUCE EL NOMBRE DE USUARIO
		usernameTxt.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				Character caracter = new Character(e.getKeyChar());
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

			}

			public boolean esValido(Character caracter) {
				char c = caracter.charValue();
				if (!(Character.isLetter(c) || c == ' ' || c == 8))
					return false;
				else
					return true;
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		// INTRODUCE EL PASSWORD
		passwordTxt.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (passwordTxt.getPassword().length == passlimite) {
					e.consume();
				}

			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		// CONFIRMAR EL PASSWORD
		repasswordTxt.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (repasswordTxt.getPassword().length == repaslimite) {
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// COMPARAR LOS PASSWORD
			boolean PassCorrecto(String passwordTxt, String repasswordTxt) {
				boolean valor = true;
				if (passwordTxt.equals(repasswordTxt)) {
					valor = false;
				} else {
					return valor;
				 	
				}
			    return valor;
			   
			}
		});

		// EL PRIMER NOMBRE
		firstnameTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				Character caracter = new Character(e.getKeyChar());
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

			}

			public boolean esValido(Character caracter) {
				char c = caracter.charValue();
				if (!(Character.isLetter(c) || c == ' ' || c == 8))
					return false;
				else
					return true;
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		// INTRODUCIR EL APELLIDO
		lastnameTxt.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				Character caracter = new Character(e.getKeyChar());
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
			}

			public boolean esValido(Character caracter) {
				char c = caracter.charValue();
				if (!(Character.isLetter(c) || c == ' ' || c == 8))
					return false;
				else
					return true;
			}

			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		// INTRODUCE EL CORREO ELECTRONICO

		emailTxt.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (emailTxt.getText().length() == emailimite) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == okButton) {
				RegisterController reController = new RegisterController();

			} else if (event.getSource() == cancelButton) {
				System.exit(0);
			}

		}

	}

}
