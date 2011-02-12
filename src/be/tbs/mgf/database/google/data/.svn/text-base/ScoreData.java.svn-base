package be.tbs.mgf.database.google.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import be.tbs.mgf.games.session.Score;

import com.google.appengine.api.datastore.Key;

/**
 * Keeps track of scores for one player.
 * @author Gino
 */
@PersistenceCapable(detachable="true")
public class ScoreData implements GoogleData, be.tbs.mgf.database.data.ScoreData {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key key;
	
	@Persistent
	private int player;
	
	@Persistent
	private int total;
	
	public ScoreData(Score score) {
		player = score.getPlayer();
		total = score.getTotal();
	}

	/* (non-Javadoc)
	 * @see be.tbs.mgf.database.data.ScoreData#getPlayer()
	 */
	@Override
	public int getPlayer() {
		return player;
	}

	/* (non-Javadoc)
	 * @see be.tbs.mgf.database.data.ScoreData#getScore()
	 */
	@Override
	public int getScore() {
		return total;
	}

	/* (non-Javadoc)
	 * @see be.tbs.mgf.database.data.ScoreData#setPlayer(int)
	 */
	@Override
	public void setPlayer(int id) {
		player = id;
	}

	/* (non-Javadoc)
	 * @see be.tbs.mgf.database.data.ScoreData#setScore(int)
	 */
	@Override
	public void setScore(int total) {
		this.total = total;
	}

	/* (non-Javadoc)
	 * @see be.tbs.mgf.database.data.Data#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ScoreData.class.getName();
	}

	
	public Key getKey() {
		return key;
	}

	
	public void setKey(Key key) {
		this.key = key;
	}

}
