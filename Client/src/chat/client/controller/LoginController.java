package chat.client.controller;

import chat.client.exceptions.LoginException;
import chat.client.exceptions.ServerException;
import chat.client.threading.ServerManager;

public class LoginController {
	private ServerManager server;
	private String url;
	private int port;
	
	public LoginController(ServerManager theServer, String theUrl, int thePort) {
		server = theServer;
		url = theUrl;
		port = thePort;
	}
	
	public void login(String username, String password) throws LoginException {
		validate(username, password);
		try {
			server.run(url, port);
			server.loginUser(username, password);
		} catch (ServerException e) {
			throw new LoginException(e.getMessage());
		} catch (InterruptedException e) {
			throw new LoginException("Error al registrar usuario " + e.getMessage());
		}
	}

	private void validate(String username, String password) throws LoginException {
		if(username == null)
			throw new LoginException("El nombre de usuario no puede ser nulo");
		if(password == null)
			throw new LoginException("La contraseña no puede ser nula");
		if(username.isEmpty())
			throw new LoginException("El nombre de la usuario no puede ser vacio");
		if(password.isEmpty())
			throw new LoginException("La contraseña no puede ser vacia");
	}
}
