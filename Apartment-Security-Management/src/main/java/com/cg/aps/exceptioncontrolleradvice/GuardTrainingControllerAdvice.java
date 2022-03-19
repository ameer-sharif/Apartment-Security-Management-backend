package com.cg.aps.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.aps.exception.GuardTrainingNameNotFoundException;
import com.cg.aps.exception.GuardTrainingUserIdNotFoundException;

@ControllerAdvice
public class GuardTrainingControllerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value = GuardTrainingUserIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleUserIdNotFoundException(GuardTrainingUserIdNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guard id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = GuardTrainingNameNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleGuardTrainingNameNotFoundException(GuardTrainingNameNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guard name ");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
	
	
}