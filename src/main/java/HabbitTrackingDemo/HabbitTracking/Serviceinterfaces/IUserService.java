package HabbitTrackingDemo.HabbitTracking.Serviceinterfaces;

import java.util.List;
import java.util.Optional;

import HabbitTrackingDemo.HabbitTracking.Model.LoginDTO;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Registration.RegistrationRequest;
import HabbitTrackingDemo.HabbitTracking.Registration.Token.VerificationToken;
import customeExeptions.response.LoginResponse;

public interface IUserService {
	
List<User> getUsers(); 

User registerUser(RegistrationRequest request);

Optional<User> findByEmail(String email);

void saveUserVerificationToken(User theUser,String verificationToken);

public String validateToken(String theToken);

}
