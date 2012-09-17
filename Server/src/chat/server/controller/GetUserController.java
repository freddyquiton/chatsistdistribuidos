package chat.server.controller;

import chat.common.model.User;
import chat.server.exceptions.GetUserException;
import chat.server.model.UserDatabase;

public class GetUserController {
	private UserDatabase database;
	
	public GetUserController(UserDatabase theDatabase) {
		database = theDatabase;
	}
	
	public User getUser(String username) throws GetUserException {
		//TODO: Validar nombre de usuario
		User user = database.searchUser(username);
		
		if (user == null)
			throw new GetUserException("The user doesn't exists");
		
		return user;
	}

}
