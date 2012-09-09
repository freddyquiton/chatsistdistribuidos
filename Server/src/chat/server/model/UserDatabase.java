package chat.server.model;

import java.io.Serializable;
import java.util.HashMap;

import chat.common.model.User;

public class UserDatabase implements Serializable {
	private HashMap<String, User> userList;
	
	public UserDatabase() {
		userList = new HashMap<>();
	}
	
	public boolean addUser(User theUser)
	{
		if(userList.containsKey(theUser.getUsername()))
			return false;
		userList.put(theUser.getUsername(), theUser);
		return true;
	}
	
	public User searchUser(String userName)
	{
		return userList.get(userName);
	}
}
