/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * DTO per gli errori di validazione relativi allo stato globale (non ad un particolare campo).
 * 
 * @author mario
 */
@ApiModel( description = "Un messaggio di errore generico per un oggetto intero" )
public class GlobalValidationError {
	@ApiModelProperty( value = "Il nome del campo", accessMode = AccessMode.READ_ONLY )
	private final String errorMessage;

	public GlobalValidationError( String errorMessage ) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}