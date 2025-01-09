//package HabbitTrackingDemo.HabbitTracking.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;
//import HabbitTrackingDemo.HabbitTracking.Model.Habit;
//import HabbitTrackingDemo.HabbitTracking.Model.HabitDTO;
//import HabbitTrackingDemo.HabbitTracking.Model.User;
//import HabbitTrackingDemo.HabbitTracking.Repo.UserRepo;
//import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;
//
//@Service
//public class RegisterService {
//@Autowired
//public UserRepo userrepo;
//@Autowired
//private JavaMailSender mailsender;
//
////Method to create admin at development time
//public User createAdmin(String username, String email, String password)
//{
//	User user =new User();
//	user.setUser_name(username);
//	user.setEmail(email);
//	user.setPassword(password);
//	user.getMobile_num();
//	user.setRole("ADMIN");
//	user.setActive(false);
//	
//	sendActivationEmail(user);
//	return user;
//	
//}
//
////Simple mail sender method inthat activationurl
//public void sendActivationEmail(User user)
//{
//	String subject ="Activate your admin account";
//	String activationUrl ="http://localhost:8080/habbit/activate?token" + user.getActivationToken();
//	String message ="Click the following link to activate your account: "+activationUrl;
//	
//	SimpleMailMessage email=new SimpleMailMessage();
//	email.setTo(user.getEmail());
//	email.setSubject(subject);
//	email.setText(message);
//	
//	mailsender.send(email);
//}
//
//public User activateAccount(String token)
//{
//	User user = userrepo.findByActivationToken(token).orElseThrow(()-> 
//	new RuntimeException("Invalid activation token"));
//	user.setActive(true);
//	user.setActivationToken(null);
//	
//	return userrepo.save(user);
//	
//}
//
////find by email method
//public Optional<User> findByEmail(String email)
//{
//	return userrepo.findByEmail(email);
//}
//
////login User using email or mobile field
//public Optional<User> findByMobEmail(String identifier)
//{
//	if(identifier.contains("@")) {
//	return userrepo.findByEmail(identifier);
//	}else {
//		return userrepo.findByMobile(identifier);
//	}
//}
//
//
////Create method for register user
//public User saveUser(User user)
//{
//	return userrepo.save(user);
//	
//}
//
////get habit by type
//public List<Habit> findHabitByType(HabitType type) {
//	return userrepo.findHabitByType(type);
//	
//}
//public List<String> findAllCategory(HabitType type) {
//    return userrepo.findbycategory(type);
//}
//
//public List<String> findallcategorybycategoryname(String category){
//	return userrepo.findcategorylist(category);
//}
//
//public List<HabitDTO> findcategorydetailbycategoryname(String name){
//	return userrepo.findcategoryDetailbyname(name);
//}
//
//}
