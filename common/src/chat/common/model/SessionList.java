package chat.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class SessionList implements Serializable {
	private ConcurrentHashMap<String, Session> sessions;
	
	public SessionList() {
		sessions = new ConcurrentHashMap<>();
	}
	
	public boolean addSession(Session session) {
		if (sessions.containsKey(session.getUsername()))
			return false;
		sessions.put(session.getUsername(), session);
		
		return true;
	}
	
	public Session getSession(String username)
	{
		return sessions.get(username);
	}
	
	public void removeSession(String username) {
		if (sessions.containsKey(username)) {
			sessions.remove(username);
		}
	}
	
	public ArrayList<Session> getSessionList()
	{
		return new ArrayList<Session>(sessions.values());
	}
}
