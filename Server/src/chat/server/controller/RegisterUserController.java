package chat.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import chat.common.model.User;
import chat.server.exceptions.RegisterUserException;
import chat.server.model.UserDatabase;

public class RegisterUserController {
	public static final String userDatabaseFile = "users.dat";
	private UserDatabase dataBase;
	private ObjectInputStream input;
	
	public RegisterUserController() throws RegisterUserException
	{
		if (existFileDatabase())
			readDataBaseFile();
		else
			dataBase = new UserDatabase();
	}
	
	private boolean existFileDatabase()
	{
		File file = new File(userDatabaseFile);
		
		return file.exists();
	}
	
	public void RegisterUser(String userName, String password, String rePassword, 
							String firstName, String lastName, String email) throws RegisterUserException
	{
		if(dataBase.searchUser(userName) != null)
			throw new RegisterUserException("The user already exists");
		//TODO: Realizar validaciones en el formato de datos
		if (!password.equals(rePassword))
			throw new RegisterUserException("The passwords are mismatched");
		
		User newUser = new User(userName, password, firstName, lastName, email);
		if(!dataBase.addUser(newUser))
			throw new RegisterUserException("Error adding user");
		saveDataBaseFile();
	}
	
	private void readDataBaseFile() throws RegisterUserException
	{
		try {
			input = new ObjectInputStream(new FileInputStream(userDatabaseFile));
			
			dataBase = (UserDatabase)input.readObject();
		} catch(IOException ioException)
		{
			System.err.println("Error reading the file");
			throw new RegisterUserException("Error reading the file");
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Error reading the database");
			throw new RegisterUserException("Error reading the user database");
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException ioException) {
				throw new RegisterUserException("Error closing the file");
			}
		}
	}
	
	private void saveDataBaseFile() throws RegisterUserException
	{
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new FileOutputStream(userDatabaseFile));
			
			output.writeObject(dataBase);
		}  catch(IOException ioException)
		{
			System.err.println("Error writing the file");
			throw new RegisterUserException("Error writing the file");
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException ioException) {
				throw new RegisterUserException("Error closing the file");
			}
		}
	}

}
