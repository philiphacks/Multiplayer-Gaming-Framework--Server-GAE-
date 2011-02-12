package be.tbs.mgf.database.google.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import be.tbs.mgf.games.session.Score;
import be.tbs.mgf.games.session.Session;

import com.google.appengine.api.datastore.Key;

/**
 * Keeps track of play sessions.
 * @author Gino
 */
@PersistenceCapable(detachable="true")
public class SessionData implements GoogleData, be.tbs.mgf.database.data.SessionData {
	
	@Persistent
	private boolean isRunning;
	
	@Persistent
	private Date created;
	
	@Persistent
	private List<ScoreData> scores;

	@PrimaryKey
	@Persistent
	private int id;
	
	/**
	 * Constructs a new session data object by using the session object provided.
	 * @param session - A session, may or may not be active.
	 */
	public SessionData(Session session) {
		isRunning = session.isRunning();
	}
	
	@Override
	public boolean getRunning() {
		return isRunning;
	}

	@Override
	public Date getTimestamp() {
		return created;
	}

	@Override
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void setTimestamp(Date stamp) {
		created = stamp;
	}
	
	@Override
	public String getEntityName() {
		return SessionData.class.getName();
	}

	@Override
	public List<Score> getScores() {
		List<Score> scoreList = new ArrayList<Score>();
		for (ScoreData score : this.scores) {
			scoreList.add(new Score(score));
		}
		return scoreList;
	}

	@Override
	public void setScores(List<Score> scores) {
		this.scores = new ArrayList<ScoreData>();
		for (Score score : scores) {
			this.scores.add(new ScoreData(score));
		}
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
