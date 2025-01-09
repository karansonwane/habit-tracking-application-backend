package HabbitTrackingDemo.HabbitTracking.Service;

import java.util.Optional;

import org.eclipse.angus.mail.handlers.message_rfc822;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import HabbitTrackingDemo.HabbitTracking.Model.LoginDTO;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Repo.LoginRepo;
import HabbitTrackingDemo.HabbitTracking.Serviceinterfaces.ILoginService;
import ch.qos.logback.core.status.Status;
import customeExeptions.response.LoginResponse;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginRepo loginRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
        
   
//    public String login(String emailOrPhoneNumber, String password) {
//        User user = loginRepo.findByEmailOrPhoneNumber(emailOrPhoneNumber);
//
//        if (user == null) {
//            return "User not found. Please check your email or phone number.";
//        }
//
//        if (!user.isEnabled()) {
//            return "Account is not verified. Please verify your email.";
//        } 
//
//        boolean isPasswordValid = passwordEncoder.matches(password, user.getPassword());
//
//        if (!isPasswordValid) {
//            return "Invalid password. Please try again.";
//        }
//
//        return "Login successful!";
//    }

    
    
    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user1 = loginRepo.findByEmail(loginDTO.getEmail());

        if (user1 == null) {
            return new LoginResponse("Email does not exist", false);
        }
         
        if (!user1.isEnabled()) {
            return new LoginResponse("Account is not verified. Please verify your email.", false);
        }

        String password = loginDTO.getPassword();
        String encodedPassword = user1.getPassword();
        boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);

        if (isPasswordRight) {
            Optional<User> user = loginRepo.findByEmailOrPassword(loginDTO.getEmail(), encodedPassword);
            if (user.isPresent()) {
                return new LoginResponse("Login successful", true);
            } else {
                return new LoginResponse("Login failed", false);
            }
        } else {
            return new LoginResponse("Password does not match", false);
        }
    }



}
