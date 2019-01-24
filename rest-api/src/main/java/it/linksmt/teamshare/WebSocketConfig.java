/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import it.linksmt.teamshare.architecture.messagebus.MessageBus;
import it.linksmt.teamshare.architecture.messagebus.impl.MessageBusImpl;

/**
 * WebSocket configuration.
 *  
 * @author mario
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	public static final String ROOT_TOPIC = "/events";

	@Bean
	public MessageBus messageBus() {
		MessageBusImpl bus = new MessageBusImpl();
		bus.setRootTopic( ROOT_TOPIC );
		
		return bus;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
	 */
	@Override
	public void configureMessageBroker( MessageBrokerRegistry config ) {
		config.enableSimpleBroker( ROOT_TOPIC );
		config.setApplicationDestinationPrefixes( "/ts" ); // ts == TeamShare
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints( StompEndpointRegistry registry ) {
		registry.addEndpoint("/ws")
		.setHandshakeHandler( defaultHandshakeHandler())
			.setAllowedOrigins( "*" );
		
		registry.addEndpoint( "/ws" )
			.setAllowedOrigins( "*" )
			.setHandshakeHandler( defaultHandshakeHandler() )
			.withSockJS();
	}

	@Bean
	public DefaultHandshakeHandler defaultHandshakeHandler() {
		return new DefaultHandshakeHandler() {
			@SuppressWarnings( { "unused", "unchecked" } )
			public boolean beforeHandshake( ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @SuppressWarnings( "rawtypes" ) Map attributes )
					throws Exception {
				if (request instanceof ServletServerHttpRequest) {
					ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
					HttpSession session = servletRequest.getServletRequest().getSession();
					attributes.put( "sessionId", session.getId() );
				}
				return true;
			}
		};
	}
}