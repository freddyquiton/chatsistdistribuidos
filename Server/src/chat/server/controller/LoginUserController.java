package chat.server.controller;

import chat.common.model.Session;
import chat.common.model.SessionList;
import chat.common.model.User;
import chat.server.exceptions.LoginUserException;
import chat.server.model.UserDatabase;

public class LoginUserController {
	private UserDatabase database;
	private SessionList sessions;
	
	public LoginUserController(UserDatabase theDatabase, SessionList list) {
		database = theDatabase;
		sessions = list;
	}
	
	public void loginUser(String username, String password, String url) throws LoginUserException {
		User theUser = database.searchUser(username);
		
		if (theUser == null)
			throw new LoginUserException("Incorrect username or password");
		//TODO: Realizar validaciones en el formato de datos		
		if (!theUser.checkPassword(password))
			throw new LoginUserException("Incorrect username or password");
		
		if(sessions.getSession(username) != null)
			throw new LoginUserException("The user is already logged in another machine");
		
		sessions.addSession(new Session(username, url));
		System.out.println("User logged: " + sessions.getSession(username).getUsername());
	}	

}
