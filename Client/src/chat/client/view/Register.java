package chat.client.view;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField lastntameTxt;
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

		passwordLabel = new JLabel("Contraseña:");
		add(passwordLabel);
		passwordTxt = new JPasswordField(20);
		add(passwordTxt);

		repasswordLabel = new JLabel("Confirme Contraseña:");
		add(repasswordLabel);
		repasswordTxt = new JPasswordField(20);
		add(repasswordTxt);

		firstnameLabel = new JLabel("Nombre(s):");
		add(firstnameLabel);
		firstnameTxt = new JTextField(20);
		add(firstnameTxt);

		lastnameLabel = new JLabel("Apellido(s):");
		add(lastnameLabel);
		lastntameTxt = new JTextField(20);
		add(lastntameTxt);

		emailLabel = new JLabel("E-mail:");
		add(emailLabel);
		emailTxt = new JTextField(20);
		add(emailTxt);

		okButton = new JButton("Registrar");
		add(okButton);

		cancelButton = new JButton("Cancelar");
		add(cancelButton);

		ButtonHandler handler = new ButtonHandler();
		okButton.addActionListener(handler);
		cancelButton.addActionListener(handler);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == okButton) {
				RegisterController reController = new RegisterController();
				

			} else if (event.getSource() == cancelButton) {
				// cerrar ventana
			}

		}

	}

}
