/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.messagebus.impl;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.util.Assert;

import it.linksmt.teamshare.architecture.messagebus.ApplicationTopics;
import it.linksmt.teamshare.architecture.messagebus.MessageBus;

/**
 * Un facade che permette di inviare messaggi ai client semplificando l'uso dei web socket.
 * 
 * @author mario
 */
public class MessageBusImpl implements MessageBus {
	private static final Logger LOG = LoggerFactory.getLogger( MessageBusImpl.class );
	
	public static class Message {
		private final Date timestamp;
		private final String type;
		private final Object payload;

		public Message( Object payload ) {
			this.timestamp = new Date();
			this.type = payload.getClass().getSimpleName();
			this.payload = payload;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public String getType() {
			return type;
		}

		public Object getPayload() {
			return payload;
		}
	}
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;

	private String defaultTopic;

	public void setDefaultTopic( String defaultTopic ) {
		this.defaultTopic = defaultTopic;
	}
	
	/* (non-Javadoc)
	 * @see it.linksmt.teamshare.eventbus.MessageBus#broadcast(java.lang.String, java.lang.Object)
	 */
	@Override
	public void broadcast( @NotEmpty String destinationTopic, @NotNull Object what ) {
		messagingTemplate.convertAndSend( destinationTopic, new Message( what ) );
	}
	
	/* (non-Javadoc)
	 * @see it.linksmt.teamshare.eventbus.MessageBus#broadcast(java.lang.Object)
	 */
	@Override
	public void broadcast( @NotNull Object what ) {
		Assert.notNull( defaultTopic, "No default topic defined!" );

		String eventTopic = ApplicationTopics.ROOT_TOPIC + "/" + what.getClass().getSimpleName();
		
		broadcast( eventTopic, what );
	}
}
