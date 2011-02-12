/**
 * 
 */
package be.tbs.mgf.games.session.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.data.StatusData;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;

/**
 * Status Update keeps track of variables an their values
 * by storing changes to them in the datastore.
 * @author Gino
 */
public class StatusUpdate {

	private Long fId;
	private int fPlayer;
	private String fProperty;
	private String fValue;
	private Date fStamp;
	
	private static DataStoreConnection fDBC = new GoogleDataStoreConnection();

	/**
	 * Constructs a new StatusUpdate using {@link StatusData} from the datastore.
	 * This construction is not persisted, because no changes are made to the status
	 */
	public StatusUpdate(StatusData sd) {
		this.fId = sd.getId();
		this.fPlayer = sd.getPlayer();
		this.fProperty = sd.getProperty();
		this.fValue = sd.getValue();
		this.fStamp = sd.getTimestamp();
	}
	
	/**
	 * Constructs a new StatusUpdate using the player who issued it, the property and its value
	 * @param player - the id of the issuing player, if applicable
	 * @param stamp - the timestamp for this status update
	 * @param property - the property name
	 * @param value - the value of the property
	 */
	public StatusUpdate(int player, Date stamp, String property, String value) {
		this.fPlayer = player;
		this.fProperty = property;
		this.fValue = value;
		this.fStamp = stamp;
		add();
	}
	
	protected void add() {
		StatusData pd = new be.tbs.mgf.database.google.data.StatusData(this);
		fDBC.add(pd);
	}

	/**
	 * @return the Id
	 */
	public Long getId() {
		return fId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.fId = id;
	}

	/**
	 * @return the Player
	 */
	public int getPlayer() {
		return fPlayer;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(int player) {
		this.fPlayer = player;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return fProperty;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.fProperty = property;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return fValue;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.fValue = value;
	}

	/**
	 * @return the stamp
	 */
	public Date getStamp() {
		return fStamp;
	}

	/**
	 * @param stamp the stamp to set
	 */
	public void setStamp(Date stamp) {
		this.fStamp = stamp;
	}

	/**
	 * Gets all status updates since the given timestamp
	 * @param previousTimestamp - the timestamp
	 * @return all statusupdates since the given stamp
	 */
	public static List<StatusUpdate> getAllSince(Date previousTimestamp) {
		PersistenceManager pm = fDBC.getPersistenceManager();
		Query q = pm.newQuery(be.tbs.mgf.database.google.data.StatusData.class);
		q.declareImports("import java.util.Date");
        q.setFilter("timestamp > fieldValue");
        q.declareParameters("Date fieldValue");
        List<StatusData> statuses = (List<StatusData>) q.execute(previousTimestamp);
        List<StatusUpdate> statusUpdates = new ArrayList<StatusUpdate>();
        for (StatusData statusData : statuses) {
			statusUpdates.add(new StatusUpdate(statusData));
		}
        return statusUpdates;
	}
	
	
	
	

}
