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
 *
 */
public class MyApplicationException extends RuntimeException {

	public MyApplicationException() {
		super();
	}

	public MyApplicationException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
		super( message, cause, enableSuppression, writableStackTrace );
	}

	public MyApplicationException( String message, Throwable cause ) {
		super( message, cause );
	}

	public MyApplicationException( String message ) {
		super( message );
	}

	public MyApplicationException( Throwable cause ) {
		super( cause );
	}
}
