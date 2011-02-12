package be.tbs.mgf.authentication;

import be.tbs.mgf.schemas.Error;
import be.tbs.mgf.schemas.PlayerDescriptor;

/**
 * @author Gino
 * Authenticates players
 */
public class Authenticator {
	
	
	private static AuthMethod _method = new SimpleAuth();
	
	private Authenticator() {
	}
	/*
	public static void setAuthMethod(AuthMethod method) {
		_method = method;
	}*/
		
	public static void setAuthMethod(String method) {
		if (method.equalsIgnoreCase("simple"))
			_method = new SimpleAuth();
	}
	
	public static String getAuthMethod() {
		if (_method instanceof SimpleAuth) {
			return "simple";
		} 
//		else if (_method instanceof GoogleAuth) {
//			
//		}
		return "null";
	}
	
	public static Error authenticate(PlayerDescriptor playerDesc) {
		return _method.authenticate(playerDesc);
	}
}
