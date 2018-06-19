package gov.uk.ho.ibm.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(value={AccountServiceException.class})
	    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request){
	       final String bodyOfResponse = "{\"message\":\"account id not found\"}";
	       final HttpHeaders httpHeaders = new HttpHeaders();
	        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	        return handleExceptionInternal(ex, bodyOfResponse, httpHeaders, HttpStatus.NOT_FOUND, request);
	    }

}
