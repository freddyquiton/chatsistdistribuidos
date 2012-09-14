package chat.client.threading;

import chat.client.exceptions.NetworkException;
import chat.client.networking.ClientToServerManager;

public class ConnectionThread implements Runnable {
	private ServerCommandQueue queue;
	private ClientToServerManager connection;
	
	public ConnectionThread(ServerCommandQueue theQueue, ClientToServerManager conn) {
		queue = theQueue;
		connection = conn;
	}

	@Override
	public void run() {
		try {
			ServerCommand command;			
			do {
				command = queue.getCommand();
				command.execute(connection);			
			} while(!(command instanceof LogoutCommand));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NetworkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
