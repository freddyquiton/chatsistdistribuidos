package chat.client.controller;

import chat.client.exceptions.LogoutException;
import chat.client.exceptions.ServerException;
import chat.client.threading.ServerManager;

public class LogoutController {
	private ServerManager server;

	public LogoutController(ServerManager theServer) {
		server = theServer;
	}

	public void logout() throws LogoutException {
		try {
			if (server.isRunning())
				server.logout();
		} catch (ServerException e) {
			throw new LogoutException(e.getMessage());
		} catch (InterruptedException e) {
			throw new LogoutException("Error al registrar usuario " + e.getMessage());
		}
	}

}
