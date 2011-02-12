package be.tbs.mgf.games.session;

import java.util.List;
import java.util.logging.Logger;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.data.SessionData;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;
import be.tbs.mgf.schemas.StatusProperty;

/**
 * 
 * @author Gino
 */
public class SessionManager {
	
	// This field my be initialized multiple times during the course of a game (#GAEQuirks).
	private static Session fSession = restoreSession();
	
	private static Logger fLog = Logger.getLogger(SessionManager.class.getName());
	
	private static DataStoreConnection fDBC = new GoogleDataStoreConnection();
	
	private SessionManager() {}
	
	/**
	 * Returns the current session.
	 * @return
	 */
	public static Session getCurrent() {
		return fSession;
	}
	
	/**
	 * Reads and returns the current session from the datastore.
	 */
	protected static Session restoreSession() {
		GoogleDataStoreConnection dbc = new GoogleDataStoreConnection();
		List<SessionData> datas = (List<SessionData>) dbc.getAll(be.tbs.mgf.database.google.data.SessionData.class.getName(), "isRunning", "true");
		if (datas.size() == 0) {
			// There are no active sessions stored.
			// Get a new one.
			return new Session(createID());
		} else if (datas.size() == 1) {
			// One active session is stored.
			// Restore it
			return new Session(datas.get(0));
		} else {
			// There was more than one active session.
			// There is no support for this yet.
			for (SessionData sessionData : datas) {
				sessionData.setRunning(false);
				fDBC.add(sessionData);
			}
			fLog.warning("Multiple active sessions were found in the datastore. Setting them as inactive and starting a new session");
			return new Session(createID());
		}
	}
	
	/**
	 * Starts a new session at this time.
	 * This can only be done when no session is currently running.
	 */
	@Deprecated
	public static void newSession() {
		if (fSession != null) {
			if (fSession.isRunning())
				fLog.warning("Attempt was made to start a new session when a session was already running. The operation had no effect.");
			else
				fSession = new Session(createID());
		} else {
			// This should not happen unless some assumptions were wrong. Log it.
			fLog.info("A new session was started when no previous session existed. First session after a datastore wipe?");
			fSession = new Session(createID());
		}		
		
	}
	
	/**
	 * Creates an ID for use when creating a <b>new</b> session.
	 * Created by checking the id's of existing sessions.
	 * @return
	 */
	protected static int createID() {
		List<SessionData> fSessions = (List<SessionData>) fDBC.getAll(be.tbs.mgf.database.google.data.SessionData.class.getName());
		int id = 0;
		for (SessionData sessionData : fSessions) {
			if (id < sessionData.getId())
				id = sessionData.getId();
		}
		return id+1;
	}
	
	public static List<StatusProperty> processProperties(StatusProperty sp) {
		// TODO : Implement
		return null;
	}
	
	public static List<StatusProperty> processProperties(List<StatusProperty> sp) {
		// TODO : Implement
		return null;
	}

}
