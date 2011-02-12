package be.tbs.mgf.database.data;

import java.util.Date;

/**
 * Provides a datastore-independent way to access Status data.
 * @author Gino
 */
public interface StatusData extends Data {
	
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp();

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp);

	/**
	 * @return the property
	 */
	public String getProperty();

	/**
	 * @param property the property to set
	 */
	public void setProperty(String property);

	/**
	 * @return the value
	 */
	public String getValue();

	/**
	 * @param value the value to set
	 */
	public void setValue(String value);

	/**
	 * @return the player
	 */
	public int getPlayer();

	/**
	 * @param player the player to set
	 */
	public void setPlayer(int player);
	
	/**
	 * Sets the id.
	 * Done automatically by an id generator
	 * @param id - the id for this entity
	 */
	public void setId(Long id);
	
	/**
	 * Gets the id
	 * @return Long - an id for this entity
	 */
	public Long getId();
	
}
