package chat.client.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;

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

public class ChatRoomFrame extends JFrame {
	private ServerManager server;
	private UserListWorker userList;
	private JList list;
	private JTabbedPane tabbedPane;

	public ChatRoomFrame(ServerManager theServer) {
		super("Chat Room");
		server = theServer;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					LogoutController logoutController = new LogoutController(
							server);
					logoutController.logout();
				} catch (LogoutException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),
							"Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					while (!userList.isDone())
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null,
									e1.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					dispose();
					System.exit(0);
				}
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
				if (!e.getValueIsAdjusting()) {
					Session theSession = (Session) list.getSelectedValue();
					try {
						if (theSession != null)
							tabbedPane.addTab("test", new ChatPanel(new Socket(
								theSession.getUrl(), 12346), server, "Minick"));
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(),
								"Error", JOptionPane.ERROR_MESSAGE);
					}
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
		mnArchivo.add(mntmLogout);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mnAyuda.add(mntmAcercaDe);

		userList = new UserListWorker(list, server);
		userList.execute();
		ListenerWorker listener = new ListenerWorker(tabbedPane, server, 12346);
		listener.execute();
	}

}
