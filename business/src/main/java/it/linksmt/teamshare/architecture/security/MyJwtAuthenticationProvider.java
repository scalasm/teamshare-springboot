/*******************************************************************************
 * Copyright (c) 2018 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * Gestisce le richieste di autenticazione relative al {@link MyJwtAuthentication} producendo, in caso positivo, un {@link MyAuthenticationToken}.
 * 
 * @author Mario Scalas
 */
@Component
public class MyJwtAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserSessionManager sessionManager;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
		MyJwtAuthentication jwt = (MyJwtAuthentication) authentication;	
	
		jwtHelper.verify( jwt.getToken() );
		
		MyUserDetails userDetails = sessionManager.getSessionByJwt( jwt.getToken() );
		
		MyAuthenticationToken authToken = null;
		if (userDetails != null) {
			authToken = new MyAuthenticationToken( userDetails );
		}
		
		return authToken;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports( Class<?> authentication ) {
		return MyJwtAuthentication.class.isAssignableFrom( authentication );
	}
}
