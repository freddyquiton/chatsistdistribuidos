package chat.client.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import chat.client.controller.LogoutController;
import chat.client.exceptions.LogoutException;
import chat.client.threading.ServerManager;
import chat.client.threading.UserListWorker;
import chat.client.threading.client.ListenerWorker;
import chat.common.model.Session;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatRoomFrame extends JFrame {
	private ServerManager server;
	private UserListWorker userList;
	private JList list;
	private JTabbedPane tabbedPane;
	private String nick;
	private int listenPort;
	private ListenerWorker listener;
	private String url;
	private int serverPort;

	public ChatRoomFrame(ServerManager theServer, String theNick, int theListenPort, String theUrl, int theServerPort) {
		super("Chat Room");
		server = theServer;
		nick = theNick;
		listenPort = theListenPort;
		url = theUrl;
		serverPort = theServerPort;

		addWindowListener(new WindowAdapter() {			
			@Override
			public void windowClosing(WindowEvent e) {				
				exitApp();
			}
		});
		setBounds(100, 100, 918, 532);
		getContentPane().setLayout(new BorderLayout(20, 20));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 200, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblListaDeContactos = new JLabel("Lista de contactos");
		GridBagConstraints gbc_lblListaDeContactos = new GridBagConstraints();
		gbc_lblListaDeContactos.insets = new Insets(0, 0, 5, 0);
		gbc_lblListaDeContactos.gridx = 0;
		gbc_lblListaDeContactos.gridy = 0;
		panel.add(lblListaDeContactos, gbc_lblListaDeContactos);

		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {				
				try {
					Session theSession = (Session) list.getSelectedValue();					
					if (theSession != null) {
						String username = theSession.getUsername();						
						int index = searchTab(username);
						
						if (index == -1)
							tabbedPane.addTab(username, new ChatPanel(new Socket(
									theSession.getUrl(), listenPort), server, nick, theSession.getUsername()));
						else {
							//TODO alguna manera de ver si esta activo
							tabbedPane.setSelectedIndex(index);							
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		mnArchivo.add(mntmLogout);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp();
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutFrame about = new AboutFrame();
				about.setVisible(true);
			}
		});
		mnAyuda.add(mntmAcercaDe);

		userList = new UserListWorker(list, server, nick);
		userList.execute();
		listener = new ListenerWorker(tabbedPane, server, listenPort, nick);
		listener.execute();
	}
	
	private int searchTab(String title) {
		for(int i = 0; i < tabbedPane.getTabCount(); i++)
			if (tabbedPane.getTitleAt(i).equals(title))
				return i;
		return -1;
	}
	
	private void logout() {
		try {
			LogoutController logoutController = new LogoutController(
					server);
			logoutController.logout();
			userList.get();
			listener.stopListener();
			listener.get();
			for(int i = 0; i < tabbedPane.getTabCount(); i++) {
				System.out.println("Esperando");
				if (tabbedPane.getTabComponentAt(i) instanceof ChatPanel)
					((ChatPanel)tabbedPane.getTabComponentAt(i)).waitUnitlFinish();
			}
		} catch (LogoutException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ExecutionException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			dispose();
			LoginFrame loginFrame = new LoginFrame(server, url, serverPort, listenPort);
			loginFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			loginFrame.setVisible(true);
		}
	}

	private void exitApp() {
		try {
			LogoutController logoutController = new LogoutController(
					server);
			logoutController.logout();
			userList.get();
			listener.stopListener();
			listener.get();
			for(int i = 0; i < tabbedPane.getTabCount(); i++) {
				System.out.println("Esperando");
				if (tabbedPane.getTabComponentAt(i) instanceof ChatPanel)
					((ChatPanel)tabbedPane.getTabComponentAt(i)).waitUnitlFinish();
			}
		} catch (LogoutException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} catch (ExecutionException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		} finally {										
			dispose();
			System.exit(0);
		}
	}

}
