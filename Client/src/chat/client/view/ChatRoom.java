package chat.client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import chat.client.controller.RegisterController;

public class ChatRoom extends JFrame{

	private JMenuBar Menuchat;
	private JTextArea writeTxt;
	private JTextArea writelate;
	private String demo;
	private JButton envJButton;
	private JTextArea contactos;
	
	public ChatRoom() {
		super("CHAT ROOM");
		setLayout(new FlowLayout());
		
		// MENU 
		Menuchat = new JMenuBar();
		setJMenuBar(Menuchat);

		JMenu filMenu = new JMenu("File");
		JMenu ediMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");
		JMenu helpMenu = new JMenu("Help");
		
		Menuchat.add(filMenu);
		Menuchat.add(ediMenu);
		Menuchat.add(viewMenu);
		Menuchat.add(helpMenu);
		
		JMenuItem newAct = new JMenuItem("New");
		filMenu.add(newAct);
		
		JMenuItem openAct = new JMenuItem("Open");
		filMenu.add(openAct);
		
		filMenu.addSeparator();
		
		JMenuItem exitAct = new JMenuItem("Exit");
		filMenu.add(exitAct);
		
		//MENSAJES 
		Box box = Box.createHorizontalBox();
		String demo = "";
		writeTxt = new JTextArea(demo,5,10);
		box.add(new JScrollPane(writeTxt));
		envJButton = new JButton("Enviar");
		box.add(envJButton);
		
		writelate = new JTextArea(20,20);
	    writelate.setEditable(false);
	    box.add(new JScrollPane(writelate));
	    
		envJButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						writelate.setText(writeTxt.getText());
					}
				});
	    /*writelate = new JTextArea(20,20);
	    writelate.setEditable(false);
	    box.add(new JScrollPane(writelate));*/
	    add(box);
		
	}

}
