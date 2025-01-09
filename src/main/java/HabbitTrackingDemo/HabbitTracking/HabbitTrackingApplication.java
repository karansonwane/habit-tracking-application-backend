package HabbitTrackingDemo.HabbitTracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude=SecurityAutoConfiguration.class)
public class HabbitTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabbitTrackingApplication.class, args);
		System.out.println("Habbit");
	}

}
