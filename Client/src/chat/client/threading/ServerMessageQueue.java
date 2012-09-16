package chat.client.threading;

import java.util.concurrent.ArrayBlockingQueue;

public class ServerMessageQueue {
	private ArrayBlockingQueue<String> buffer;
	
	public ServerMessageQueue() {
		buffer = new ArrayBlockingQueue<>(1);
	}
	
	public void addMessage(String message) throws InterruptedException {
		buffer.put(message);
	}
	
	public String getMessage() throws InterruptedException {
		return buffer.take();
	}
}
