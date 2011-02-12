package be.tbs.mgf.database.data;

import java.util.Date;
import java.util.List;

import be.tbs.mgf.games.session.Score;

/**
 * Provides a datastore-independent way to access Session data.
 * @author Gino
 */
public interface SessionData extends Data {

	/**
	 * Sets whether this session is active or not.
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning);
	
	/**
	 * Gets whether this session is active or not.
	 * @return
	 */
	public boolean getRunning();
	
	/**
	 * Sets the timestamp this session was created.
	 * @param stamp - Timestamp at the creation of this session.
	 */
	public void setTimestamp(Date stamp);
	
	/**
	 * Gets the timestamp this session was created on.
	 * @return Date - The time this session was created.
	 */
	public Date getTimestamp();
	
	/**
	 * Sets the scores for this session.
	 * @param scores - A {@link List} of {@link Score}s
	 */
	public void setScores(List<Score> scores);
	
	/**
	 * Gets the scores for this session.
	 * @return List<Score> - The list of scores.
	 */
	public List<Score> getScores();
	
	/**
	 * Sets the id for this session
	 * @param isRunning
	 */
	public void setId(int id);
	
	/**
	 * Gets this session's id
	 * @return
	 */
	public int getId();
	
}
