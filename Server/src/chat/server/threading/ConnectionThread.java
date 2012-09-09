package chat.server.threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.common.tools.Messages;
import chat.server.controller.RegisterUserController;
import chat.server.exceptions.RegisterUserException;

public class ConnectionThread implements Runnable {
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	public ConnectionThread(Socket theSocket)
	{
		socket = theSocket;
	}
	
	@Override
	public void run() {
		try {
			getStreams();
			String firstMessage = (String)input.readObject();
			
			if (firstMessage.equals(Messages.REGISTER))
			{
				output.writeObject(Messages.OK);
				registerUser();
			}
		} catch (IOException ioException) {
			System.err.println("Error in networking " + ioException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			System.err.println("Error in networking " + classNotFoundException.getMessage());
		} finally
		{
			close();
		}
	}

	private void registerUser() throws ClassNotFoundException, IOException {
		String userName = (String)input.readObject();
		String password = (String)input.readObject();
		String rePassword = (String)input.readObject();
		String firstName = (String)input.readObject();
		String lastName = (String)input.readObject();
		String email = (String)input.readObject();
		
		try {
			RegisterUserController controller = new RegisterUserController();
			controller.RegisterUser(userName, password, rePassword, firstName, lastName, email);
			output.writeObject(Messages.OK);
		} catch (RegisterUserException e) {
			output.writeObject(e.getMessage());
			e.printStackTrace();
		}
	}

	private void close() {
		try
		{
			System.out.println("Connection closed from... " + socket.getInetAddress().getHostAddress());
			if (input != null)
				input.close();
			if (output != null)
				output.close();
			socket.close();
		}  catch (IOException ioException) {
			System.err.println(ioException.getMessage());
		}
	}

	private void getStreams() throws IOException {
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
	}

}
