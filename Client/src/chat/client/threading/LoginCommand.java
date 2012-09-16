package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;

public class LoginCommand implements ServerCommand {
	private String username;
	private String password;
	
	public LoginCommand(String theUsername, String thePassword) {
		username = theUsername;
		password = thePassword;
	}

	@Override
	public void execute(ClientToServerManager connection)
			throws ServerException {
		connection.login(username, password);
	}

}
