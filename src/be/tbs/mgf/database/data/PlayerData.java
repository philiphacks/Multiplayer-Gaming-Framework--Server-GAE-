package be.tbs.mgf.database.data;

import java.util.Date;

/**
 * Provides a datastore-independent way to access Player data.
 * @author Gino
 */
public interface PlayerData extends Data {
	
	/**
	 * @return the id
	 */
	public int getId();

	/**
	 * @param id the id to set
	 */
	public void setId(int id);

	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name the name to set
	 */
	public void setName(String name);

	/**
	 * @return the last_stamp
	 */
	public Date getLast_stamp();

	/**
	 * @param lastStamp the last_stamp to set
	 */
	public void setLast_stamp(Date lastStamp);

}
