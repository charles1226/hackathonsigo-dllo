package com.siigo.invoice.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppException(Exception ex, WebRequest request) {
		logger.error("Error: ", ex);
		String errorMessageDesccription = ex.getLocalizedMessage();
		
		if(errorMessageDesccription == null) errorMessageDesccription = ex.toString();
		
		ErrorResponse errorMessage = ErrorResponse.of(errorMessageDesccription, ErrorCode.GLOBAL, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(Exception ex, WebRequest request) {
		logger.error("Error: ", ex);
		String errorMessageDesccription = ex.getLocalizedMessage();
		
		if(errorMessageDesccription == null) errorMessageDesccription = ex.toString();
		
		ErrorResponse errorMessage = ErrorResponse.of(errorMessageDesccription, ErrorCode.GLOBAL, HttpStatus.UNPROCESSABLE_ENTITY);
		return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
}
