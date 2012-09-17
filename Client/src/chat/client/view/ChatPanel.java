package chat.client.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.client.threading.MessageQueue;
import chat.client.threading.ServerManager;
import chat.client.threading.client.ConnectionWorker;

public class ChatPanel extends JPanel {
	private JTextArea textArea;
	private JTextArea textMessages;
	private MessageQueue messages;
	private String nick;

	/**
	 * Create the panel.
	 */
	public ChatPanel(Socket connection, ServerManager theServer, String theNick) {
		messages = new MessageQueue();
		nick = theNick;
		
		setLayout(new BorderLayout(10, 10));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[] {0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		GridBagConstraints gbc_lblNombreDeUsuario = new GridBagConstraints();
		gbc_lblNombreDeUsuario.anchor = GridBagConstraints.WEST;
		gbc_lblNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeUsuario.gridx = 0;
		gbc_lblNombreDeUsuario.gridy = 0;
		panel.add(lblNombreDeUsuario, gbc_lblNombreDeUsuario);
		
		JLabel labelUserName = new JLabel("");
		GridBagConstraints gbc_labelUserName = new GridBagConstraints();
		gbc_labelUserName.insets = new Insets(0, 0, 5, 0);
		gbc_labelUserName.gridx = 1;
		gbc_labelUserName.gridy = 0;
		panel.add(labelUserName, gbc_labelUserName);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 0, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 2;
		panel.add(lblEmail, gbc_lblEmail);
		
		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 2;
		panel.add(label_1, gbc_label_1);
		
		textArea = new JTextArea();
		add(new JScrollPane(textArea), BorderLayout.CENTER);		
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblEscribeAca = new JLabel("Escribe aca:");
		GridBagConstraints gbc_lblEscribeAca = new GridBagConstraints();
		gbc_lblEscribeAca.anchor = GridBagConstraints.WEST;
		gbc_lblEscribeAca.insets = new Insets(0, 0, 5, 5);
		gbc_lblEscribeAca.gridx = 0;
		gbc_lblEscribeAca.gridy = 0;
		panel_1.add(lblEscribeAca, gbc_lblEscribeAca);
		
		textMessages = new JTextArea();
		textMessages.setRows(1);
		GridBagConstraints gbc_textMessages = new GridBagConstraints();
		gbc_textMessages.insets = new Insets(0, 0, 0, 5);
		gbc_textMessages.fill = GridBagConstraints.BOTH;
		gbc_textMessages.gridx = 0;
		gbc_textMessages.gridy = 1;		
		panel_1.add(new JScrollPane(textMessages), gbc_textMessages);
		
		JButton btnSend = new JButton("Enviar");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					messages.addMessage(nick + textMessages.getText());
				} catch (InterruptedException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 1;
		panel_1.add(btnSend, gbc_btnSend);
		
		ConnectionWorker worker = new ConnectionWorker(connection, theServer, textArea, messages);		
		worker.execute();
	}
	
	

}
