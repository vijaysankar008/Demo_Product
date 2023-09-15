package com.nandha.Nandha.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
//ControllerAdvice Annotation
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> productNotFound(ProductNotFoundException exception){
		ResponseEntity<String> entity= new ResponseEntity<>("Product is Not Found which you have Searching",HttpStatus.BAD_REQUEST);
		return entity;
	}
	@ExceptionHandler(InvalidHeadersFileException.class)
	public ResponseEntity<String> invalidHeaders(InvalidHeadersFileException exception){
		return new ResponseEntity<>("Headers Are Not Found",HttpStatus.BAD_REQUEST);
	}
	
}
