package chat.server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import chat.server.threading.ConnectionThread;

public class ServerManager {
	ServerSocket server;
	
	public ServerManager(int port)
	{
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		Thread thread = new Thread(new ConnectionThread(connection));
		
		thread.start();
	}

}
