package HabbitTrackingDemo.HabbitTracking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import HabbitTrackingDemo.HabbitTracking.Service.LoginService;
import customeExeptions.response.LoginResponse;
import HabbitTrackingDemo.HabbitTracking.Exp.CustomExp;
import HabbitTrackingDemo.HabbitTracking.Model.LoginDTO;

@RestController
@RequestMapping("/users")
public class LoginController {

    @Autowired
    private LoginService loginService;

   @PostMapping(path ="/login")
   public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
   {
	   LoginResponse loginResponse=loginService.loginUser(loginDTO);
	   
	return ResponseEntity.ok(loginResponse);
	   
   }
} 
