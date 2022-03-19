package com.cg.aps.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.aps.exception.GuardShiftNameNotFoundException;
import com.cg.aps.exception.GuardShiftUserIdNotFoundException;


@ControllerAdvice
public class GuardShiftControllerAdvice {
	
	@ExceptionHandler(value = GuardShiftUserIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleGuardShiftUserIdNotFoundException(GuardShiftUserIdNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guardshift id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = GuardShiftNameNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleGuardShiftNameNotFoundException(GuardShiftNameNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guardshift name");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
	
	
	
	

}