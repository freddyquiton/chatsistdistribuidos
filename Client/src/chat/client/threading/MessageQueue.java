package chat.client.threading;

import java.util.concurrent.ArrayBlockingQueue;

public class MessageQueue {
	private ArrayBlockingQueue<String> buffer;
	
	public MessageQueue() {
		buffer = new ArrayBlockingQueue<>(1);
	}
	
	public void addMessage(String message) throws InterruptedException {
		buffer.put(message);
	}
	
	public String getMessage() throws InterruptedException {
		return buffer.take();
	}
}
