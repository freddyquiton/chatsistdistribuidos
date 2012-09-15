package chat.client.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private final int userLimite = 10;
	private final int passLimite = 10;
	private JLabel userLabel;
	private JTextField userText;
	private JLabel passLabel;
	private JPasswordField passText;
	private JButton aceptarBtn;
	private JButton cancelarBtn;
	private JLabel registerLabel;
	private JButton registerButton;
	private JPanel panel1;
	private JPanel panel2;
	
	public Login() {
		super("LOGIN");
		setLayout(new GridLayout(1, 2));
		setResizable(false);
		
		panel1 = new JPanel();
		add(panel1, 0);
		panel2 = new JPanel();
		add(panel2, 1);
		
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());

		userLabel = new JLabel("Usuario:");
		panel1.add(userLabel);
		userText = new JTextField(20);
		panel1.add(userText);

		passLabel = new JLabel("Password:");
		panel1.add(passLabel);
		passText = new JPasswordField(20);
		panel1.add(passText);

		aceptarBtn = new JButton("Aceptar");
		panel1.add(aceptarBtn);

		cancelarBtn = new JButton("Cancelar");
		panel1.add(cancelarBtn);
		
		registerLabel = new JLabel("No tienes cuenta?, Registrate!!!!");
		panel2.add(registerLabel);
		
		registerButton = new JButton("Registrarse");
		panel2.add(registerButton);

		ButtonHandler handler = new ButtonHandler();
		aceptarBtn.addActionListener(handler);
		cancelarBtn.addActionListener(handler);

		KeyHandler keyHandler = new KeyHandler();
		userText.addKeyListener(keyHandler);
		passText.addKeyListener(keyHandler);
		
		setSize(500, 300);
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == aceptarBtn) {
				// RegisterController reController = new RegisterController();

			} else if (event.getSource() == cancelarBtn) {
				System.exit(0);
			}

		}

	}

	private class KeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getSource() == userText) {
				Character caracter = new Character(e.getKeyChar());
				
				if (!esValido(caracter)) {
					String texto = "";
					for (int i = 0; i < userText.getText().length(); i++)
						if (esValido(new Character(userText.getText().charAt(i))))
							texto += userText.getText().charAt(i);
					userText.setText(texto);
					getToolkit().beep();
				}
				if (userText.getText().length() == userLimite) {
					e.consume();
				}
			} else if (e.getSource() == passText) {
				char caracter = e.getKeyChar();
				
				if (((caracter < '0') || (caracter > '9'))
						&& (caracter != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (passText.getPassword().length == passLimite) {
					e.consume();
				}
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
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent arg0) {}
	}
}
