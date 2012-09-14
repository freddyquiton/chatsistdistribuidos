package chat.client.threading;

import chat.client.exceptions.NetworkException;
import chat.client.networking.ClientToServerManager;

public class ServerManager {
	private ServerCommandQueue queue;
	
	public ServerManager() {
		queue = new ServerCommandQueue();
	}
	
	public void run(String url, int port) throws NetworkException {
		ClientToServerManager conn = new ClientToServerManager(url, port);
		Thread thread = new Thread(new ConnectionThread(queue, conn));
		
		thread.start();
	}
	
	public void registerUser(String username, String password,
			String rePassword, String firstName,
			String lastName, String email) throws InterruptedException {
		queue.addCommand(new RegisterCommand(username, password, 
				rePassword, firstName, lastName, email));
	}
}
