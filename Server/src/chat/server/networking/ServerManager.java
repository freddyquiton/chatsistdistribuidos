package chat.server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import chat.common.model.SessionList;
import chat.server.model.UserDatabase;
import chat.server.threading.ConnectionThread;

public class ServerManager {
	private ServerSocket server;
	private UserDatabase database;
	private SessionList sessions;
	
	public ServerManager(int port, UserDatabase theDatabase, SessionList theSessions)
	{
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		database = theDatabase;
		sessions = theSessions;
	}
	
	public void runServer()
	{
		try
		{
			System.out.println("Waiting connection...");
			while(true)
			{
				waitForConnection();
			}
		} catch (IOException ioException)
		{
			ioException.printStackTrace();
		}
		
	}

	private void waitForConnection() throws IOException {
		Socket connection;
		
		connection = server.accept();
		System.out.println("Connection accepted from... " + connection.getInetAddress().getHostAddress());
		Thread thread = new Thread(new ConnectionThread(connection, database, sessions));
		
		thread.start();
	}

}
