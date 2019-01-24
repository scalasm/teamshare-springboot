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

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.util.Assert;

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
	
	private String rootTopic;

	@Autowired
	public void setRootTopic( String rootTopic ) {
		this.rootTopic = rootTopic;
	}
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;

	@PostConstruct
	public void checkConfiguration() {
		Assert.notNull( rootTopic, "Root topic not set: please configure it!" );
	}
	
	/* (non-Javadoc)
	 * @see it.linksmt.teamshare.eventbus.MessageBus#broadcast(java.lang.String, java.lang.Object)
	 */
	@Override
	public void broadcast( @NotEmpty String destinationTopic, @NotNull Object what ) {
		Assert.isTrue(  destinationTopic.startsWith( rootTopic ), 
				String.format( "Destination topic must begin with configured root topic (%s)", rootTopic ) );

		Message message = new Message( what );

		LOG.debug( "Broadcasting event {} to topic {} ...", message.getType(), destinationTopic );
		
		messagingTemplate.convertAndSend( destinationTopic, message );
	}
	
	/* (non-Javadoc)
	 * @see it.linksmt.teamshare.eventbus.MessageBus#broadcast(java.lang.Object)
	 */
	@Override
	public void broadcast( @NotNull Object what ) {
		String eventTopic = rootTopic + "/" + what.getClass().getSimpleName();
		
		broadcast( eventTopic, what );
	}
}
