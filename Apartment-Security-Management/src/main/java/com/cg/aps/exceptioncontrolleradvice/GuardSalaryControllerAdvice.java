package com.cg.aps.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.aps.exception.GuardSalaryNameNotFoundException;
import com.cg.aps.exception.GuardSalaryUserIdNotFoundException;

@ControllerAdvice
public class GuardSalaryControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = GuardSalaryUserIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleGuardSalaryUserIdNotFoundException(GuardSalaryUserIdNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guardsalary id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(value = GuardSalaryNameNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleGuardShiftNameNotFoundException(GuardSalaryNameNotFoundException de) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong guardsalary name");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", de.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	}

}