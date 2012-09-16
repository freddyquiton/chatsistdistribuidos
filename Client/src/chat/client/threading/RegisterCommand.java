package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;

public class RegisterCommand implements ServerCommand {
	private String username;
	private String password;
	private String rePassword;
	private String firstName;
	private String lastName;
	private String email;
	
	public RegisterCommand(String theUsername, String thePassword,
						String theRePassword, String theFirstName,
						String theLastName, String theEmail) {
		username = theUsername;
		password = thePassword;
		rePassword = theRePassword;
		firstName = theFirstName;
		lastName = theLastName;
		email = theEmail;
	}

	@Override
	public void execute(ClientToServerManager connection) throws ServerException {
		connection.register(username, password, rePassword, firstName, lastName, email);
	}

}
