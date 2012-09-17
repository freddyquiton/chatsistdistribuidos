package chat.client.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTabbedPane;

import chat.client.exceptions.NetworkException;
import chat.client.threading.ServerManager;
import chat.client.view.ChatPanel;

public class ClientToClientManager {
	private JTabbedPane panel;
	private ServerManager server;
	private int port;
	private ServerSocket listener;

	public ClientToClientManager(JTabbedPane thePanel, ServerManager theServer,
			int thePort) {
		panel = thePanel;
		server = theServer;
		port = thePort;
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

		panel.addTab("test", new ChatPanel(connection, server, "Minick"));
	}
	
	public void stopListening() throws NetworkException {
		try {
			listener.close();
		} catch (IOException e) {
			throw new NetworkException(e.getMessage());
		}
	}

}
