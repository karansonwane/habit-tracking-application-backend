//package HabbitTrackingDemo.HabbitTracking.Controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import HabbitTrackingDemo.HabbitTracking.Exp.CustomExp;
//import HabbitTrackingDemo.HabbitTracking.Model.Demo;
//import HabbitTrackingDemo.HabbitTracking.Model.Habit;
//import HabbitTrackingDemo.HabbitTracking.Model.HabitDTO;
//import HabbitTrackingDemo.HabbitTracking.Model.User;
//import HabbitTrackingDemo.HabbitTracking.Service.RegisterService;
//import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;
//import jakarta.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping("/habbit")
//public class RegisterController {
//@Autowired
//private RegisterService service;
//
//////API for create admin 
////@PostMapping("/create-admin")
////public ResponseEntity<User> createAdmin(@RequestBody Demo user)
////{
////	User admin=service.createAdmin(user.getUsername(), user.getEmail(),
////			user.getPassword());
////	return ResponseEntity.ok(admin);
////	
////}
////
////// Endpoint to activate the account
////@GetMapping("/activate")
////public ResponseEntity<String> activateAccount(@RequestParam String token)
////{
////	service.activateAccount(token);
////	
////    return ResponseEntity.ok("Account activated successfully!");
////	
////}
////
//////API for Register user(using email to avoid duplicate user)
////@PostMapping("/rgister")
////public ResponseEntity<String> registerUser(@RequestBody User user) throws CustomExp
////{
////	BCryptPasswordEncoder bcryptpassword = new BCryptPasswordEncoder();
////	Optional<User> useremail =  service.findByEmail(user.getEmail());
////	if(useremail.isPresent()) {
////	return new ResponseEntity<String>("email already register", HttpStatus.OK);
////	}else {
////		user.setPassword(bcryptpassword.encode(user.getPassword()));
////		service.saveUser(user);
////	 throw new CustomExp("Register successful");
////	}
////}
////
////// Login EndPoint for User[using mob or email And Password]
////@PostMapping("/userlogin")
////public ResponseEntity<String> loginUser(HttpSession session,
////		@RequestParam("identifier") String identifier, @RequestParam("password") String password)
////{
////	User user = new User();
////	BCryptPasswordEncoder bcryptpassword =new BCryptPasswordEncoder();
////	Optional<User> emailnum = service.findByMobEmail(identifier);
////    Object userlogin = 	session.getAttribute("Userlogin");
////	if(userlogin !=null) {
////		return new ResponseEntity<String>("Already login", HttpStatus.OK);
////	}else {
////		
////		if(emailnum !=null) {
////			
////			if(user.getStatus().equals("Active")){
////				//if(password == null) {
////		boolean verifypassword = bcryptpassword.matches(password, user.getPassword() );
////               
////            	   //throw new IllegalArgumentException("Password can not be null");
////               
////				//boolean verifypassword = false;
////				if(verifypassword) {
////					session.setAttribute("Userlogin", emailnum);
////				    session.setMaxInactiveInterval(500);
////				return new ResponseEntity<>("User login successfuly", HttpStatus.OK);
////			}else {
////				return new ResponseEntity<String>("wrong password", HttpStatus.BAD_REQUEST);
////			}
////		}else {
////			return new ResponseEntity<String>("User account inactive", HttpStatus.FORBIDDEN);
////		}
////	}else {
////		return	new ResponseEntity<String>("wrong eamilnum", HttpStatus.BAD_REQUEST);
////	}
////
////}
////
////}
////
//
//
////find by habbit category
//@GetMapping("/habittype/{type}")
//public List<Habit> findbyhabittype( @PathVariable HabitType type){
//	return service.findHabitByType(type);
//}
//
//@GetMapping("/category/{type}")
//public List<String> findcategarybytype(@PathVariable HabitType type) {
//    return service.findAllCategory(type);
//}
//
//@GetMapping("/fetchcategory/{category}")
//public List<String> findcategorybycategoryname(@PathVariable String category) {
//	return service.findallcategorybycategoryname(category);
//}
//
//@GetMapping("/categorydetail/{name}")
//public List<HabitDTO> fetchcategorydetailbycategoryname(@PathVariable String name){
//	return service.findcategorydetailbycategoryname(name);
//}
//
//
//}
