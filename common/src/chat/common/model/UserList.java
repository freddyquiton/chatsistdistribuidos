package chat.common.model;

import java.io.Serializable;
import java.util.HashMap;

public class UserList implements Serializable {
	private HashMap<String, User> userList;
	
	public UserList()
	{
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
