package com.cg.aps.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.aps.exception.DeliveryIdNotFoundException;

@ControllerAdvice
public class DeliveryControllerAdvice extends ResponseEntityExceptionHandler{
	@ExceptionHandler(value = DeliveryIdNotFoundException.class) 
	public ResponseEntity<?> handleDeliveryIdNotFoundException(DeliveryIdNotFoundException de) {
		System.out.println("Controller advice is executed when exception is thrown ***");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong delivery id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	// more handler methods.


}