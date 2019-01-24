/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus;

import java.util.Date;

/**
 * @author mario
 *
 */
public class UserLoggedInMessage {

	private final Date timestamp;
	
	private Integer userId;
	
	private String userName;
	
	public UserLoggedInMessage( Integer userId, String userName ) {
		this.timestamp = new Date();
		this.userId = userId;
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId( Integer userId ) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
