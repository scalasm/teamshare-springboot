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
public final class ApplicationTopics {
	public static final String ROOT_TOPIC = "/events";
	
	public static final String USER_LOGGED_IN = ROOT_TOPIC + "/UserLoggedIn";

}
