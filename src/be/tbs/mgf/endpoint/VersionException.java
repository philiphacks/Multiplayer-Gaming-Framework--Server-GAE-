package be.tbs.mgf.endpoint;

import be.tbs.mgf.error.FrameworkException;

/**
 * Signifies that version incompatibility has occured.
 * The framework and request versions did not match.
 * @author Gino
 */
public class VersionException extends FrameworkException {
	
	/**
	 * Generated SUID
	 */
	private static final long serialVersionUID = 3284736472244472869L;
	private int fClient;
	private int fServer;

	/**
	 * Creates a new {@link VersionException} with the given client and server versions.
	 * @param clientVersion - The version of MGF running on the client.
	 * @param serverVersion - The version of MGF running on the server.
	 */
	public VersionException(int clientVersion, int serverVersion) {
		super(101,"Version mismatch. Client version: " + clientVersion + " Server version: " + serverVersion);
		this.fClient = clientVersion;
		this.fServer = serverVersion;
	}
	
	/**
	 * Gets the client's version of the MGF
	 * @return strictly positive integer
	 */
	public int getClientVersion() {
		return fClient;
	}
	
	/**
	 * Gets the server's version of the MGF
	 * @return strictly positive integer
	 */
	public int getServerVersion() {
		return fServer;
	}

}
