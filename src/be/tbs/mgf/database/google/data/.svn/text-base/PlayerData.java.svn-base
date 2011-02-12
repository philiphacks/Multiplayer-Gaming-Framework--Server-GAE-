package be.tbs.mgf.database.google.data;

import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import be.tbs.mgf.games.player.Player;

/**
 * Player Data
 * Keeps track of player information.
 * @author Gino
 */
@PersistenceCapable(detachable="true")
public class PlayerData implements GoogleData, be.tbs.mgf.database.data.PlayerData {
	
	@PrimaryKey
	@Persistent
	protected String playerId;
	
	@Persistent
	protected String name;
	
	@Persistent
	protected Date last_stamp;
	
	/**
	 * Constructs a Player Data object using a description of the player.
	 * @param id - The assigned ID for the player
	 * @param name - The name of the player as a String
	 * @param timestamp - The last timestamp seen in a request sent by this player.
	 */
	public PlayerData(int id, String name, Date timestamp) {
		this.playerId = id+"";
		this.name = name;
		this.last_stamp = timestamp;
	}
	
	/**
	 * Constructs a Player Data object using the Player object provided.
	 * @param player - An instance of a player.
	 */
	public PlayerData(Player player) {
		this.playerId = player.getId()+"";
		this.last_stamp = player.getLastStamp();
		this.name = player.getName();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return Integer.parseInt(playerId);
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.playerId = id+"";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the last_stamp
	 */
	public Date getLast_stamp() {
		return last_stamp;
	}

	/**
	 * @param lastStamp the last_stamp to set
	 */
	public void setLast_stamp(Date lastStamp) {
		last_stamp = lastStamp;
	}

	@Override
	public String getEntityName() {
		return PlayerData.class.getName();
	}

}
