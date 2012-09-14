package chat.server;

import chat.common.model.SessionList;
import chat.server.exceptions.UserDatabaseException;
import chat.server.model.UserDatabase;
import chat.server.networking.ServerManager;

public class Main {
	public static void main(String[] args) {		
		try {
			UserDatabase database = new UserDatabase();
			SessionList sessions = new SessionList();
			ServerManager server = new ServerManager(12345, database, sessions);
			
			server.runServer();
		} catch (UserDatabaseException e) {
			e.printStackTrace();
		}
	}

}
