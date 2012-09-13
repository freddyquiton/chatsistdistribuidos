package chat.client.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import chat.client.exceptions.NetworkException;
import chat.common.tools.Encripter;
import chat.common.tools.Messages;

public class ClientToServerManager {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String url;
	private int port;
	
	public ClientToServerManager(String theUrl, int thePort) throws NetworkException {
		url = theUrl;
		port = thePort;
		try {
			connectToServer();
			getStreams();
		} catch (IOException ioException) {
			throw new NetworkException("Network error: " + ioException.getMessage());
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
						String lastName, String email) throws NetworkException {
		try {
			String inputMessage;
			
			output.writeObject(Messages.REGISTER);
			inputMessage = (String)input.readObject();
			if (!inputMessage.equals(Messages.OK))
				throw new NetworkException("Unexpected input" + inputMessage);
			output.writeObject(username);
			output.writeObject(Encripter.encryptPassword(password));
			output.writeObject(Encripter.encryptPassword(rePassword));
			output.writeObject(firstName);
			output.writeObject(lastName);
			output.writeObject(email);
			inputMessage = (String)input.readObject();
			if (!inputMessage.equals(Messages.OK))
				throw new NetworkException(inputMessage);
		} catch (IOException ioException) {
			throw new NetworkException("Network error: " + ioException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			throw new NetworkException("Network error: " + classNotFoundException.getMessage());
		}		
	}
	
	public void close() throws NetworkException {
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException ioException) {
			throw new NetworkException("Network error: " + ioException.getMessage());
		}
	}

}
