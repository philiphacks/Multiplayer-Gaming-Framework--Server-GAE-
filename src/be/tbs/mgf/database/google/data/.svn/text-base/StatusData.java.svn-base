package be.tbs.mgf.database.google.data;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import be.tbs.mgf.games.session.status.StatusUpdate;
import be.tbs.mgf.schemas.StatusProperty;

/**
 * Status Data
 * Keeps track of the status (value) of a property
 * @author Gino
 */
@PersistenceCapable(detachable="true")
public class StatusData implements GoogleData, be.tbs.mgf.database.data.StatusData {
	
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
    
	@Persistent
	protected Date timestamp;
	
	@Persistent
	protected String property;
	
	@Persistent
	protected String value;
	
	@Persistent
	protected int player;

	/**
	 * Constructs a StatusData object.
	 * The containing property an value information is gathered from the StatusProperty parameter.
	 * The timestamp of this status update is taken at the time of construction of this object.
	 * @param sp - StatusProperty containing the status property you want to persist.
	 */
	public StatusData(StatusProperty sp) {
		property = sp.getName();
		value = sp.getValue();
		timestamp = new Date();
		player = -1;
	}
	
	/**
	 * Constructs StatusData from a {@link StatusUpdate}
	 * @param statusUpdate - the status update to get information from
	 */
	public StatusData(StatusUpdate statusUpdate) {
		this.player = statusUpdate.getPlayer();
		this.property = statusUpdate.getProperty();
		this.value = statusUpdate.getValue();
		this.timestamp = statusUpdate.getStamp();
	}

	/**
	 * Sets the id
	 * -- automatically done by an ID generator
	 * @param id - Long value for the id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the id
	 * @return Long - a unique id for this entity
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the player
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(int player) {
		this.player = player;
	}
	
	@Override
	public String getEntityName() {
		return StatusData.class.getName();
	}

}
