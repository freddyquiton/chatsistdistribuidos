package chat.server.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import chat.common.model.User;
import chat.common.model.UserList;
import chat.server.exceptions.UserDatabaseException;

public class UserDatabase {
	public static final String userDatabaseFile = "users.dat";
	private UserList userList;
	private ObjectInputStream input;
	
	public UserDatabase() throws UserDatabaseException{
		if (existFileDatabase())
			readDataBaseFile();
		else
			userList = new UserList();
	}
	
	public boolean addUser(User theUser)
	{
		return userList.addUser(theUser);
	}
	
	public User searchUser(String userName)
	{
		return userList.searchUser(userName);
	}
	
	private boolean existFileDatabase()
	{
		File file = new File(userDatabaseFile);
		
		return file.exists();
	}
	
	private void readDataBaseFile() throws UserDatabaseException {
		try {
			input = new ObjectInputStream(new FileInputStream(userDatabaseFile));
			
			userList = (UserList)input.readObject();
		} catch(IOException ioException)
		{
			System.err.println("Error reading the file");
			throw new UserDatabaseException("Error reading the file");
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Error reading the database");
			throw new UserDatabaseException("Error reading the user database");
		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException ioException) {
				throw new UserDatabaseException("Error closing the file");
			}
		}		
	}
	
	public void saveData() throws UserDatabaseException
	{
		ObjectOutputStream output = null;
		
		try {
			output = new ObjectOutputStream(new FileOutputStream(userDatabaseFile));
			
			output.writeObject(userList);
		}  catch(IOException ioException)
		{
			System.err.println("Error writing the file");
			throw new UserDatabaseException("Error writing the file");
		} finally {
			try {
				if (output != null)
					output.close();
			} catch (IOException ioException) {
				throw new UserDatabaseException("Error closing the file");
			}
		}
	}
}
