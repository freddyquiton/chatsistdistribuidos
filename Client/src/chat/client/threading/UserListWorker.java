package chat.client.threading;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import chat.common.model.Session;
import chat.common.model.SessionList;

public class UserListWorker extends SwingWorker<Void, String> {
	private ServerManager server;
	private JList list;
	private String username;
	
	public UserListWorker(JList theList, ServerManager theServer, String theUsername) {
		list = theList;
		server = theServer;
		username = theUsername;
	}

	@Override
	protected Void doInBackground() {
		System.out.println("Buscando conectados...");
		while(server.isRunning()) {
			try {				
				SessionList userList = server.getUserList();
				DefaultListModel<Session> model = new DefaultListModel<Session>();
				
				model.clear();
				
				for(Session session : userList.getSessionList()) {
					if (!username.equals(session.toString()))
						model.addElement(session);
				}
				
				list.setModel(model);
				if(server.isRunning())
					Thread.sleep(5000);
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		System.out.println("Ya no buscar conectados...");
		return null;
	}
}
