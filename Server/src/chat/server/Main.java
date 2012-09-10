package chat.server;

import chat.server.networking.ServerManager;

public class Main {
	public static void main(String[] args) {
		ServerManager server = new ServerManager(12345);
		
		server.runServer();
	}

}
