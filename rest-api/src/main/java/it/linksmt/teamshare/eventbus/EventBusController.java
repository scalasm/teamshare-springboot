/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.eventbus;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import it.linksmt.teamshare.business.services.events.UserLoggedInEvent;

/**
 * @author mario
 */
@Controller
public class EventBusController {
	
	@EventListener
	@SendTo( "/events/UserLoggedIn" )
	public UserLoggedInMessage onUserLoggedIn( UserLoggedInEvent event ) {
		String fullName = event.getSource().getNome() + " " + event.getSource().getCognome();
		
		return new UserLoggedInMessage( event.getSource().getId(), fullName );
	}
}
