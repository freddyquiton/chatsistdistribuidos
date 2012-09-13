package chat.server.controller;

import chat.common.model.User;
import chat.server.exceptions.RegisterUserException;
import chat.server.exceptions.UserDatabaseException;
import chat.server.model.UserDatabase;

public class RegisterUserController {
	private UserDatabase database;
	
	
	public RegisterUserController(UserDatabase theDatabase)
	{
		database = theDatabase;
	}
	
	public void RegisterUser(String userName, String password, String rePassword, 
							String firstName, String lastName, String email) throws RegisterUserException
	{
		if(database.searchUser(userName) != null)
			throw new RegisterUserException("The user already exists");
		//TODO: Realizar validaciones en el formato de datos
		if (!password.equals(rePassword))
			throw new RegisterUserException("The passwords are mismatched");
		
		User newUser = new User(userName, password, firstName, lastName, email);
		if(!database.addUser(newUser))
			throw new RegisterUserException("Error adding user");
		System.out.println("User registered: " + database.searchUser(userName).getUsername());
		try {
			database.saveData();
		} catch (UserDatabaseException e) {
			System.err.println(e.getMessage());
			throw new RegisterUserException(e.getMessage());
		}
	}
}
