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


public class Login extends JFrame {
	
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passLabel;
	private JPasswordField passText;
	private JButton aceptarBttn;
	private JButton cancelarBttn;

	public Login() {
		super("LOGIN");
		setLayout(new FlowLayout());
		
		userLabel = new JLabel("Usuario:");
		add(userLabel);
		userText = new JTextField(20);
		add(userText);
		final int userLimite = 10;
		
		passLabel = new JLabel("Password:");
		add(passLabel);
		passText = new JPasswordField(20);
		add(passText);
		final int passLimite = 10;
		
		aceptarBttn = new JButton("Aceptar");
		add(aceptarBttn);
		
		cancelarBttn = new JButton("Cancelar");
		add(cancelarBttn);
		
		ButtonHandler handler = new ButtonHandler();
		aceptarBttn.addActionListener(handler);
		cancelarBttn.addActionListener(handler);
		
		//INTRODUCE EL NOMBRE DE USUARIO
		userText.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				Character caracter = new Character(e.getKeyChar());
				if (!esValido(caracter)) {
					String texto = "";
					for (int i = 0; i < userText.getText().length(); i++)
						if (esValido(new Character(userText.getText()
								.charAt(i))))
							texto += userText.getText().charAt(i);
					userText.setText(texto);
					getToolkit().beep();
				}
				if (userText.getText().length() == userLimite) {
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
			public void keyPressed(KeyEvent arg0) {
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				
			}
		});
		
		//INTRODUCE EL PASSWORD
		passText.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (passText.getPassword().length == passLimite) {
					e.consume();
				}

			}

			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		
		
	}
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == aceptarBttn) {
				RegisterController reController = new RegisterController();

			} else if (event.getSource() == cancelarBttn) {
				System.exit(0);
			}

		}

	}
}
