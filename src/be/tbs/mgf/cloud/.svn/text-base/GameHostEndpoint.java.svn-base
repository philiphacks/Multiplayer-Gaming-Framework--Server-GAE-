package be.tbs.mgf.cloud;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import be.tbs.mgf.authentication.Authenticator;
import be.tbs.mgf.database.PMF;
import be.tbs.mgf.endpoint.MGFEndpoint;
import be.tbs.mgf.schemas.Error;
import be.tbs.mgf.schemas.JoinReply;
import be.tbs.mgf.schemas.JoinRequest;
import be.tbs.mgf.schemas.LeaveReply;
import be.tbs.mgf.schemas.LeaveRequest;
import be.tbs.mgf.schemas.PlayerDescriptor;
import be.tbs.mgf.schemas.StatusProperty;
import be.tbs.mgf.schemas.StatusReply;
import be.tbs.mgf.schemas.StatusRequest;
import be.tbs.mgf.synchronization.PingPongManager;

/**
 * @author Gino Wuytjens
 *
 * The endpoint for all requests to the virtual game server.
 * Contains the Join, Status and Leave methods.
 */
@Endpoint
public class GameHostEndpoint extends MGFEndpoint {
	
	public static final String GAME_JOIN_ACTION = "http://3bstest.appspot.com/Join";
	public static final String GAME_STATUS_ACTION = "http://3bstest.appspot.com/Status";
	public static final String GAME_LEAVE_ACTION = "http://3bstest.appspot.com/Leave";
		
	@Autowired
	Marshaller marshaller;
	
	@SoapAction(GAME_JOIN_ACTION)
	public JoinReply join(JoinRequest joinRequest) throws DatatypeConfigurationException {
		return doJoin(joinRequest);
	}
	
	@SoapAction(GAME_STATUS_ACTION)
	public StatusReply status(StatusRequest statusRequest) throws DatatypeConfigurationException {
		return doStatus(statusRequest);
//		
//		// Perform Framework Tasks
//		StatusReply reply = new StatusReply();
//		reply.setVersion(1);
//		
//		reply.setPong(PingPongManager.ping(DataStoreQueries.getPlayerName(statusRequest.getPlayer().getPlayerID()), statusRequest.getPing()));
//
//		// Framework data added to reply.
//		try {
//			
//			/// Authentication
//			// TODO: Fix Initialization of Authenticator through application context!
////			
////			Error authError = doAuth(statusRequest.getPlayerID());
////			if (authError != null) {
////				// Player is not authenticated.
////				reply.setError(authError);
////				return reply;
////			}
//			
//			handleStatus(statusRequest, reply);
//			
//			StatusManager.addStatusProperty(statusRequest.getPlayer().getPlayerID(), statusRequest.getProperty().getName(), statusRequest.getProperty().getValue(), statusRequest.getPing().getTimeStamp().toGregorianCalendar().getTime());
//			
//			
//			
//			//Error requestError = StatusManager.checkRequest(statusRequest.getProperty());
//			//if (requestError != null) {
//				// Property was not found in the datastore
//		//		reply.setError(requestError);
//			//	return reply;
//			//}
//			//reply.setProperty(StatusManager.getProperty(statusRequest.getProperty()));
//			
//			return reply;
//			
//		} catch (Exception e) {
//			Error exception = new Error();
//			exception.setCode(0);
//			exception.setDescription(e.getLocalizedMessage() + "\n" + getStackTrace(e));
//			reply.setError(exception);
//			return reply;
//		}
	}

	@SoapAction(GAME_LEAVE_ACTION)
	public LeaveReply leave(LeaveRequest leaveRequest) throws DatatypeConfigurationException {
		return doLeave(leaveRequest);
//		
//		// Perform Framework Tasks
//		LeaveReply reply = new LeaveReply();
//		reply.setVersion(1);
//		
//		int pID = leaveRequest.getPlayer().getPlayerID();
//		reply.setPong(PingPongManager.leave(DataStoreQueries.getPlayerName(pID), leaveRequest.getPing()));
//		
//		try {
//			
//			// verwijderen player van datastore
//			Error remError = doRemove(leaveRequest.getPlayer().getPlayerID());
//			if (remError != null) {
//				// Player is not removed database of players
//				reply.setError(remError);
//				return reply;
//			}
//			
//			reply.setPlayerID(leaveRequest.getPlayer().getPlayerID());
//			return reply;
//		
//		} catch (Exception e) {
//			Error exception = new Error();
//			exception.setCode(0);
//			exception.setDescription(e.getLocalizedMessage() + "\n" + getStackTrace(e));
//			reply.setError(exception);
//			return reply;
//		}
	}

	// DEBUG
	protected static String getStackTrace(Throwable aThrowable) {
	    final Writer result = new StringWriter();
	    final PrintWriter printWriter = new PrintWriter(result);
	    aThrowable.printStackTrace(printWriter);
	    return result.toString();
	  }
}
