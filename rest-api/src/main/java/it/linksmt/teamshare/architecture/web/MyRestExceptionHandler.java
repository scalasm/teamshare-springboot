/*******************************************************************************
 * Copyright (c) 2019 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.linksmt.teamshare.architecture.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import it.linksmt.teamshare.architecture.MyApplicationException;
import it.linksmt.teamshare.architecture.MySecurityException;

/**
 * Gestione centralizzata degli errori: possiamo ridefinire qui gli handler per i vari tipi di eccezioni.
 * 
 * @author mario
 */
@ControllerAdvice
public class MyRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status,
			WebRequest request ) {
		return new ResponseEntity<>( createValidationErrorDetails( e ), HttpStatus.BAD_REQUEST );
	}
	
	@ExceptionHandler( { MyApplicationException.class } )
	public ResponseEntity<Object> onApplicationException( MyApplicationException e, WebRequest request ) {
		return handleExceptionInternal( e, createDefaultErrorDetails( e ), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request );
	}

	@ExceptionHandler( { MySecurityException.class } )
	public ResponseEntity<Object> onSecurityException( MySecurityException e, WebRequest request ) {
		return handleExceptionInternal( e, createDefaultErrorDetails( e ), new HttpHeaders(), HttpStatus.FORBIDDEN, request );
	}
	
	private ErrorDetails createDefaultErrorDetails( Exception e ) {
		return new ErrorDetails( e.getClass().getSimpleName(), e.getMessage() );
	}
	
	private ValidationErrorDetails createValidationErrorDetails( MethodArgumentNotValidException e ) {
		BindingResult bindingResult = e.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		List<ObjectError> objectErrors = bindingResult.getGlobalErrors();

		List<FieldValidationError> fieldValidationErrors = fieldErrors.stream()
				.map( fe -> toFieldError( fe ) )
				.collect( Collectors.toList() );

		List<GlobalValidationError> globalMessages = objectErrors.stream()
				.map( ge -> toGlobalValidationError( ge ) )
				.collect( Collectors.toList() );
		
		return new ValidationErrorDetails( "Validation failed", e.getMessage(), globalMessages, fieldValidationErrors );
	}
	
	private GlobalValidationError toGlobalValidationError( ObjectError fe ) {
		String errorMessage = messageSource.getMessage( fe.getCode(), fe.getArguments(), LocaleContextHolder.getLocale() );
		
		return new GlobalValidationError( errorMessage );
	}
	
	private static FieldValidationError toFieldError( FieldError fe ) {
		// Nota: se volete il messaggio in testo libero (es. ""must not be blank") usate la riga commentata invece
//		String validationErrorCode = hasText( fe.getDefaultMessage() ) ? fe.getDefaultMessage() : fe.getCode();

		String validationErrorCode = fe.getCodes()[0];
		
		return new FieldValidationError( fe.getField(), validationErrorCode );
	}

	private static List<String> toStrings( Object[] arguments ) {
		List<String> stringArgs = new ArrayList<>();
		if (arguments != null) {
			stringArgs = Arrays.stream( arguments ).map( (o) -> o.toString() ).collect( Collectors.toList() );
		}
		return stringArgs;
	}
}
