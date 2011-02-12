package be.tbs.mgf.synchronization;

import be.tbs.mgf.error.FrameworkException;

/**
 * Exception thrown when synchronization with a player fails.
 * @author Gino
 */
public class SynchronizationException extends FrameworkException {
	
	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = -3799524843496366523L;
	private int fPlayer;

//	private SynchronizationException() {
//		super();
//	}
//
//	private SynchronizationException(String arg0, Throwable arg1) {
//		super(arg0, arg1);
//	}
//
//	private SynchronizationException(String arg0) {
//		super(arg0);
//	}
//
//	private SynchronizationException(Throwable arg0) {
//		super(arg0);
//	}
//	
	
	
	/**
	 * Constructs a new {@link SynchronizationException} for the given player.
	 * This creates an exception representing a time out.
	 * @param player - the id of the failing player
	 */
	public SynchronizationException(int player) {
		super(700,"Connection timed out");
		this.fPlayer = player;
	}
	
	/**
	 * Constructs a new {@link SynchronizationException} for the given player.
	 * This exception may contain any error other than a time out.
	 * For time outs, please use the constructor {@link SynchronizationException}(int player)
	 * @param player - the id of the failing player
	 * @param error - the error code
	 * @param description - a pretty description of the error
	 */
	public SynchronizationException(int player, int error, String description) {
		super(error,description);
		this.fPlayer = player;
	}
//	
//	/**
//	 * Constructs a new {@link SynchronizationException} for the given player, with the given msg.
//	 * @param player - the id of the failing player
//	 * @param message - extra information
//	 */
//	public SynchronizationException(int player, String message) {
//		
//	}
	
	/**
	 * Gets the player
	 * @return int - the id of the failing player
	 */
	public int getPlayer() {
		return fPlayer;
	}

}
