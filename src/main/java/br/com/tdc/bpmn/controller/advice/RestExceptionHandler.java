package br.com.tdc.bpmn.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleServiceException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "handleServiceException interceptou exceção [" + ex.getClass() + "] com a mensagem ["
				+ ex.getMessage() + "]";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

}
