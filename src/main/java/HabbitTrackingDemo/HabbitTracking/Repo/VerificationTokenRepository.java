package HabbitTrackingDemo.HabbitTracking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import HabbitTrackingDemo.HabbitTracking.Registration.Token.VerificationToken;
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

	VerificationToken findByToken(String token);

}
