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
	
	public UserListWorker(JList theList, ServerManager theServer) {
		list = theList;
		server = theServer;
	}

	@Override
	protected Void doInBackground() {
		System.out.println("Buscando conectados...");
		while(server.isRunning()) {
			try {				
				SessionList userList = server.getUserList();
				DefaultListModel model = new DefaultListModel();
				
				model.clear();
				for(Session session : userList.getSessionList()) {
					model.addElement(session);
				}
				list.setModel(model);
				Thread.sleep(5000);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);				
			}
		}
		System.out.println("Ya no buscar conectados...");
		return null;
	}

}
