package be.tbs.mgf.games.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.data.SessionData;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;

/**
 * Mechanism for keeping track of 'rounds' with a start and an end.
 * Status properties are applied through this object, as if the properties would apply to this session only.
 * <b>Any changes made to the internal state of this object is persisted to the datastore!</b>
 * @author Gino
 */
public class Session {
	
	private boolean fRunning;
	
	private Date fCreated;
	
	private List<Score> fScores;
	
	private int fId;
	
	private DataStoreConnection fDBC = new GoogleDataStoreConnection();
	
	public Session(SessionData data) {
		// Local
		fRunning = data.getRunning();
		fCreated = data.getTimestamp();
		fScores = data.getScores();
		fId = data.getId();
		// No persistency ops needed, because this session is restored from the database.
	}
	
	/**
	 * Creates a new session.
	 * The session is created as a running session,
	 * with the current date/time as the timestamp of its start.
	 * The creation of this session is immediately persisted to the datastore.
	 * @param id - The id for this session.
	 */
	public Session(int id) {
		// Local
		fRunning = true;
		fCreated = new Date();
		fScores = new ArrayList<Score>();
		// Persistent
		persist();
	}
	
	/**
	 * Persists the current state of this session to the datastore.
	 */
	private void persist() {
		be.tbs.mgf.database.google.data.SessionData sd = new be.tbs.mgf.database.google.data.SessionData(this);
		fDBC.add(sd);
	}
	
	/**
	 * Sets whether this session is in business (active) or not.
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		fRunning = isRunning;
		persist();
	}

	/**
	 * Checks if this session is running or not.
	 * @return boolean - true if this session is active.
	 */
	public boolean isRunning() {
		return fRunning;
	}

	/**
	 * Gets the date this session was created.
	 * @return Date - The date this session was created.
	 */
	public Date getCreated() {
		return fCreated;
	}
	
	/**
	 * Gets the list of scores for every player.
	 * This list might contain players already disconnected from the game.
	 * @return List<Score> - The list of score objects for every player.
	 */
	public List<Score> getScores() {
		return fScores;
	}
	
	/**
	 * Sets the score.
	 * If a score for the player is already present, it is overwritten.
	 * @param s
	 */
	public void setScore(Score score) {
		for (int i = 0; i < fScores.size(); i++) {
			Score existing_score = fScores.get(i);
			if (existing_score.getPlayer() == score.getPlayer()) {
				fScores.remove(i);
			}
		}
		persist();
	}
	
	/**
	 * Gets the score for the specified player.
	 * @param player - The player to get the score for.
	 * @return Score - The score of the specified player. Null if there is no score for the specified player.
	 */
	public Score getScore(int player) {
		for (Score score : fScores) {
			if (score.getPlayer() == player) {
				return score;
			}
		}
		return null;
	}

}
