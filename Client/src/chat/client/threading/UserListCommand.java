package chat.client.threading;

import chat.client.exceptions.ServerException;
import chat.client.networking.ClientToServerManager;
import chat.common.model.SessionList;

public class UserListCommand implements ServerCommand {
	private boolean ready;
	private SessionList list;
	
	public UserListCommand() {
		ready = false;
		list = null;
	}

	@Override
	public void execute(ClientToServerManager connection)
			throws ServerException {
		list = connection.userList();
		ready = true;
	}
	
	public boolean isReady() {
		return ready;
	}
	
	public SessionList getList() {
		return list;
	}

}
