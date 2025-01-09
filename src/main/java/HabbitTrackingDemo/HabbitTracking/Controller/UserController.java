package HabbitTrackingDemo.HabbitTracking.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Service.userService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final userService userService;
	
	@GetMapping
public List<User> getUsers(){
	return userService.getUsers();
}
}
