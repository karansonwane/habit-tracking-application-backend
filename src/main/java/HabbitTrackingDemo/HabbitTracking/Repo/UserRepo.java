package HabbitTrackingDemo.HabbitTracking.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import HabbitTrackingDemo.HabbitTracking.Model.Habit;
import HabbitTrackingDemo.HabbitTracking.Model.HabitDTO;
import HabbitTrackingDemo.HabbitTracking.Model.User;
import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
		
//find by email custon repository class method
	@Query("select a from User a where a.email=:email")
	public Optional<User> findByEmail(String email);
	
//	//find by mobile number cutom method for login logic
//	@Query("select a from User a where a.mobile_num=:mobile_num")
//	public Optional<User> findByMobile(String mobile_num);
//	
	@Query("select a from Habit a where a.type=:type")
	public List<Habit> findHabitByType(HabitType type);
	
		 @Query("SELECT DISTINCT h.category FROM Habit h WHERE h.type = :type")
		 List<String> findbycategory(@Param("type") HabitType type);
		 
		 @Query("SELECT  h.name from Habit h where h.category=:category")
		 List<String> findcategorylist(@Param("category") String category);
		 
		 @Query("SELECT new HabbitTrackingDemo.HabbitTracking.Model.HabitDTO(h.name,h.plan) FROM Habit h WHERE h.name = :name")
		 List<HabitDTO> findcategoryDetailbyname(@Param("name") String name);
}

