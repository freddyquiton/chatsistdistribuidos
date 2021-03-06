package chat.client.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import chat.client.exceptions.ServerException;
import chat.common.model.SessionList;
import chat.common.model.User;
import chat.common.tools.Encripter;
import chat.common.tools.Messages;

public class ClientToServerManager {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String url;
	private int port;
	
	public ClientToServerManager(String theUrl, int thePort) throws ServerException {
		url = theUrl;
		port = thePort;
		try {
			connectToServer();
			getStreams();
		} catch (IOException ioException) {
			throw new ServerException("Network error: " + ioException.getMessage());
		} 
	}
	
	private void connectToServer() throws IOException {
		socket = new Socket(InetAddress.getByName(url), port);
	}
	
	private void getStreams() throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		output.flush();
		
		input = new ObjectInputStream(socket.getInputStream());
	}
	
	public void register(String username, String password,
						String rePassword, String firstName,
						String lastName, String email) throws ServerException {
		try {
			String inputMessage;
			
			output.writeObject(Messages.REGISTER);
			output.flush();
			inputMessage = (String)input.readObject();
			if (!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input" + inputMessage);
			output.writeObject(username);
			output.flush();
			output.writeObject(Encripter.encryptPassword(password));
			output.flush();
			output.writeObject(Encripter.encryptPassword(rePassword));
			output.flush();
			output.writeObject(firstName);
			output.flush();
			output.writeObject(lastName);
			output.flush();
			output.writeObject(email);
			output.flush();
			inputMessage = (String)input.readObject();
			if (!inputMessage.equals(Messages.OK))
				throw new ServerException(inputMessage);
		} catch (IOException ioException) {
			throw new ServerException("Network error: " + ioException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			throw new ServerException("Network error: " + classNotFoundException.getMessage());
		}		
	}
	
	public void login(String username, String password) throws ServerException {		
		try {
			String inputMessage;
			
			output.writeObject(Messages.LOGIN);
			output.flush();
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
			output.writeObject(username);
			output.flush();
			output.writeObject(Encripter.encryptPassword(password));
			output.flush();
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException(inputMessage);
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new ServerException(e.getMessage());
		}		
	}
	
	public void logout() throws ServerException {
		
		
		try {
			String inputMessage;
			
			output.writeObject(Messages.LOGOUT);
			output.flush();
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new ServerException(e.getMessage());
		}		
	}
	
	public SessionList userList() throws ServerException {
		SessionList list = null;
				
		try {
			String inputMessage;
			
			output.writeObject(Messages.CONTACTLIST);
			output.flush();
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.BEGINLIST))
				throw new ServerException("Unexpected input " + inputMessage);
			list = (SessionList)input.readObject();
			if (list == null)
				throw new ServerException("Unexpected input: null list");
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
		} catch (IOException e) {
			throw new ServerException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new ServerException(e.getMessage());
		}
		
		return list;
		
	}
	
	public User getUser(String username) throws ServerException {
		User user = null;
		
		try {
			String inputMessage;
			
			output.writeObject(Messages.GETUSER);
			output.flush();
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
			output.writeObject(username);
			output.flush();
			
			user = (User)input.readObject();
			if (user == null)
				throw new ServerException("Unexpected input: null user");
			inputMessage = (String)input.readObject();
			if(!inputMessage.equals(Messages.OK))
				throw new ServerException("Unexpected input " + inputMessage);
			System.out.println("USUARIO:D");
		} catch(Exception e) {
			throw new ServerException(e.getMessage());
		}
		
		return user;
	}
	
	public void close() throws ServerException {
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException ioException) {
			throw new ServerException("Network error: " + ioException.getMessage());
		}
	}

}
