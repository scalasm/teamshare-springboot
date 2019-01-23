package it.linksmt.teamshare.architecture.web;

import java.util.List;

public class ValidationErrorDetails extends ErrorDetails {
	private final String message;
	private final String details;

	private final List<GlobalValidationError> globalValidationErrors;
	private final List<FieldValidationError> fieldValidationErrors;
	
	public ValidationErrorDetails( String message, String details,
			List<GlobalValidationError> globalValidationErrors, List<FieldValidationError> fieldValidationErrors ) {
		super( message, details );
		
		this.message = message;
		this.details = details;
		this.globalValidationErrors = globalValidationErrors;
		this.fieldValidationErrors = fieldValidationErrors;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public List<GlobalValidationError> getGlobalValidationErrors() {
		return globalValidationErrors;
	}

	public List<FieldValidationError> getFieldValidationErrors() {
		return fieldValidationErrors;
	}
}