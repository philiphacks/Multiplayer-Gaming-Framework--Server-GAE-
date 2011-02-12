package be.tbs.mgf.games.player;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.data.PlayerData;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;

/**
 * Represents a storage independent player.
 * <b>Any changes made to the internal state of this object is persisted to the datastore!</b>
 * @author Gino Wuytjens & Philip De Smedt
 */
public class Player {
	
	private int fId;
	
	private Date fStamp;
	
	private String fName;
	
//	private DataStoreConnection fDBC = new GoogleDataStoreConnection();
	
	private Logger fLog = Logger.getLogger(Player.class.getName());

	private DataStoreConnection fDBC = new GoogleDataStoreConnection();

	/**
	 * Constructs a player from a player entity present in the datastore.
	 * @param data - The datastore information about this player.
	 */
	public Player(PlayerData data) {
		fId = data.getId();
		fStamp = data.getLast_stamp();
		fName = data.getName();
	}
	
	/**
	 * Creates a new player from a name and a timestamp.
	 * This is a persistent object an upon return, this constructor will have persisted
	 * this player instance to the datastore! When an exception is thrown,
	 * no player was actually added to the game.
	 * @param name - the name of the player
	 * @param stamp - the timestamp in the latest message of the player
	 * @throws RosterException - When a player with that name already exists.
	 */
	public Player(String name, Date stamp) throws RosterException {
		this.fName = name;
		this.fStamp = stamp;
		List<PlayerData> players = (List<PlayerData>) fDBC .getAll(be.tbs.mgf.database.google.data.PlayerData.class.getName(), "name", name);
		fLog.info("Creating new player, game contains " + players.size() + " players already");
		for (PlayerData playerData : players) {
			fLog.info("Player: " + playerData.getName() + " Id: " + playerData.getId() + " Stamp: " + playerData.getLast_stamp());
		}
		if (players.size() > 0) {
			throw new RosterException(-1,name);
		}
		// This player did not yet exist, we can officially add us by getting our id and then persisting.
		this.fId = getNewId();
		join();
	}
	
	/**
	 * Sets the latest timestamp seen by this player.
	 * @param stamp - The timestamp from a request.
	 */
	public void setLastStamp(Date stamp) {
		fStamp = stamp;
		persist();
	}
	
	/**
	 * Gets the latest time stamp seen by this player.
	 * @return
	 */
	public Date getLastStamp() {
		return fStamp;
	}
	
	/**
	 * Gets the id for this player.
	 * @return
	 */
	public int getId() {
		return fId;
	}
	
	/**
	 * Gets the name for this player.
	 * @return
	 */
	public String getName() {
		return fName;
	}
	
	/**
	 * Creates an ID for a new player.
	 * @return int - the id for a new, joining player
	 */
	protected int getNewId() {
		List<PlayerData> players = (List<PlayerData>) fDBC.getAll(be.tbs.mgf.database.google.data.PlayerData.class.getName());
		int id = 1;
		for (int i = 0; i < players.size(); i++) {
			if (id <= players.get(i).getId())
				id = players.get(i).getId()+1;
		}
		return id;
	}
	
	/**
	 * Stores this player in the data store.
	 */
	protected void join() {
		be.tbs.mgf.database.google.data.PlayerData pd = new be.tbs.mgf.database.google.data.PlayerData(this);
		fDBC.add(pd);
	}
	
	/**
	 * Persists changes to this object to the datastore.
	 * This player must already be present in the data store,
	 * otherwise this method might return a JDO exception.
	 */
	public void persist() {
		PersistenceManager pm = fDBC.getPersistenceManager();
		PlayerData playerData = pm.getObjectById(be.tbs.mgf.database.google.data.PlayerData.class, fId+"");
		if (fId != playerData.getId())
			playerData.setId(fId);
		if (fName != playerData.getName())
			playerData.setName(fName);
		if (fStamp != playerData.getLast_stamp())
			playerData.setLast_stamp(fStamp);
		pm.close();
	}

}
