package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;
import chat.common.model.SessionList;
import chat.common.tools.Messages;

public class ServerManager {
	private ServerCommandQueue queue;
	private MessageQueue messages;
	private boolean running;
	
	public ServerManager() {
		queue = new ServerCommandQueue();
		messages = new MessageQueue();
		running = false;
	}
	
	public void run(String url, int port) throws ServerException {
		if (running)
			return;
		
		ClientToServerManager conn = new ClientToServerManager(url, port);
		Thread thread = new Thread(new ConnectionThread(queue, messages, conn));
		
		thread.start();
		running = true;
	}
	
	public void registerUser(String username, String password,
			String rePassword, String firstName,
			String lastName, String email) throws InterruptedException, ServerException {
		queue.addCommand(new RegisterCommand(username, password, 
				rePassword, firstName, lastName, email));
		String message = messages.getMessage();
		if (!message.equals(Messages.OK)) {
			throw new ServerException(message);
		}
	}
	
	public void loginUser(String username, String password) throws InterruptedException, ServerException {		
		queue.addCommand(new LoginCommand(username, password));
		String message = messages.getMessage();		
		if (!message.equals(Messages.OK)) {
			throw new ServerException(message);
		}
	}
	
	public void logout() throws InterruptedException, ServerException {
		queue.addCommand(new LogoutCommand());
		String message = messages.getMessage();		
		if (!message.equals(Messages.OK)) {
			throw new ServerException(message);
		}
		running = false;
	}
	
	public SessionList getUserList() throws InterruptedException, ServerException {
		UserListCommand command = new UserListCommand();
		
		queue.addCommand(command);
		
		String message = messages.getMessage();		
		
		if (!message.equals(Messages.OK)) {
			throw new ServerException(message);
		}
		
		while(!command.isReady()) {
			Thread.sleep(100);			
		}
		
		return command.getList();
	}
	
	public boolean isRunning() {
		return running;
	}
}
