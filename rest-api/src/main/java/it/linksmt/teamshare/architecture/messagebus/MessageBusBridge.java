/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import it.linksmt.teamshare.business.events.EntityCreatedEvent;
import it.linksmt.teamshare.business.events.UserLoggedInEvent;

/**
 * Un ponte tra gli {@link ApplicationEvent} definiti internamente nel backend e i messaggi inviati dal backend ai client utilizzando i web socket.
 * 
 * @author mario
 */
@Component
public class MessageBusBridge {
	@Autowired
	private MessageBus bus;
	
	@EventListener
	public void onUserLoggedIn( UserLoggedInEvent event ) {
		String fullName = event.getSource().getNome() + " " + event.getSource().getCognome();
		
		bus.broadcast( new UserLoggedInMessage( event.getSource().getId(), fullName ) );
	}

	@EventListener
	public void onEntityCreated( EntityCreatedEvent event ) {
		bus.broadcast( new EntityCreatedMessage( event.getSource() ) );
	}
}
