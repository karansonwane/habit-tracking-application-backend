package HabbitTrackingDemo.HabbitTracking.Controller;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Registration.RegistrationRequest;
import HabbitTrackingDemo.HabbitTracking.Registration.Token.VerificationToken;
import HabbitTrackingDemo.HabbitTracking.Repo.UserRepository;
import HabbitTrackingDemo.HabbitTracking.Repo.VerificationTokenRepository;
import HabbitTrackingDemo.HabbitTracking.Service.userService;
import HabbitTrackingDemo.HabbitTracking.events.RegistrationCompleteEvent;
import customeExeptions.EmailAlreadyExistsException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
	
	private final VerificationTokenRepository tokenRepository;
	
	private final UserRepository userRepository;
	
	private final userService userService;
	
	private final ApplicationEventPublisher publisher;
	
	
//	The HttpServletRequest is used to dynamically construct the base URL where the application is hosted, so that the verification link sent to the user points to the correct server. This is crucial for environments where the application might run on different hosts (e.g., localhost in development, a public domain in production), and you want the verification link to work correctly in any case.
	@PostMapping
	public String registerUser(@RequestBody RegistrationRequest registrationRequest ,final HttpServletRequest request) {
		
		  if (userRepository.existsByEmail(registrationRequest.email())) {
		        throw new EmailAlreadyExistsException("Email already exists");
		    }
		  
			User user=userService.registerUser(registrationRequest);
			
			
		//publish registration event
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "Success! plese check your eamil for registration conformation";
	}
	
	
	
	@GetMapping("/verifyEmail")
	public String verifyEmail( @RequestParam("token") String token) {
		VerificationToken theToken = tokenRepository.findByToken(token);
	    if(theToken.getUser().isEnabled()) {
	    	return " this account has been already verified plese login.....!";
	    } 
	    String verificationResult =userService.validateToken(token);
	    if(verificationResult.equalsIgnoreCase("valid")) {
	    	return "Email verified successfully. now you can login to habit tracking application";
	    }
	    return "invalid verification token";
	}
	
	public String applicationUrl(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
}
 