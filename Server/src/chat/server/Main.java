package chat.server;

import chat.server.exceptions.UserDatabaseException;
import chat.server.model.UserDatabase;
import chat.server.networking.ServerManager;

public class Main {
	public static void main(String[] args) {		
		try {
			UserDatabase database = new UserDatabase();
			ServerManager server = new ServerManager(12345, database);
			
			server.runServer();
		} catch (UserDatabaseException e) {
			e.printStackTrace();
		}
	}

}
