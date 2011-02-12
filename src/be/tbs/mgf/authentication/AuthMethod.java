package be.tbs.mgf.authentication;

import be.tbs.mgf.schemas.Error;
import be.tbs.mgf.schemas.PlayerDescriptor;

/**
 * Provides an interface for an authentication method.
 * Authentication Methods use the {@link PlayerDescriptor}
 * to determine the status of a player.
 * @author Gino
 */
public interface AuthMethod {
	
	/**
	 * Authenticates a player.
	 * @param playerDesc Description of a player according to the MGF protocol.
	 * @return An {@link Error} if the player was not succesfully authenticated, null if authentication succeeded.
	 */
	public Error authenticate(PlayerDescriptor playerDesc);
}
