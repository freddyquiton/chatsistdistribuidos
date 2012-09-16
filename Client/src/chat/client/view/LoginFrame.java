package chat.client.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.client.controller.LoginController;
import chat.client.exceptions.LoginException;
import chat.client.threading.ServerManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	private LoginController controller;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	
	public LoginFrame(ServerManager theServer, String theUrl, int thePort) {
		setResizable(false);
		controller = new LoginController(theServer, theUrl, thePort);
		setTitle("Cat Chat -Login");
		setBounds(100, 100, 647, 409);
		getContentPane().setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(15, 10));
		
		JLabel lblBienvenidosACatchat = new JLabel("Bienvenidos a CatChat");
		lblBienvenidosACatchat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBienvenidosACatchat.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBienvenidosACatchat, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.login(txtUsername.getText(), new String(txtPassword.getPassword()));
					JOptionPane.showMessageDialog(null, "Login exitoso!, ahora podra usar el servicio de chat"
							, "Login exitoso", JOptionPane.INFORMATION_MESSAGE);
				} catch (LoginException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel_2.add(btnLogin);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] {0, 0};
		gbl_panel_3.rowHeights = new int[] {0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		GridBagConstraints gbc_lblNombreDeUsuario = new GridBagConstraints();
		gbc_lblNombreDeUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeUsuario.gridx = 0;
		gbc_lblNombreDeUsuario.gridy = 0;
		panel_3.add(lblNombreDeUsuario, gbc_lblNombreDeUsuario);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 0;
		panel_3.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
		gbc_lblContrasea.anchor = GridBagConstraints.EAST;
		gbc_lblContrasea.insets = new Insets(0, 0, 0, 5);
		gbc_lblContrasea.gridx = 0;
		gbc_lblContrasea.gridy = 1;
		panel_3.add(lblContrasea, gbc_lblContrasea);
		
		txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		panel_3.add(txtPassword, gbc_txtPassword);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[] {0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNoTienesCuenta = new JLabel("No tienes cuenta? REGISTRATE!");
		lblNoTienesCuenta.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_lblNoTienesCuenta = new GridBagConstraints();
		gbc_lblNoTienesCuenta.insets = new Insets(0, 0, 5, 0);
		gbc_lblNoTienesCuenta.gridx = 0;
		gbc_lblNoTienesCuenta.gridy = 0;
		panel_1.add(lblNoTienesCuenta, gbc_lblNoTienesCuenta);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		GridBagConstraints gbc_btnRegistrarse = new GridBagConstraints();
		gbc_btnRegistrarse.gridx = 0;
		gbc_btnRegistrarse.gridy = 1;
		panel_1.add(btnRegistrarse, gbc_btnRegistrarse);

	}

}