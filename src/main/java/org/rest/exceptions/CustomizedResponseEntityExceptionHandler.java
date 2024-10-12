package org.rest.exceptions;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	/** Exceptions genéricas, herda de ResponseEntityExceptionHandler
	 para sobrescrever e adicionar descriçoes detalhadas na exception */
	
	@ExceptionHandler(Exception.class)

	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(), //timeStamp
			"Resource not found please check this url ;) "+ ex.getMessage(), //message
			request.getDescription(false));//details
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);/**  HttpStatus 500 */
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)

	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
			new Date(),//timeStamp
			"Resource not found " + ex.getMessage(), //message
			request.getDescription(false));//details
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND); /**  HttpStatus 400 with body */
	}

}
