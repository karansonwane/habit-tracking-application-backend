package HabbitTrackingDemo.HabbitTracking.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HabbitTrackingDemo.HabbitTracking.Model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
	boolean existsByEmail(String email);
	User findById(long id);
   
}
