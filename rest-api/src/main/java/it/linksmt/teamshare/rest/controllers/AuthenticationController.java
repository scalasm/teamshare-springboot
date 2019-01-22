/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.linksmt.teamshare.business.request.LoginByEmailAndPasswordDto;
import it.linksmt.teamshare.business.services.impl.AuthenticationService;
import it.linksmt.teamshare.business.services.impl.UserAuthenticationDto;

/**
 * @author mario
 */
@RestController
@RequestMapping( "/public/authentication" )
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping( "/login" )
	public UserAuthenticationDto login( @RequestBody LoginByEmailAndPasswordDto request ) {
		return authenticationService.login( request );
	}
}
