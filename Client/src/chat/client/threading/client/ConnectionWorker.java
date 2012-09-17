package chat.client.threading.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import chat.client.threading.MessageQueue;
import chat.client.threading.ServerManager;
import chat.common.model.User;
import chat.common.tools.Messages;

public class ConnectionWorker extends SwingWorker<Void, Void> {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private User destinyUser;	
	private JTextArea textArea;
	private ServerManager server;
	private MessageQueue messages;
	
	public ConnectionWorker(Socket connection, ServerManager theServer, JTextArea txtArea, MessageQueue queue) {
		socket = connection;
		server = theServer;
		messages = queue;
		textArea = txtArea;	
	}
	
	@Override
	protected Void doInBackground() {
		try {
			getStreams();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		destinyUser = new User("prueba", null, null, null, null);
		
		MessageViewerWorker worker = new MessageViewerWorker(textArea, input, server, destinyUser.getUsername());		
		worker.execute();
		
		
		try {
			String msg = messages.getMessage();
			
			while(!msg.equals(Messages.ENDCONVERSATION)) {
				sendMessage(msg);
				msg = messages.getMessage();
			}
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
	
	private void getStreams() throws IOException {
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
	}
	
	private void sendMessage(String message) throws IOException {
		output.writeObject(message);
		output.flush();
		
		textArea.append(message + "\n");
	}
	
	public void close() throws IOException {
		output.flush();
		output.close();
		
		socket.close();
	}
}
