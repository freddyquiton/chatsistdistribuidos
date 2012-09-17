package chat.client.threading.client;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import chat.client.exceptions.NetworkException;
import chat.client.networking.ClientToClientManager;
import chat.client.threading.ServerManager;

public class ListenerWorker extends SwingWorker<Void, Void> {
	private JTabbedPane panel;
	private ServerManager server;
	private int port;	
	
	public ListenerWorker(JTabbedPane thePanel, ServerManager theServer, int thePort) {
		panel = thePanel;
		server = theServer;
		port = thePort;
	}

	@Override
	protected Void doInBackground() {		
		try {
			ClientToClientManager client = new ClientToClientManager(panel, server, port);
			
			client.runServer();			
		} catch (NetworkException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	
	

}
