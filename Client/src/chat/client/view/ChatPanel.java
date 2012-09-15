package chat.client.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChatPanel extends JPanel {
	private GridBagLayout layout;
	private GridBagConstraints constraits;
	private JTextArea txtChat;
	private JTextArea txtMessage;
	private JLabel labelWrite;
	private JButton buttonSend;
	
	public ChatPanel() {		
		super();
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraits = new GridBagConstraints();
		
		txtChat = new JTextArea(10, 30);
		txtMessage = new JTextArea(1, 30);
		buttonSend = new JButton("Enviar");
		labelWrite = new JLabel("Escribe el mensaje aca:");
		
		constraits.fill = GridBagConstraints.BOTH;
		addComponent(buttonSend, 11, 9, 1, 1);
		
		constraits.fill = GridBagConstraints.BOTH;
		addComponent(labelWrite, 10, 0, 1, 1);
		
		constraits.fill = GridBagConstraints.BOTH;
		constraits.weightx = 10;
		constraits.weighty = 0;
		addComponent(txtMessage, 11, 0, 9, 1);
		
		constraits.fill = GridBagConstraints.BOTH;
		constraits.weightx = 10;
		constraits.weighty = 10;
		addComponent(txtChat, 0, 0, 10, 10);
		
	}
	
	private void addComponent(Component component, int row, int column, int width, int height) {
		constraits.gridx = column;
		constraits.gridy = row;
		constraits.gridwidth = width;
		constraits.gridheight = height;
		layout.setConstraints(component, constraits);
		add(component);
	}

}
