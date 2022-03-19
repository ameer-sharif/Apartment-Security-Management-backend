package com.cg.aps.exceptioncontrolleradvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.aps.exception.FlatIdNotFoundException;

@ControllerAdvice
public class FlatControllerAdvice extends ResponseEntityExceptionHandler  {

	@ExceptionHandler(value = FlatIdNotFoundException.class) // You can Have one more exception objects
	public ResponseEntity<?> handleFlatIdNotFoundException(FlatIdNotFoundException fe) {
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		System.out.println("Controller advice is executed when exception is thrown *****");
		Map<String, Object> errorMessage = new LinkedHashMap<>();
		errorMessage.put("error", "wrong id");
		errorMessage.put("timestamp", LocalDateTime.now());
		errorMessage.put("details", fe.getMessage());
		errorMessage.put("test", "Testing");

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
}
