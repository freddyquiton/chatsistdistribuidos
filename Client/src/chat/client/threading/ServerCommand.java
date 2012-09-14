package chat.client.threading;

import chat.client.exceptions.NetworkException;
import chat.client.networking.ClientToServerManager;

public interface ServerCommand {
	void execute(ClientToServerManager connection) throws NetworkException ;
}
