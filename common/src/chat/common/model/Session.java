package chat.common.model;

import java.io.Serializable;

public class Session implements Serializable {
	private String username;
	private String url;
	
	public Session(String theUsername, String theUrl) {
		username = theUsername;
		url = theUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getUrl() {
		return url;
	}	
	
}
