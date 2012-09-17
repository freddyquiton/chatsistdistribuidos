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
	private ClientToClientManager client;
	
	public ListenerWorker(JTabbedPane thePanel, ServerManager theServer, int thePort) {
		panel = thePanel;
		server = theServer;
		port = thePort;
		client = new ClientToClientManager(panel, server, port);
	}

	@Override
	protected Void doInBackground() {		
		try {
			client.runServer();			
		} catch (NetworkException e) {			
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	protected void done() {
		try {
			client.stopListening();
		} catch (NetworkException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void stopListener() {
		try {
			client.stopListening();
		} catch (NetworkException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	

}
