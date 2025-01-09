package HabbitTrackingDemo.HabbitTracking.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface LoginRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1 OR u.number = ?1")
    User findByEmailOrPhoneNumber(String emailOrPhoneNumber);
    
    Optional<User> findByEmailOrPassword(String email,String password);
    
    User findByEmail(String email);
}
