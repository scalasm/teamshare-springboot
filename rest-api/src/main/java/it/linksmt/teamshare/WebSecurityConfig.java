/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.linksmt.teamshare.security.JWTSecurityInterceptor;

/**
 * Configurazione della sicurezza web.
 * 
 * @author mario
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
 
	@Autowired
	private JWTSecurityInterceptor jwtSecurityInterceptor;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors( InterceptorRegistry registry ) {
		WebMvcConfigurer.super.addInterceptors( registry );
	
		registry.addInterceptor( jwtSecurityInterceptor );
	}
	
	/* (non-Javadoc)
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
     */
    @Override
    public void addCorsMappings( CorsRegistry registry ) {
        registry
				.addMapping( "/**" )
				.allowedMethods( "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH" );
    }
}