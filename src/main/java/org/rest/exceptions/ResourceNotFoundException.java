package org.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//404 Simple Exception without body
public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException(String ex) {
		//Constructor with Postalization message
		super(ex); //class pai
	}
	
}
