package be.tbs.mgf.endpoint;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import be.tbs.mgf.authentication.Authenticator;
import be.tbs.mgf.database.DataStoreConnection;
import be.tbs.mgf.database.google.GoogleDataStoreConnection;
import be.tbs.mgf.database.google.data.PlayerData;
import be.tbs.mgf.games.ReservedWords;
import be.tbs.mgf.games.player.Player;
import be.tbs.mgf.games.player.RosterException;
import be.tbs.mgf.games.session.status.StatusUpdate;
import be.tbs.mgf.schemas.Error;
import be.tbs.mgf.schemas.JoinReply;
import be.tbs.mgf.schemas.JoinRequest;
import be.tbs.mgf.schemas.LeaveReply;
import be.tbs.mgf.schemas.LeaveRequest;
import be.tbs.mgf.schemas.Message;
import be.tbs.mgf.schemas.PlayerDescriptor;
import be.tbs.mgf.schemas.Pong;
import be.tbs.mgf.schemas.StatusProperty;
import be.tbs.mgf.schemas.StatusReply;
import be.tbs.mgf.schemas.StatusRequest;
import be.tbs.mgf.synchronization.PingPongManager;
import be.tbs.mgf.synchronization.SynchronizationException;

/**
 * Endpoint for the gaming service.
 * Only implemented using JAXB classes at this time.
 * This class is meant to be inherited by specific
 * web service implementation endpoints.
 * @author Gino
 */
public class MGFEndpoint {
	
	private static int fVersion = 1;
	
	private static DataStoreConnection fDBC = new GoogleDataStoreConnection();
	
	private static Logger fLog = Logger.getLogger(MGFEndpoint.class.getName());
	
	/**
	 * Check the version compatibility of both parties.
	 * @param request
	 * @param reply
	 * @throws VersionException
	 */
	public void checkVersion(Message request, Message reply) throws VersionException {
		reply.setVersion(fVersion);
		if (request.getVersion() != fVersion) {
			throw new VersionException(request.getVersion(), fVersion);
		}
	}
	
	
	public JoinReply doJoin(JoinRequest request) {
		JoinReply reply = new JoinReply();
		
		// --1-- Version control
		try {
			checkVersion(request, reply);
		} catch (VersionException e) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(e.getErrorCode());
			error.setDescription(e.getDescription());
			reply.setError(error);
			return reply;
		}
		
		// --1b-- Check for capacity
		List<PlayerData> players = (List<PlayerData>) fDBC.getAll(PlayerData.class.getName());
		if (players.size() >= getCapacity()) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(820);
			error.setDescription("Server is full");
			reply.setError(error);
			return reply;
		}
		
		// --2-- Authenticate Player
		Error authError = Authenticator.authenticate(request.getPlayer());
		if (authError != null) {
			Pong authPong = PingPongManager.generatePong(request.getPing());
			reply.setPong(authPong);
			reply.setError(authError);
			return reply;
		}
		
		// --3-- Create Player
		Player player;
		try {
			player = new Player(request.getPlayer().getPlayerName(), request.getPing().getTimeStamp().toGregorianCalendar().getTime());
		} catch (RosterException e) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(e.getErrorCode());
			error.setDescription(e.getDescription());
			reply.setError(error);
			return reply;
		}
		PlayerDescriptor playerDesc = new PlayerDescriptor();
		playerDesc.setPlayerID(player.getId());
		reply.setPlayer(playerDesc);
		
		// --4-- Synchronization
		Pong pong = PingPongManager.pingUnchecked(player, request.getPing());
		reply.setPong(pong);
		
		// --5-- Save a status update for this event.
		StatusUpdate su = new StatusUpdate(player.getId(), player.getLastStamp(), "player-join", player.getName());
		
		// --6-- Done
	
		return reply;
	}
	
	public StatusReply doStatus(StatusRequest request) {
		StatusReply reply = new StatusReply();
		
		// --1-- Version control
		try {
			checkVersion(request, reply);
		} catch (VersionException e) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(e.getErrorCode());
			error.setDescription(e.getDescription());
			reply.setError(error);
			return reply;
		}
		
		// --2-- Get the player
		// TODO : Check if this player has joined already, maybe through authenticator
		Player player = new Player((be.tbs.mgf.database.data.PlayerData) fDBC.getObjectByID(PlayerData.class, request.getPlayer().getPlayerID()+""));
		Date previous_timestamp = player.getLastStamp();
		
		// --3-- Ping pong
		try {
			Pong pong = PingPongManager.ping(player, request.getPing());
			reply.setPong(pong);
		} catch (SynchronizationException e) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(e.getErrorCode());
			error.setDescription(e.getDescription());
			reply.setError(error);
			return reply;
		}
		
		List<StatusProperty> replySPs = reply.getProperty();
		StatusProperty st = request.getProperty();
		if (st.getName().equalsIgnoreCase(ReservedWords.STATUS_REQUEST_UPDATES)) {

			// --4-- Get statuses needed for this player
			List<StatusUpdate> statuses = StatusUpdate.getAllSince(previous_timestamp);
			for (StatusUpdate statusUpdate : statuses) {
				StatusProperty sp = new StatusProperty();
				sp.setName(statusUpdate.getProperty());
				sp.setValue(statusUpdate.getValue());
				sp.setPlayer(statusUpdate.getPlayer());
				replySPs.add(sp);
			}
			
		} else if (st.getName().equalsIgnoreCase(ReservedWords.STATUS_REQUEST_PLAYERS)) {
			List<PlayerData> players = (List<PlayerData>) fDBC.getAll(PlayerData.class.getName());
			for (PlayerData playerData : players) {
				StatusProperty sp = new StatusProperty();
				sp.setName("player");
				sp.setValue(playerData.getName());
				sp.setPlayer(playerData.getId());
				replySPs.add(sp);
			}
		} else {
			// --5-- Apply the status in the request / TODO : check if this is allowed?
			StatusUpdate su = new StatusUpdate(player.getId(),player.getLastStamp(), st.getName(), st.getValue());
		}
				
		return reply;
	}
	
	public LeaveReply doLeave(LeaveRequest request) {
		LeaveReply reply = new LeaveReply();
		
		// --1-- Version control
		try {
			checkVersion(request, reply);
		} catch (VersionException e) {
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(e.getErrorCode());
			error.setDescription(e.getDescription());
			reply.setError(error);
			return reply;
		}
		
		// --2-- Check if specified player exists
		List<PlayerData> player = (List<PlayerData>) fDBC.getAll(PlayerData.class.getName(), "playerId", ""+request.getPlayer().getPlayerID());
		
		if (player.size() != 1) {
			fLog.warning("While executing a leave operation, found " + player.size() + " players matching the leaving player " + request.getPlayer().getPlayerID());
			Pong pong = PingPongManager.generatePong(request.getPing());
			reply.setPong(pong);
			Error error = new Error();
			error.setCode(910);
			error.setDescription("No single record of this player could be found");
			reply.setError(error);
			return reply;
		}
		
		// --3-- Delete specified player
		PlayerData pd = player.get(0);
		Player p = new Player(pd);
		reply.setPong(PingPongManager.pingUnchecked(p, request.getPing()));
		reply.setPlayerID(p.getId());
		fDBC.delete(pd);
		
		// --4-- Save a status update for this event.
		StatusUpdate su = new StatusUpdate(p.getId(), p.getLastStamp(), "player-leave", p.getName());
		return reply;
	}
	
	/**
	 * Gets this server's capacity for players.
	 * @return int - The maximum number of players allowed on this server.
	 */
	public int getCapacity() {
		return 4;
	}

}
