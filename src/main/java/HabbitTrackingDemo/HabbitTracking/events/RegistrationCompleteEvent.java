package HabbitTrackingDemo.HabbitTracking.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class RegistrationCompleteEvent extends ApplicationEvent{

	@Autowired
	private User user;
	
	private String applicationURL;
	public RegistrationCompleteEvent(User user, String applicationURL) {
		super(user);
		this.user = user;
		this.applicationURL = applicationURL;
	}
	
	
	
	
	
}
