package HabbitTrackingDemo.HabbitTracking.Registration.Token;

import java.util.Calendar;
import java.util.Date;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class VerificationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date expirationTime;
	
	private static final int EXPIRATION_TIME=15;
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User user;
	
	public VerificationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
		this.expirationTime = this.getTokenExpirationTime();
	}
	
	
	public VerificationToken(String token) {
		super();
		this.token = token;
	}
	
	public Date getTokenExpirationTime() {
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(calendar.MINUTE, EXPIRATION_TIME);
		return new Date(calendar.getTime().getTime());
	}

	
}
