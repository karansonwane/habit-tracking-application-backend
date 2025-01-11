package HabbitTrackingDemo.HabbitTracking.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import HabbitTrackingDemo.HabbitTracking.Exp.UserAlreadyExistsException;
import HabbitTrackingDemo.HabbitTracking.Model.LoginDTO;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Registration.RegistrationRequest;
import HabbitTrackingDemo.HabbitTracking.Registration.Token.VerificationToken;
import HabbitTrackingDemo.HabbitTracking.Repo.UserRepository;
import HabbitTrackingDemo.HabbitTracking.Repo.VerificationTokenRepository;
import HabbitTrackingDemo.HabbitTracking.Serviceinterfaces.IUserService;
import customeExeptions.response.LoginResponse;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class userService implements IUserService{
	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final VerificationTokenRepository tokenRepository;
	

	@Override
	public List<User> getUsers() {
		 return userRepository.findAll();
		 
	}

	@Override
	public User registerUser(RegistrationRequest request) {
		Optional<User>user= this.findByEmail(request.email());
		if(user.isPresent()) {
		throw new UserAlreadyExistsException("User with email "+request.email()+" already exists");
		}
		var newUser=new User();
		newUser.setName(request.name());
		newUser.setNumber(request.number());
		newUser.setEmail(request.email());
		newUser.setPassword(passwordEncoder.encode(request.password()));
		newUser.setRole(request.role());
		return userRepository.save(newUser);
	}
 
	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public void saveUserVerificationToken(User theUser, String verificationToken) {
     var verificationtoken = new VerificationToken(verificationToken,theUser);	
	tokenRepository.save(verificationtoken);
	
	}
	@Override
	public String validateToken(String theToken) {
		VerificationToken token = tokenRepository.findByToken(theToken);
		if(token==null) {
			return "invalid verification token";
		}
		User user= token.getUser();
		
		Calendar calendar=Calendar.getInstance();
		if((token.getExpirationTime().getTime()-calendar.getTime().getTime())<=0) {
			
			tokenRepository.delete(token);
			return "token already expired";
		}
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}

	public User getUserById(long id) {
		return userRepository.findById(id);
	}
	
	

	
	



	
}
