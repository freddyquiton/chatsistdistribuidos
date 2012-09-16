package chat.client.controller;

import chat.client.exceptions.ServerException;
import chat.client.exceptions.RegisterException;
import chat.client.threading.ServerManager;

public class RegisterController {
	private ServerManager server;
	private String url;
	private int port;
	
	public RegisterController(ServerManager theServer, String theUrl, int thePort) {
		server = theServer;
		url = theUrl;
		port = thePort;
	}
	
	public boolean isValidUsername(String username) {
		return username.matches("[A-Za-z0-9]+") && username.length() <= 20 && username.length() > 0;
	}
	
	public boolean isValidPassword(String password) {
		return password.length() >= 8 && password.length() <= 20;
	}
	
	public boolean isRePasswordEqualToPassword(String password, String rePassword) {
		return password.equals(rePassword);
	}
	
	public boolean isValidFirstName(String firstName) {
		return firstName.length() <= 30 && firstName.length() > 0 && firstName.matches("[A-Z][A-Za-z]+( [A-Z][A-Za-z]+)*");
	}
	
	public boolean isValidLastName(String lastName) {
		return lastName.length() <= 30 && lastName.length() > 0 && lastName.matches("[A-Z][A-Za-z]+( [A-Z][A-Za-z]+)*");
	}
	
	public boolean isValidEmail(String email) {
		return email.length() > 0 && email.length() <= 50 && email.matches("[A-Za-z0-9][A-Za-z0-9_\\-\\.]*@[A-Za-z0-9][A-Za-z0-9_\\-\\.]*\\.[a-z]+");
	}
	
	public void register(String username, String password, String rePassword, String firstName, String lastName, String email) throws RegisterException {
		validate(username, password, rePassword, firstName, lastName, email);
		
		try {
			server.run(url, port);
			server.registerUser(username, password, rePassword, firstName, lastName, email);
		} catch (ServerException e) {
			throw new RegisterException(e.getMessage());
		} catch (InterruptedException e) {
			throw new RegisterException("Error al registrar usuario " + e.getMessage());
		}
	}

	private void validate(String username, String password, String rePassword,
			String firstName, String lastName, String email)
			throws RegisterException {
		if (!isValidUsername(username))
			throw new RegisterException("El nombre de usuario debe ser máximo 20 carácteres, y solo letras y números");
		if(!isValidPassword(password))
			throw new RegisterException("La contraseña debe ser mínimo 8 caracteres, y máximo 20 caracteres");
		if(!isRePasswordEqualToPassword(password, rePassword))
			throw new RegisterException("Las contraseñas no coinciden");
		if(!isValidFirstName(firstName))
			throw new RegisterException("Los nombres deben ser solo letras");
		if(!isValidLastName(lastName))
			throw new RegisterException("Los apellidos deben ser solo letras");
		if(!isValidEmail(email))
			throw new RegisterException("E-mail invalido");
	}
	
}
