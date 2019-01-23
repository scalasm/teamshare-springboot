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
 * Dettaglio dell'errore specifico per un particolare campo (e.g., not null).
 * 
 * @author mario
 */
@ApiModel( description = "Un messaggio di errore di validazione su uno specifico campo" )
public class FieldValidationError {
	@ApiModelProperty( value = "Il nome del campo", accessMode = AccessMode.READ_ONLY )
	private final String field;

	@ApiModelProperty( value = "Il messaggio di errore (già internazionalizzato)", accessMode = AccessMode.READ_ONLY )
	private final String errorMessage;

	public FieldValidationError( final String field, final String errorMessage ) {
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}