package be.tbs.mgf.games.player;

import be.tbs.mgf.error.FrameworkException;

/**
 * Signifies that an error occurred while 
 * adding/removing/changing something about the 
 * players registered on the server.
 * @author Gino
 */
public class RosterException extends FrameworkException {
	
	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = -4996641159566861542L;
	private int fPlayer;
	private String fName;

	/**
	 * Constructs a new {@link RosterException} for the given player.
	 * This creates an exception representing a player already exists exception.
	 * @param player - the id of the second, duplicate player
	 * @param name - the name of the player
	 */
	public RosterException(int player, String name) {
		super(900,"Player already exists");
		this.fPlayer = player;
		this.fName = name;
	}
	
	/**
	 * Constructs a new {@link RosterException} for the given player.
	 * This exception may contain any error other than a player already exists error.
	 * For time outs, please use the constructor {@link RosterException}(int player)
	 * @param player - the id of the failing player
	 * @param name - the name of the player
	 * @param error - the error code
	 * @param description - a pretty description of the error
	 */
	public RosterException(int player, String name, int error, String description) {
		super(error,description);
		this.fPlayer = player;
		this.fName = name;
	}
	
	/**
	 * Gets the player
	 * @return int - the id of the failing player
	 */
	public int getPlayer() {
		return fPlayer;
	}

}
