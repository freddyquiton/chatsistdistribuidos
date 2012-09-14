package chat.client.threading;

import java.util.concurrent.ArrayBlockingQueue;

public class ServerCommandQueue {
	private ArrayBlockingQueue<ServerCommand> buffer;
	
	public ServerCommandQueue() {
		buffer = new ArrayBlockingQueue<>(1);
	}
	
	public void addCommand(ServerCommand command) throws InterruptedException {
		buffer.put(command);
	}
	
	public ServerCommand getCommand() throws InterruptedException {
		return buffer.take();
	}

}
