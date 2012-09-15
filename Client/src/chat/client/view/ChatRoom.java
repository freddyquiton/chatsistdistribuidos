package chat.client.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;

public class ChatRoom extends JFrame {
	private GridBagLayout layout;
	private GridBagConstraints constraits;
	private JTabbedPane tabs;
	private JLabel labelRooms;
	private JList<String> contactsList;

	public ChatRoom()  {
		super("Sala de chat");
		
		layout = new GridBagLayout();
		setLayout(layout);
		constraits = new GridBagConstraints();
		
		tabs = new JTabbedPane();
		labelRooms = new JLabel("Contactos");
		contactsList = new JList<>();		

		constraits.fill = GridBagConstraints.BOTH;
		addComponent(labelRooms, 0, 5, 2, 1);
		
		constraits.fill = GridBagConstraints.BOTH;
		constraits.weightx = 10;
		constraits.weighty = 1;
		addComponent(tabs, 0, 0, 5, 7);
				
		constraits.fill = GridBagConstraints.BOTH;
		constraits.weightx = 5;
		constraits.weighty = 10;
		addComponent(contactsList, 1, 5, 2, 6);
		
		tabs.addTab("Cliente1", null, new ChatPanel(), "Cliente 1 :)");
		tabs.addTab("Cliente2", null, new ChatPanel(), "Cliente 2 :)");		
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
