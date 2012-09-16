package chat.common.model;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class UserList implements Serializable {
	private ConcurrentHashMap<String, User> userList;
	
	public UserList()
	{
		userList = new ConcurrentHashMap<>();
	}
	
	public boolean addUser(User theUser)
	{
		if(userList.containsKey(theUser.getUsername()))
			return false;
		userList.put (theUser.getUsername(), theUser);
		return true;
	}
	
	public User searchUser(String userName)
	{
		return userList.get(userName);
	}

}
