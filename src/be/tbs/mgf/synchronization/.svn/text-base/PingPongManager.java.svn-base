package be.tbs.mgf.synchronization;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;
import be.tbs.mgf.database.google.data.PlayerData;
import be.tbs.mgf.games.player.Player;
import be.tbs.mgf.schemas.Ping;
import be.tbs.mgf.schemas.Pong;

/**
 * Ping Pong Manager
 * Keeps track of players' ping pong messages.
 * @author Gino
 */
public class PingPongManager {

	private static DataStoreConnection fDBC = new GoogleDataStoreConnection();
	
	private static Logger fLog = Logger.getLogger(PingPongManager.class.getName());
	
	private PingPongManager() {}
	
	/**
	 * Updates the last timestamp seen for this player by the provided one.
	 * Performs no checks for timeouts, this can thus be used when a ping is received on
	 * join and leave requests. <b>The player has to have already joined the game for this
	 * call to be valid.</b>
	 * @param player - the pinging player
	 * @param ping - the ping
	 * @return pong
	 */
	public static Pong pingUnchecked(Player player, Ping ping) {
		player.setLastStamp(ping.getTimeStamp().toGregorianCalendar().getTime());
		return generatePong(ping);
	}
	
	/**
	 * Updates the last timestamp seen for this player by the provided one.
	 * Performs some checks for timeouts and clock de-sync. <b>The player has to have already joined the game for this
	 * call to be valid.</b>
	 * @param player - the pinging player
	 * @param ping - the ping
	 * @return pong
	 * @throws SynchronizationException - On timeout or clock desync
	 */
	public static Pong ping(Player player, Ping ping) throws SynchronizationException {
		Date last_stamp = getLastTimeStamp(player.getId());
		Date new_stamp = ping.getTimeStamp().toGregorianCalendar().getTime();
		// First check if the new stamp is newer than the last_stamp
		if (new_stamp.after(last_stamp)) {
			if ((new_stamp.getTime()-last_stamp.getTime())/1000 > 60) {
				// Last timestamp was more than 60 seconds ago. Time out.
				throw new SynchronizationException(player.getId());
			} else {
				// No timeout, generate pong.
				player.setLastStamp(ping.getTimeStamp().toGregorianCalendar().getTime());
				return generatePong(ping);
			}
		} else {
			// There seems to be a conflict between clocks / time changed during gameplay / we're dealing with a timetraveller
			throw new SynchronizationException(player.getId(), 702, "A clock synchronization conflict caused the player to lose sync");
		}
	}

	/**
	 * Generates a pong as a reply to an incoming ping.
	 * This should only be used when the player could not join the game.
	 * This won't update any information in the datastore.
	 * @param ping - the incoming ping
	 * @return pong
	 */
	public static Pong generatePong(Ping ping) {
		Pong pong = new Pong();
		try {
			pong.setTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		} catch (DatatypeConfigurationException e) {
			fLog.severe("Encountered a DatatypeConfigurationException while generating a Pong");
		}
		pong.setRequestStamp(ping.getTimeStamp());
		return pong;
	}
	
	
	protected static Date getLastTimeStamp(int player) throws SynchronizationException {
		List<PlayerData> players = (List<PlayerData>) fDBC.getAll(PlayerData.class.getName(),"playerId" , player+"");
		if (players.size() != 1) {
			throw new SynchronizationException(player,701,"No single record for this player was found.");
		}
		PlayerData pd = players.get(0);
		return pd.getLast_stamp();
	}

}
