package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;
import chat.common.model.User;

public class GetUserCommand implements ServerCommand {
	private String username;
	private User user;
	private boolean ready;
	
	public GetUserCommand(String theUsername) {
		username = theUsername;
		user = null;
		ready = false;
	}

	@Override
	public void execute(ClientToServerManager connection)
			throws ServerException {
		user = connection.getUser(username);
		ready = true;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	public User getUser() {
		return user;
	}
	
	
}
