package chat.client.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

import chat.client.controller.RegisterController;
import chat.client.exceptions.RegisterException;
import chat.client.threading.ServerManager;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxt;
	private JPasswordField passwordTxt;
	private JPasswordField rePasswordTxt;
	private JTextField firstNameTxt;
	private JTextField lastNameTxt;
	private JTextField emailTxt;
	private RegisterController controller;
	private JLabel lblUserNameError;
	private JLabel lblPasswordError;
	private JLabel lblRepasswordError;
	private JLabel lblFirstNameError;
	private JLabel lblLastNameError;
	private JLabel lblEmailError;
	private JLabel lblError;

	/**
	 * Create the frame.
	 */
	public RegisterFrame(ServerManager theServer, String theUrl, int thePort) {
		setTitle("Registrar Usuario");
		controller = new RegisterController(theServer, theUrl, thePort);	
		setBounds(100, 100, 661, 403);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));
		
		JLabel lblRegistrarUsuario = new JLabel("Registrar Usuario");
		lblRegistrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRegistrarUsuario, BorderLayout.NORTH);
		
		JPanel panelBotton = new JPanel();
		panelBotton.setBackground(Color.WHITE);
		contentPane.add(panelBotton, BorderLayout.SOUTH);
		panelBotton.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.register(usernameTxt.getText(), 
							new String(passwordTxt.getPassword()),
							new String(rePasswordTxt.getPassword()), firstNameTxt.getText(),
							lastNameTxt.getText(), emailTxt.getText());
					JOptionPane.showMessageDialog(null, "Registro exitoso!, ahora podra usar el servicio de chat"
							, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE); 
				} catch (RegisterException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		btnRegister.setIcon(new ImageIcon(RegisterFrame.class.getResource("/chat/client/view/resources/registerIcon.png")));
		panelBotton.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancelar");
		panelBotton.add(btnCancel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		ImagePanel imagePanel = new ImagePanel(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/chat/client/view/resources/ico2.jpg")));
		panel.add(imagePanel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 150, 10};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 20};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label = new JLabel("Nombre de Usuario:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		
		usernameTxt = new JTextField();
		usernameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!controller.isValidUsername(usernameTxt.getText())) {
					lblUserNameError.setText("*");
					lblError.setText("El nombre de usuario es invalido");
				} else {
					lblUserNameError.setText("");
					lblError.setText("");
				}
			}
		});
		usernameTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameTxt.setColumns(20);
		GridBagConstraints gbc_usernameTxt = new GridBagConstraints();
		gbc_usernameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_usernameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTxt.gridx = 1;
		gbc_usernameTxt.gridy = 0;
		panel_1.add(usernameTxt, gbc_usernameTxt);
		
		lblUserNameError = new JLabel("");
		lblUserNameError.setForeground(Color.RED);
		GridBagConstraints gbc_lblUserNameError = new GridBagConstraints();
		gbc_lblUserNameError.insets = new Insets(0, 0, 5, 0);
		gbc_lblUserNameError.gridx = 2;
		gbc_lblUserNameError.gridy = 0;
		panel_1.add(lblUserNameError, gbc_lblUserNameError);
		
		JLabel label_1 = new JLabel("Contrase\u00F1a:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel_1.add(label_1, gbc_label_1);
		
		passwordTxt = new JPasswordField();
		passwordTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(!controller.isValidPassword(new String(passwordTxt.getPassword()))) {
					lblPasswordError.setText("*");
					lblError.setText("La contraseña es invalida");
				} else {
					lblPasswordError.setText("");
					lblError.setText("");
				}
			}
		});
		passwordTxt.setColumns(20);
		passwordTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_passwordTxt = new GridBagConstraints();
		gbc_passwordTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTxt.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTxt.gridx = 1;
		gbc_passwordTxt.gridy = 1;
		panel_1.add(passwordTxt, gbc_passwordTxt);
		
		lblPasswordError = new JLabel("");
		lblPasswordError.setForeground(Color.RED);
		GridBagConstraints gbc_lblPasswordError = new GridBagConstraints();
		gbc_lblPasswordError.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasswordError.gridx = 2;
		gbc_lblPasswordError.gridy = 1;
		panel_1.add(lblPasswordError, gbc_lblPasswordError);
		
		JLabel label_2 = new JLabel("Repetir contrase\u00F1a:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel_1.add(label_2, gbc_label_2);
		
		rePasswordTxt = new JPasswordField();
		rePasswordTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!controller.isRePasswordEqualToPassword(new String(passwordTxt.getPassword()),
														   new String(rePasswordTxt.getPassword()))) {
					lblRepasswordError.setText("*");
					lblError.setText("La contraseña es invalida");
				} else {
					lblRepasswordError.setText("");
					lblError.setText("");
				}
			}
		});
		rePasswordTxt.setColumns(20);
		rePasswordTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_rePasswordTxt = new GridBagConstraints();
		gbc_rePasswordTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_rePasswordTxt.insets = new Insets(0, 0, 5, 5);
		gbc_rePasswordTxt.gridx = 1;
		gbc_rePasswordTxt.gridy = 2;
		panel_1.add(rePasswordTxt, gbc_rePasswordTxt);
		
		lblRepasswordError = new JLabel("");
		lblRepasswordError.setForeground(Color.RED);
		GridBagConstraints gbc_lblRepasswordError = new GridBagConstraints();
		gbc_lblRepasswordError.insets = new Insets(0, 0, 5, 0);
		gbc_lblRepasswordError.gridx = 2;
		gbc_lblRepasswordError.gridy = 2;
		panel_1.add(lblRepasswordError, gbc_lblRepasswordError);
		
		JLabel label_3 = new JLabel("Nombre(s):");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel_1.add(label_3, gbc_label_3);
		
		firstNameTxt = new JTextField();
		firstNameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!controller.isValidFirstName(firstNameTxt.getText())) {
					lblFirstNameError.setText("*");
					lblError.setText("El nombre de usuario es invalido");
				} else {
					lblFirstNameError.setText("");
					lblError.setText("");
				}
			}
		});
		firstNameTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		firstNameTxt.setColumns(30);
		GridBagConstraints gbc_firstNameTxt = new GridBagConstraints();
		gbc_firstNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameTxt.gridx = 1;
		gbc_firstNameTxt.gridy = 3;
		panel_1.add(firstNameTxt, gbc_firstNameTxt);
		
		lblFirstNameError = new JLabel("");
		lblFirstNameError.setForeground(Color.RED);
		GridBagConstraints gbc_lblFirstNameError = new GridBagConstraints();
		gbc_lblFirstNameError.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstNameError.gridx = 2;
		gbc_lblFirstNameError.gridy = 3;
		panel_1.add(lblFirstNameError, gbc_lblFirstNameError);
		
		JLabel label_4 = new JLabel("Apellido(s):");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 4;
		panel_1.add(label_4, gbc_label_4);
		
		lastNameTxt = new JTextField();
		lastNameTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!controller.isValidLastName(lastNameTxt.getText())) {
					lblLastNameError.setText("*");
					lblError.setText("El nombre de usuario es invalido");
				} else {
					lblLastNameError.setText("");
					lblError.setText("");
				}
			}
		});
		lastNameTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lastNameTxt.setColumns(30);
		GridBagConstraints gbc_lastNameTxt = new GridBagConstraints();
		gbc_lastNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameTxt.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameTxt.gridx = 1;
		gbc_lastNameTxt.gridy = 4;
		panel_1.add(lastNameTxt, gbc_lastNameTxt);
		
		lblLastNameError = new JLabel("");
		lblLastNameError.setForeground(Color.RED);
		GridBagConstraints gbc_lblLastNameError = new GridBagConstraints();
		gbc_lblLastNameError.insets = new Insets(0, 0, 5, 0);
		gbc_lblLastNameError.gridx = 2;
		gbc_lblLastNameError.gridy = 4;
		panel_1.add(lblLastNameError, gbc_lblLastNameError);
		
		JLabel label_5 = new JLabel("E-mail:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.EAST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 5;
		panel_1.add(label_5, gbc_label_5);
		
		emailTxt = new JTextField();
		emailTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!controller.isValidEmail(emailTxt.getText())) {
					lblEmailError.setText("*");
					lblError.setText("El nombre de usuario es invalido");
				} else {
					lblEmailError.setText("");
					lblError.setText("");
				}
			}
		});
		emailTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailTxt.setColumns(50);
		GridBagConstraints gbc_emailTxt = new GridBagConstraints();
		gbc_emailTxt.insets = new Insets(0, 0, 5, 5);
		gbc_emailTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTxt.gridx = 1;
		gbc_emailTxt.gridy = 5;
		panel_1.add(emailTxt, gbc_emailTxt);
		
		lblEmailError = new JLabel("");
		lblEmailError.setForeground(Color.RED);
		GridBagConstraints gbc_lblEmailError = new GridBagConstraints();
		gbc_lblEmailError.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmailError.gridx = 2;
		gbc_lblEmailError.gridy = 5;
		panel_1.add(lblEmailError, gbc_lblEmailError);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 3;
		gbc_lblError.insets = new Insets(0, 0, 0, 5);
		gbc_lblError.gridx = 0;
		gbc_lblError.gridy = 6;
		panel_1.add(lblError, gbc_lblError);
	}

}
