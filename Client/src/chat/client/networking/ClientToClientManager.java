package chat.client.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTabbedPane;

import chat.client.exceptions.NetworkException;
import chat.client.threading.ServerManager;
import chat.client.view.ChatPanel;
import chat.common.model.Session;
import chat.common.model.SessionList;

public class ClientToClientManager {
	private JTabbedPane panel;
	private ServerManager server;
	private int port;
	private ServerSocket listener;	
	private String myNick;

	public ClientToClientManager(JTabbedPane thePanel, ServerManager theServer,
			int thePort, String myNick) {
		panel = thePanel;
		server = theServer;
		port = thePort;		
		this.myNick = myNick;
	}

	public void runServer() throws NetworkException {
		try {
			listener = new ServerSocket(port);

			while (server.isRunning()) {
				System.out.println("Waiting for clients to chat...");

				waitForConnection();
			}
		} catch (IOException e) {
			throw new NetworkException(e.getMessage());
		}
		System.out.println("Terminando de escuchar por clientes");
	}

	private void waitForConnection() throws IOException {
		Socket connection;

		connection = listener.accept();
		String url = connection.getInetAddress().getHostAddress();
		try {
			SessionList list = server.getUserList();

			String nick = "";		
						
			for (Session session : list.getSessionList()) {				
				if (session.getUrl().equals(url)) {					
					nick = session.getUsername();
					break;
				}
			}

			panel.addTab(nick, new ChatPanel(connection, server, myNick, nick));
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void stopListening() throws NetworkException {
		try {
			listener.close();
		} catch (IOException e) {
			throw new NetworkException(e.getMessage());
		}
	}

}
