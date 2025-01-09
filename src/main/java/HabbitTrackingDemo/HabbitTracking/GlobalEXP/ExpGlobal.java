package HabbitTrackingDemo.HabbitTracking.GlobalEXP;

import java.util.Collections;

import javax.management.BadAttributeValueExpException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import HabbitTrackingDemo.HabbitTracking.Exp.CustomExp;
import customeExeptions.EmailAlreadyExistsException;

@RestControllerAdvice
public class ExpGlobal {
    
	@ExceptionHandler(CustomExp.class)
	public ResponseEntity<String> successPop(CustomExp customexp)
	{
		return new ResponseEntity<String>("Successful",HttpStatus.OK);
	}
	
	// 
	@ExceptionHandler(BadAttributeValueExpException.class)
	public ResponseEntity<String> badCredentials(BadAttributeValueExpException ex )
	{
		return new ResponseEntity<String>("Bad Credentials", HttpStatus.BAD_REQUEST);
		
	}
	
	
	 @ExceptionHandler(EmailAlreadyExistsException.class)
	    public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body(Collections.singletonMap("error", ex.getMessage()));
	    }
}
