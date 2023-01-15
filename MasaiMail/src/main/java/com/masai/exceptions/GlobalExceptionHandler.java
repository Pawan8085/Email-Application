package com.masai.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//bookExceptionHandler
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<MyErrorDetails> emailExceptionHandler(EmailException ee, WebRequest wr){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ee.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	//allExceptionhHandler
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception e, WebRequest wr){
		
		MyErrorDetails err=new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	//allExceptionhHandler
		@ExceptionHandler(NoHandlerFoundException.class)
		public ResponseEntity<MyErrorDetails> NoHandlerFoundExceptionHandler(NoHandlerFoundException ne, WebRequest wr){
			
			MyErrorDetails err=new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ne.getMessage());
			err.setDetails(wr.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		//validationExceptionHandleR
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<MyErrorDetails> MANVEHandlder(MethodArgumentNotValidException manve){
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage("Validation Error");
			err.setDetails(manve.getBindingResult().getFieldError().getDefaultMessage());
			
			return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
		}
}
