package chat.common.model;

import java.io.Serializable;

import chat.common.tools.Encripter;

public class User implements Serializable {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	
	public User(String theUsername, String thePassword, String theFirstName,
			String theLastName, String theEmail) {
		setUsername(theUsername);
		setPassword(thePassword);
		setFirstName(theFirstName);
		setLastName(theLastName);
		setEmail(theEmail);
	}
	
	// Get methods
	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getEmail() {
		return email;
	}	

	public String getLastName() {
		return lastName;
	}
	
	// Set methods	
	public void setUsername(String theUsername) {
		username = theUsername;
	}
	
	public void setFirstName(String theFirstName) {
		firstName = theFirstName;
	}
	
	public void setLastName(String theLastName) {
		lastName = theLastName;
	}	

	public void setEmail(String theEmail) {
		email = theEmail;
	}

	public void setPassword(String thePassword) {
		password = Encripter.encryptPassword(thePassword);
	}
	
	public boolean checkPassword(String thePassword)
	{
		return password == Encripter.encryptPassword(thePassword);
	}
	
}
