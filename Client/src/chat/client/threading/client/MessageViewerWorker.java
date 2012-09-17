package chat.client.threading.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

import chat.client.threading.ServerManager;
import chat.common.tools.Messages;

public class MessageViewerWorker extends SwingWorker<Void, Void> {
	private JTextArea textArea;
	private ObjectInputStream input;
	private ServerManager server;
	private String username;
	
	public MessageViewerWorker(JTextArea theTextArea, ObjectInputStream theInput, ServerManager theServer, String destinatary) {
		textArea = theTextArea;
		input = theInput;
		server = theServer;
		username = destinatary;
	}

	@Override
	protected Void doInBackground() {
		String message = "";
		
		while(server.isRunning() && !message.equals(Messages.ENDCONVERSATION)) {			
			try {
				message = (String)input.readObject();
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);				
			}
			
			textArea.append(username + " dijo: " + message + "\n");
		}
		
		try {
			input.close();
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return null;
	}
}
