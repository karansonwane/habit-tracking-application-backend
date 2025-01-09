package HabbitTrackingDemo.HabbitTracking.events.Listener;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.Service.userService;
import HabbitTrackingDemo.HabbitTracking.events.RegistrationCompleteEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener
implements ApplicationListener<RegistrationCompleteEvent> {
	
	private final userService userService;
	
	private final JavaMailSender mailSender;
	
	private  User theUser;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {

		//1.get the newly registered user
		 theUser= event.getUser();
		//2.create a verification token for the user
		String verificationToken = UUID.randomUUID().toString();
		//3.save the varification token fo the user
		userService.saveUserVerificationToken(theUser,verificationToken);
		//4. build the varification URL to be send to the user
		String url= event.getApplicationURL()+"/register/verifyEmail?token="+verificationToken;
		//5. send the email 
		try { 
			sendVerificationEmail(url);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		log.info("click the link to verify your registration : {}", url);
	}
	public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
		String subject="Email verification for habit tracker";
		String senderName="habit tracker";
		String mailContent="<p>Hello,"+theUser.getName()+",</p>"
				+"<p>thank you for registration with habit tracker, "+" "+
				"plese, follow the link below to complate your registration with habit tracker</p>"
				+ "<a href=\""+url+"\" verify your email to activate your account</a>"+"<p>click here to virefy <br> Habit tracker";
				MimeMessage message=mailSender.createMimeMessage();
				var messageHelper= new MimeMessageHelper(message);  
		        messageHelper.setFrom("karansonwane49@gmail.com", senderName);
		        messageHelper.setTo(theUser.getEmail());
		        messageHelper.setSubject(subject);
		        messageHelper.setText(mailContent, true);
		        mailSender.send(message);
	}
}
