/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture;

/**
 * @author mario
 */
public class MySecurityException extends MyApplicationException {

	public MySecurityException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public MySecurityException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MySecurityException( String message, Throwable cause ) {
		super( message, cause );
	}

	/**
	 * @param message
	 */
	public MySecurityException( String message ) {
		super( message );
	}

	/**
	 * @param cause
	 */
	public MySecurityException( Throwable cause ) {
		super( cause );
	}
}
