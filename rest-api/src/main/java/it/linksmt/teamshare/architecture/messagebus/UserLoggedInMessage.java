/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus;

/**
 * @author mario
 *
 */
public class UserLoggedInMessage {
	private final Integer userId;
	
	private final String userName;
	
	public UserLoggedInMessage( Integer userId, String userName ) {
		this.userId = userId;
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}
}
