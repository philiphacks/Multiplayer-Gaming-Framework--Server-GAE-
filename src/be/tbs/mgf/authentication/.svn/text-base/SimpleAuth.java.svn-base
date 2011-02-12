package be.tbs.mgf.authentication;

import be.tbs.mgf.schemas.Error;
import be.tbs.mgf.schemas.PlayerDescriptor;

/**
 * @author Gino
 */
public class SimpleAuth implements AuthMethod {
	
	private Error fError = null;
	
	public SimpleAuth() {
		
	}
	
	@Override
	public Error authenticate(PlayerDescriptor playerDesc) {
		// The descriptor needs to contain a valid name or a valid ID
		// It can't contain both
		fError = null;
		String playerName = playerDesc.getPlayerName();
		Integer id = playerDesc.getPlayerID();
		if (playerName != null) {
			// Name but no ID
			// Valid if length > 1
			if (playerName.length()<2) {
				fError = new Error();
				fError.setCode(801);
				fError.setDescription("Name must contain at least 2 characters.");
			}
		} else {
			// ID but no name
			// Valid if positive
			if (id < 0) {
				fError = new Error();
				fError.setCode(811);
				fError.setDescription("Player ID is strictly negative, should be positive");
			}
		}
		return fError;
	}

}
