package it.linksmt.teamshare.architecture.web;

import java.util.Date;

public class ErrorDetails {
	private final Date timestamp;
	private final String message;
	private final String details;

	public ErrorDetails( String message, String details ) {
		super();
		this.timestamp = new Date();
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}