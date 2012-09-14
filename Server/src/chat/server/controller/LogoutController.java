package chat.server.controller;

import chat.common.model.SessionList;

public class LogoutController {
	private SessionList sessions;
	
	public LogoutController(SessionList theSessions) {
		sessions = theSessions;
	}
	
	public void logout(String username) {
		sessions.removeSession(username);
	}
}
