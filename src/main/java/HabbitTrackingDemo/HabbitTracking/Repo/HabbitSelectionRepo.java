package HabbitTrackingDemo.HabbitTracking.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import HabbitTrackingDemo.HabbitTracking.Model.Habit;
import HabbitTrackingDemo.HabbitTracking.Model.SelectedUserHabits;
import HabbitTrackingDemo.HabbitTracking.enumclasses.HabitType;

@Repository
public interface HabbitSelectionRepo extends JpaRepository<Habit, Long> {
	@Query("select distinct h.category from Habit h where h.type = :type")
	List<String> findbytype(@Param("type") HabitType type);
	
	@Query("select h.name from Habit h where h.category = :category")
	List<String> findbycategory(@Param("category") String category);
	
	@Query("select h from Habit h where h.name=:habitname")
	List<Habit> findbyhabitname(@Param("habitname") String habitname);
	
	@Query("SELECT h FROM Habit h WHERE h.id = :id")
    Habit getHabitById(@Param("id") Long id);

	
}
 