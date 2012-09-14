package chat.server.threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.common.model.SessionList;
import chat.common.tools.Messages;
import chat.server.controller.LoginUserController;
import chat.server.controller.LogoutController;
import chat.server.controller.RegisterUserController;
import chat.server.exceptions.LoginUserException;
import chat.server.exceptions.RegisterUserException;
import chat.server.model.UserDatabase;

public class ConnectionThread implements Runnable {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private UserDatabase database;
	private SessionList sessions;
	private String username;

	public ConnectionThread(Socket theSocket, UserDatabase theDatabase,
			SessionList theSessions) {
		socket = theSocket;
		database = theDatabase;
		sessions = theSessions;
		username = null;
	}

	@Override
	public void run() {
		String firstMessage = "";

		try {
			getStreams();

			do {
				firstMessage = (String) input.readObject();

				if (firstMessage.equals(Messages.REGISTER)) {
					registerUser();
				} else if (firstMessage.equals(Messages.LOGIN)) {
					loginUser();
				} else if (firstMessage.equals(Messages.CONTACTLIST)) {
					sendUserList();
				} else if (firstMessage.equals(Messages.LOGOUT)) {
					logout();					
				} else {
					output.writeObject("Error!! unknow message");
					firstMessage = Messages.LOGOUT;					
				}
			} while (!firstMessage.equals(Messages.LOGOUT));
			
			if (username != null) {
				LogoutController logoutController = new LogoutController(sessions);
				
				logoutController.logout(username);
			}
		} catch (IOException ioException) {
			System.err.println("Error in networking "
					+ ioException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Error in networking "
					+ classNotFoundException.getMessage());
		}

		close();
	}

	private void registerUser() throws ClassNotFoundException, IOException {
		output.writeObject(Messages.OK);

		String userName = (String) input.readObject();
		String password = (String) input.readObject();
		String rePassword = (String) input.readObject();
		String firstName = (String) input.readObject();
		String lastName = (String) input.readObject();
		String email = (String) input.readObject();

		try {
			RegisterUserController controller = new RegisterUserController(
					database);
			controller.registerUser(userName, password, rePassword, firstName,
					lastName, email);
			output.writeObject(Messages.OK);
		} catch (RegisterUserException e) {
			output.writeObject(e.getMessage());
			System.err.println(e.getMessage());
		}
	}

	private void loginUser() throws IOException, ClassNotFoundException {
		output.writeObject(Messages.OK);

		String username = (String) input.readObject();
		String password = (String) input.readObject();

		LoginUserController controller = new LoginUserController(database,
				sessions);

		try {
			controller.loginUser(username, password, socket.getInetAddress()
					.getHostAddress());
			this.username = username;
		} catch (LoginUserException e) {
			output.writeObject(e.getMessage());
			System.err.println(e.getMessage());
		}

	}

	private void sendUserList() throws IOException {
		output.writeObject(Messages.OK);
		output.writeObject(Messages.BEGINLIST);
		output.writeObject(sessions);
	}

	private void logout() throws IOException {
		output.writeObject(Messages.OK);
		
	}

	private void close() {
		try {
			System.out.println("Connection closed from... "
					+ socket.getInetAddress().getHostAddress());
			if (input != null)
				input.close();
			if (output != null)
				output.close();
			socket.close();
		} catch (IOException ioException) {
			System.err.println(ioException.getMessage());
		}
	}

	private void getStreams() throws IOException {
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
	}

}
