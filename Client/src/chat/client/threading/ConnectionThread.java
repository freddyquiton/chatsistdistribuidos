package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;
import chat.common.tools.Messages;

public class ConnectionThread implements Runnable {
	private ServerCommandQueue queue;
	private ClientToServerManager connection;
	private MessageQueue messages;
	
	public ConnectionThread(ServerCommandQueue theQueue, MessageQueue theMessages, ClientToServerManager conn) {
		queue = theQueue;
		connection = conn;
		messages = theMessages;
	}

	@Override
	public void run() {
		ServerCommand command = null;
		System.out.println("Iniciando conexión con el servidor");
		try {			
			do {
				try {
					command = queue.getCommand();
					command.execute(connection);
					messages.addMessage(Messages.OK);
				} catch(ServerException networkException) {
					messages.addMessage(networkException.getMessage());
				}
			} while(!(command instanceof LogoutCommand));
			System.out.println("Terminando conexion....");
		} catch (InterruptedException e) {
			
		}
		
	}

}
